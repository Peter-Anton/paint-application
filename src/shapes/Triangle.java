package shapes;

import java.awt.*;

public class Triangle extends ShapeBase{
  Point point2;
  Point point3;
    int [] y;
    int[] x;
    public Triangle(){};
    public Triangle(Point point1,Point point2,Point point3){
       setPosition(point1);
       setShapeName("triangle");
       this.point2=point2;
       this.point3=point3;
         x=new int[]{getPosition().x,point2.x,point3.x};
         y=new int[]{getPosition().y,point2.y,point3.y};
    }

    @Override
    public boolean contains(Point point) {
        Point[] points = {getPosition(),point2,point3};

        double d1, d2, d3;
        boolean hasNegative, hasPositive;

        d1 = sign(point, points[0], points[1]);
        d2 = sign(point, points[1], points[2]);
        d3 = sign(point, points[2], points[0]);

        hasNegative = (d1 < 0) ||(d2 < 0) ||(d3 < 0);
        hasPositive = (d1 > 0) ||(d2 > 0) ||(d3 > 0);
        return !(hasNegative && hasPositive);
    }

    @Override
    public void moveTo(Point point) {
        Point oldPoint = getPosition();
        Point dragPoint=this.getDraggingPoint();
        point.x+=(oldPoint.x-dragPoint.x);
        point.y+= (oldPoint.y-dragPoint.y);
        int x = point.x - oldPoint.x;
        int y = point.y - oldPoint.y;
        point2.x+=x;
        point3.x+=x;
        point2.y+=y;
        point3.y+=y;
        setPosition(point);
        System.out.println(getPosition());
        System.out.println(point2);
        System.out.println(point3);

    }
    private double sign(Point p1, Point p2, Point p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }


    @Override
    public void draw(Graphics canvas) {
        int[] x={getPosition().x,point2.x,point3.x};
        int [] y={getPosition().y,point2.y,point3.y};
        canvas.setColor(getColor());
        canvas.drawPolygon(x,y,3);
        if (getFillColor()!=null)
        {   canvas.setColor(getFillColor());
        canvas.fillPolygon(x,y,3);

        }
    }

}
