package shapes;

import java.awt.*;

public class Triangle extends ShapeBase{
  Point point2;
  Point point3;
    public Triangle(){};
    public Triangle(Point point1,Point point2,Point point3,String name){
       setPosition(point1);
       setShapeName(name);
       this.point2=point2;
       this.point3=point3;
          }

    @Override
    public boolean contains(Point point) {
        //TODO:contains
        return false;
    }

    @Override
    public void moveTo(Point point) {
        //TODO:moveTo
    }

    @Override
    public void draw(Graphics canvas) {
       int[] x={getPosition().x,point2.x,point3.x};
       int [] y={getPosition().y,point2.y,point3.y};
        canvas.setColor(getColor());
        canvas.drawPolygon(x,y,3);
    }

    @Override
    public void drawFill(Graphics canvas) {
        int[] x={getPosition().x,point2.x,point3.x};
        int [] y={getPosition().y,point2.y,point3.y};
        canvas.setColor(getColor());
        canvas.fillPolygon(x,y,3);
    }

}
