package shapes;
import org.json.simple.JSONObject;

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

        public  JSONObject shapeToJson(LineSegment l)
    {
        JSONObject lineSegment = new JSONObject();
        lineSegment.put("position",l.getPosition());
        lineSegment.put("point",l.point);
        lineSegment.put("color",l.getColor());
        lineSegment.put("fillColor",l.getFillColor());

        JSONObject lineSegmentObj = new JSONObject();
        lineSegmentObj.put("LineSegment",lineSegment);
        return lineSegmentObj;
    }
    public static LineSegment parseShapeObject(JSONObject shape)
    {
        JSONObject shapeObj = (JSONObject) shape.get("LineSegment");
        LineSegment lineSegment = new LineSegment();
        lineSegment.setPosition((Point) shapeObj.get("position"));
        lineSegment.point = (Point) shapeObj.get("point");
        lineSegment.setColor((Color) shapeObj.get("color"));
        lineSegment.setFillColor((Color) shapeObj.get("fillColor"));

        return lineSegment;
    }

    @Override
    public JSONObject shapeToJson(Shape shape) {
        return null;
    }


}
