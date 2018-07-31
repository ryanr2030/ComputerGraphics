import javax.management.loading.MLet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Question3 extends Frame {
    public static void main(String[] args) {
        new Question3();
    }

    Question3() {
        super("Question3");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(340, 230);
        add("Center", new CvQuestion3());
        show();
    }
}
    class CvQuestion3 extends Canvas {
        float rWidth = 10.F, rHeight = 7.5F, pixelSize;
        int centerX, centerY, dGrid = 10, maxX, maxY;

        void initgr() {
            Dimension d;
            d = getSize();
            maxX = d.width - 1;
            maxY = d.height - 1;
            pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
            centerX = maxX / 2;
            centerY = maxY / 2;
        }

        int iX(float x) {
            return Math.round(centerX + x / pixelSize);
        }

        int iY(float y) {
            return Math.round(centerY - y / pixelSize);
        }

        void putPixel(Graphics g, int x, int y) {
            int x1 = x * dGrid, y1 = y * dGrid, h = dGrid / 2;
            g.drawOval(x1 - h, y1 - h, dGrid, dGrid);
        }

        void drawLine(Graphics g, int xP, int yP, int xQ, int yQ) {
            int x = xP, y = yP, D = 0, HX = xQ - xP, HY = yQ - yP, c, M, xInc = 1, yInc = 1;
            if (HX < 0) {
                xInc = -1;
                HX = -HX;
            }
            if (HY < 0) {
                yInc = -1;
                HY = -HY;
            }
            if (HY <= HX) {
                c = 2 * HX;
                M = 2 * HY;
                for (; ; ) {
                    putPixel(g, x, y);
                    if (x == xQ) break;
                    x += xInc;
                    D += M;
                    if (D > HX) {
                        y += yInc;
                        D -= c;
                    }
                }
            }
            else {
                c = 2 * HX;
                M = 2 * HY;
                for (; ; ) {
                    putPixel(g, x, y);
                    if (y == yQ) break;
                    y += yInc;
                    D += M;
                    if (D > HY) {
                        x+= xInc;
                        D -= c;
                    }
                }
            }

        }

        void drawCircle(Graphics g, int xC, int yC, int r) {
            int x = 0, y = r, u = 1, v = 2 * r - 1, E = 0;
            while (x < y) {

                putPixel(g, xC + x, yC + y);
                putPixel(g, xC + y, yC - x);
                putPixel(g, xC - x, yC - y);
                putPixel(g, xC - y, yC + x);
                x++;
                E += u;
                u += 2;
                if (v < 2 * E) {
                    y--;
                    E -= v;
                    v -= 2;
                }
                if (x > v) break;
                putPixel(g, xC + y, yC + x);
                putPixel(g, xC + x, yC - y);
                putPixel(g, xC - y, yC - x);
                putPixel(g, xC - x, yC + y);
            }
        }

        void showGrid(Graphics g) {
            for (int x = dGrid; x <= maxX; x += dGrid) {
                for (int y = dGrid; y <= maxY; y += dGrid) {
                    g.drawLine(x, y, x, y);
                }
            }
        }

        public void paint(Graphics g) {
            initgr();
            int x1, y1, x2, y2, xc, yc, r;
            System.out.print("Enter Line starting and end point (x1, y1, x2, y2) seperated by spaces:");
            Scanner input= new Scanner(System.in);
            x1=input.nextInt();
            y1=input.nextInt();
            x2=input.nextInt();
            y2=input.nextInt();
            System.out.print("Enter circle center and radius (xc, yc, radius) seperated by spaces:");
            xc=input.nextInt();
            yc=input.nextInt();
            r=input.nextInt();

            showGrid(g);
            drawLine(g, x1, y1, x2, y2);
            drawCircle(g, xc, yc, r);
        }
    }
