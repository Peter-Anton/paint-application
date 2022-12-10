package shapes;
import org.json.simple.JSONObject;
import java.awt.*;
public class LineSegment extends ShapeBase{
    Point point;
    public LineSegment(){
        super(new Point());
    }

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
        return Math.abs(lengthFromPoint-lineLength) <= 1;
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


    public void parseShapeObject(JSONObject shape)
    {
        super.parseShapeObject(shape);
        Point point  = new Point();
        point.x = ((Long) shape.get("pointx")).intValue();
        point.y = ((Long) shape.get("pointy")).intValue();
        this.point=point;
    }

    @Override
    public JSONObject shapeToJson() {
        JSONObject lineSegment = super.shapeToJson();
        lineSegment.put("pointx",this.point.x);
        lineSegment.put("pointy",this.point.y);

        return lineSegment;
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
    public Point resize(Point cornerPoint, Point dragedPoint) {
        Point[] points=getPoint();
        if (points[0].equals(cornerPoint)) {
            super.setPosition(dragedPoint);
            return getPoint()[0];
        }
        if (points[1].equals(cornerPoint)) {
            point=dragedPoint;
            return getPoint()[1];
        }
        return null;
    }
}
