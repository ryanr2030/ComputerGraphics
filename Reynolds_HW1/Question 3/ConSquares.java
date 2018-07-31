

import java.awt.*;
import java.awt.event.*;

public class ConSquares extends Frame {
    public static void main(String[] args) {new ConSquares();}

    ConSquares() {
        super("Concentric Squares: 50 Concentric Squares inside one another");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        setSize(600, 400);
        add("Center", new CvConSquares());
        setVisible(true);
    }
}

class CvConSquares extends Canvas {
    int maxX, maxY, minMaxXY, xCenter, yCenter;

    void initgr() {
        Dimension d = getSize();
        maxX = d.width - 1; maxY = d.height - 1;
        minMaxXY = Math.min(maxX, maxY);
        xCenter = maxX / 2; yCenter = maxY / 2;
    }

    int iX(float x) {return Math.round(x);}

    int iY(float y) {return maxY - Math.round(y);}

    public void paint(Graphics g) {
        initgr();


        float side = 0.49F * minMaxXY,

                xA, yA, xB, yB, xC, yC, xD, yD, xA1, yA1, xB1, yB1, xC1, yC1, xD1, yD1, p, q;
        q = 0.50F; p = 1 - q;
        xA = xCenter - side; yA = yCenter + side;
        xB = xCenter+side; yB = yCenter+side;
        xC =xA; yC =yCenter-side;
        xD=xCenter+side; yD=yCenter-side;
        //for() draw them 8 times per height and width
        for (int i = 0; i < 50; i++) {
            g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
            g.drawLine(iX(xB), iY(yB), iX(xD), iY(yD));
            g.drawLine(iX(xD), iY(yD), iX(xC), iY(yC));
            g.drawLine(iX(xC), iY(yC),iX(xA), iY(yA));
            xA1 = p * xA + q * xB; yA1 = p * yA + q * yB;
            xB1 = p * xB + q * xD; yB1 = p * yB + q * yD;
            xC1 = p * xC + q * xA; yC1 = p * yC + q * yA;
            xD1=p*xD+q*xC; yD1=p*yD+q*yC;
            xA = xA1; xB = xB1; xC = xC1; xD=xD1;
            yA = yA1; yB = yB1; yC = yC1; yD=yD1;
        }
    }
}