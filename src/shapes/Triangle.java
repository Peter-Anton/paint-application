package shapes;
import org.json.simple.JSONObject;
import java.awt.*;
public class Triangle extends ShapeBase{
  Point point2;
  Point point3;
    int [] y;
    int[] x;
    public Triangle(){
        super(new Point());
    }
    public Triangle(Point point1,Point point2,Point point3){
        super(point1);
       this.point2=point2;
       this.point3=point3;
         x=new int[]{getPosition().x,point2.x,point3.x};
         y=new int[]{getPosition().y,point2.y,point3.y};
    }

    public String toString(){
        return "triangle-" + uniqueKey1;
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

    @Override
    public Point[] getPoint() {
        return new Point[]{getPosition(),point2,point3};
    }

    @Override
    public Point resize(Point cornerpoint, Point dragedPoint) {
        Point[] points=getPoint();
        if (points[0].equals(cornerpoint)) {
            super.setPosition(dragedPoint);
            return getPoint()[0];
        }
        if (points[1].equals(cornerpoint)) {
             point2=dragedPoint;
             return getPoint()[1];

        }
        if (points[2].equals(cornerpoint)) {
            point3=dragedPoint;
            return getPoint()[2];
        }
        return null;
    }
    public JSONObject shapeToJson() {
        JSONObject triangle = super.shapeToJson();
        triangle.put("point2x",this.point2.x);
        triangle.put("point2y",this.point2.y);
        triangle.put("point3x",this.point3.x);
        triangle.put("point3y",this.point3.y);
        return triangle;
    }
    public void parseShapeObject(JSONObject shape)
    {
        super.parseShapeObject(shape);
        Point point2  = new Point();
        point2.x = ((Long) shape.get("point2x")).intValue();
        point2.y = ((Long) shape.get("point2y")).intValue();
        this.point2=point2;
        Point point3  = new Point();
        point3.x = ((Long) shape.get("point3x")).intValue();
        point3.y = ((Long) shape.get("point3y")).intValue();
        this.point3=point3;

    }

}
