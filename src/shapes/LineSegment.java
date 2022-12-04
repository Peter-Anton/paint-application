package shapes;
import java.awt.*;
public class LineSegment extends ShapeBase{
    Point point;

    public LineSegment(){}
    public LineSegment(Point point1,Point point2){
        point=point2;
        setPosition(point1);
        setShapeName("line");
    }
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawLine(getPosition().x, getPosition().y, point.x, point.y);
           }


    @Override
    public boolean contains(Point point) {
        Point pStart=new Point(getPosition().x, getPosition().y);
        double lineLength=pStart.distance(this.point);
        double lengthFromPoint=pStart.distance(point)+this.point.distance(point);
        return Math.abs(lengthFromPoint-lineLength)<=2;
    }

    @Override
    public void moveTo(Point point) {
        Point oldPoint = getPosition();
        Point dragPoint=this.getDraggingPoint();
        point.x+=(getPosition().x-dragPoint.x);
        point.y+= (getPosition().y-dragPoint.y);
        int x = point.x - oldPoint.x;
        int y = point.y - oldPoint.y;
        this.point.x+=x;
        this.point.y+=y;
        setPosition(point);


    }
}
