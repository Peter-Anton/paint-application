package shapes;
import java.awt.*;
public class LineSegment extends ShapeBase{
    Point point;

    public LineSegment(Point point1,Point point2){
        super(point1);
        point=point2;
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

    @Override
    public String toString() {
        return "line-"+uniqueKey1;
    }

    @Override
    public Point[] getPoint() {
        return new Point[]{getPosition(),point};
    }

    @Override
    public void resize(Point cornerPoint, Point dragedPoint) {
        Point[] points=getPoint();
        if (points[0].equals(cornerPoint)) {
            setPosition(dragedPoint);
        }
        if (points[1].equals(cornerPoint)) {
            point=dragedPoint;
        }
    }
}
