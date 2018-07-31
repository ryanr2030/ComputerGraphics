import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;


public class Question4 extends Frame {

    public static void main(String[] args)


    {new Question4();}
    Question4()
    {

        super("Define vertices by clicking");
        addWindowListener(new WindowAdapter()
        { public void windowClosing(WindowEvent e)
        { System.exit(0);}
        });


        setSize (500, 300);
        add("Center", new Lines());
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        show();
    }
}



class Lines extends Canvas {


    Vector v = new Vector();

    Point2D a, b, c, d;
    float x0, y0, rWidth = 10.0F, rHeight = 7.5F, pixelSize, xI,yI;
    boolean ready = true;
    int centerX, centerY;
    int clickCounter = 0;

    Lines() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                float xA = fx(evt.getX()), yA = fy(evt.getY());
                float dx = xA - x0, dy = yA - y0;
                if (clickCounter < 4) {
                    v.addElement(new Point2D(xA, yA));
                    clickCounter = clickCounter + 1;
                    repaint();
                    ready = true;
                } else if (clickCounter == 4) {
                    ready = false;
                    v.addElement(new Point2D(xA, yA));
                }
            }
        });
    }

    public void paint(Graphics g) {

        g.drawString("Click Four Points to create two lines.", 5, 20);

        g.setColor(Color.white);

        selection();
        int left = iX(-rWidth / 2), right = iX(rWidth / 2),
                bottom = iY(-rHeight / 2), top = iY(rHeight / 2) + 60;
        g.drawRect(left, top - 15, right - left, bottom - top);
        g.fillRect(left, top - 15, right - left, bottom - top);
        g.setColor(Color.red);

        int n = 4;
        if (n == 0) return;
        a = (Point2D) (v.elementAt(0));
        g.fillOval(iX(a.x) - 2, iY(a.y) - 2, 4, 4);


        for (int i = 1; i<n; i++) {
            if (i == (4) && !ready) {
                break;
            }

            Point2D b = (Point2D) (v.elementAt(i % n));
            g.fillOval(iX(b.x) - 2, iY(b.y) - 2, 4, 4);
            if(i!=2)
            g.drawLine(iX(a.x), iY(a.y), iX(b.x), iY(b.y));
            a = b;
        }
        a=(Point2D) (v.elementAt(0));
        b=(Point2D) (v.elementAt(1));
        c=(Point2D) (v.elementAt(2));
        d=(Point2D) (v.elementAt(3));
        double a1 = b.y - a.y;
        double b1 = a.x - b.x;

        double c1 = a1*(a.x) + b1*(a.y);

        // Line CD represented as a2x + b2y = c2
        double a2 = d.y - c.y;
        double b2 = d.x - c.x;
        double c2 = a2*(c.x)+ b2*(c.y);

        double determinant = a1*b2 - a2*b1;
        double epsilon=(Math.pow(10.0,-3.0)*(Math.pow(b1,2)+Math.pow(a1,2)+Math.pow(d.x-c.x,2)+Math.pow(a2,2)));
        if (determinant<=epsilon)
        {
            g.drawString("Line AB and CD are colinear or parrallel", 5, 40);
        }
        else
        {
            double x = (b2*c1 - b1*c2)/determinant;
            double y = (a1*c2 - a2*c1)/determinant;
            g.drawOval(iX((float)x)-5, iY((float)y)-5, 10, 10);
        }













    }


    void selection() {
        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;
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

    float fx(int x) {
        return (x - centerX) * pixelSize;
    }

    float fy(int y) {
        return (centerY - y) * pixelSize;
    }
}


class Point2D {
    float x, y;
    Point2D(float x, float y){this.x = x; this.y = y;}}




