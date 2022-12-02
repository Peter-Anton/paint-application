package shapes;


import java.awt.*;

public class LineSegment extends ShapeBase{
    Point point;
    public LineSegment(){}
    public LineSegment(Point point1,Point point2,String name){
        point=point2;
        setPosition(point1);
        setShapeName(name);
        setColor(Color.black);
    }
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawLine(getPosition().x, getPosition().y, point.x, point.y);
           }

    @Override
    public void drawFill(Graphics canvas) {}

    @Override
    public boolean contains(Point point) {
        return false;
    }

    @Override
    public void moveTo(Point point) {
        setPosition(point);

    }
}
