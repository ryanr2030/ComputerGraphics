import java.awt.*;
import java.awt.event.*;
public class squares extends Frame{
    squares(int n, int k, float q) {
        super("squares: Bunch O Squares");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(
                    WindowEvent e) {
                System.exit(0);
            }
        });
        add("Center", new CvSqs(n, k, q));
        setSize(600, 400);
        show();
    }

    public static void main(String[] args){
        int n=8;
        int k=10;
        float q=0.2F;
        new squares(n,k,q);
    }

}

class CvSqs extends Canvas{
    int centerX, centerY, n, k;
    float p0, q0;
    CvSqs(int nn, int kk, float qq){
        n=nn;
        k=kk;
        q0=qq;
        p0=1-q0;
    }
    int iX(float x){return Math.round(centerX+x);}
    int iY(float y){return Math.round(centerY+y);}
    public void paint(Graphics g){
        Dimension d=getSize();
        int maxX=d.width-1;
        int maxY=d.height-1;
        int minMaxXY=Math.min(maxX, maxY);
        centerX=maxX/2;
        centerY=maxY/2;
        float r=0.45F*minMaxXY/n;
        for(int x=0;x<n;x++)
            for(int y=0; y<n; y++){
                float xCnew=(2*x-n+1)*r;
                float yCnew=(2*y-n+1)*r;
                float xA,xB, xC, xD, yA, yB, yC, yD,
                        xA1, xB1, xC1, xD1, yA1, yB1, yC1, yD1,
                        p=p0, q=q0;

                xA=xD=xCnew-r;
                xB=xC=xCnew+r;
                yA=yB=yCnew-r;
                yC=yD=yCnew+r;


                for(int i=0; i<k;i++){
                    g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
                    g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
                    g.drawLine(iX(xC), iY(yC), iX(xD), iY(yD));
                    g.drawLine(iX(xD), iY(yD), iX(xA), iY(yA));

                    for (int l = 0; l<k; l++) {

                        if(x%2+y%2==1) {
                            p = q0;
                            q = p0;
                        }
                        else {
                            q = 0.2F;
                            p = 1 - p;
                        }



                    }
                    xA1=q*xA+p*xB;
                    yA1=q*yA+p*yB;

                    xB1=q*xB+p*xC;
                    yB1=q*yB+p*yC;

                    xC1=q*xC+p*xD;
                    yC1=q*yC+p*yD;

                    xD1=q*xD+p*xA;
                    yD1=q*yD+p*yA;

                    xA=xA1;
                    xB=xB1;
                    xC=xC1;
                    xD=xD1;

                    yA=yA1;
                    yB=yB1;
                    yC=yC1;
                    yD=yD1;






                }
            }
        }
    }

