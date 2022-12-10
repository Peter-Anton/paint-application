package shapes;

import org.json.simple.JSONObject;

import java.awt.*;

public class Rectangle extends ShapeBase{
    int width;
    int height;
    public Rectangle(){
        super(new Point());
    }
    public Rectangle(Point point,int width,int height){
        super(point);
        this.height=height;
        this.width=width;

    }
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawRect(getPosition().x,getPosition().y,width,height);
        if(getFillColor()!=null)
        {canvas.setColor(getFillColor());
        canvas.fillRect(getPosition().x+1,getPosition().y+1,width-1,height-1);
        }

    }

    @Override
    public boolean contains(Point point) {
        java.awt.Rectangle rectangle= new java.awt.Rectangle(getPosition().x,getPosition().y,width,height);
        return rectangle.contains(point);
            }

    @Override
    public void moveTo(Point point) {
        Point dragPoint=this.getDraggingPoint();
        point.x+=(getPosition().x-dragPoint.x);
        point.y+= (getPosition().y-dragPoint.y);
        setPosition(point);
    }

    public String toString(){
        return "rectangle-" + uniqueKey1;
    }

    @Override
    public Point[] getPoint() {
        return new Point[]{getPosition(),
                new Point(getPosition().x+width,getPosition().y),
                new Point(getPosition().x,getPosition().y+height),
                new Point(getPosition().x+width,getPosition().y+height)
        };
    }

    @Override
    public Point resize(Point cornerPoint, Point dragedPoint) {
        Point[] points=getPoint();
        if (points[0].equals(cornerPoint)) {
            width=Math.abs(width+(getPosition().x-dragedPoint.x));
            height=Math.abs(height+(getPosition().y-dragedPoint.y));
            setPosition(dragedPoint);
            return getPoint()[0];
        }
        if (points[1].equals(cornerPoint)) {
            this.height=getPosition().y+height-dragedPoint.y;
            this.width=dragedPoint.x-getPosition().x;
            setPosition(new Point(getPosition().x,dragedPoint.y));
            return getPoint()[1];
        }
        if (points[2].equals(cornerPoint)) {
            this.height=dragedPoint.y-getPosition().y;
            this.width=getPosition().x+width-dragedPoint.x;
            setPosition(new Point(dragedPoint.x,getPosition().y));
            return getPoint()[2];

        }
        if (points[3].equals(cornerPoint)) {
            this.width=Math.abs(width+(dragedPoint.x-points[3].x));
            this.height=Math.abs(height+(dragedPoint.y-points[3].y));
            return getPoint()[3];
        }
        return null;

    }

    public void parseShapeObject(JSONObject shape)
    {
        super.parseShapeObject(shape);
        this.width = ((Long)shape.get("width")).intValue();
        this.height = ((Long)shape.get("height")).intValue();
    }

    @Override
    public JSONObject shapeToJson() {
        JSONObject rectangle = super.shapeToJson();
        rectangle.put("width",this.width);
        rectangle.put("height",this.height);

        return rectangle;
    }
}
