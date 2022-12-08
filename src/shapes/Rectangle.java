package shapes;

import org.json.simple.JSONObject;

import java.awt.*;

public class Rectangle extends ShapeBase{
    int width;
    int height;
    public Rectangle(){}
    public Rectangle(Point point,int width,int height){
        this.height=height;
        this.width=width;
        this.setPosition(point);
        setShapeName("rectangle");
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

    public  JSONObject shapeToJson(Rectangle r)
    {
        JSONObject rectangle = new JSONObject();
        rectangle.put("position",r.getPosition());
        rectangle.put("width",r.width);
        rectangle.put("height",r.height);
        rectangle.put("color",r.getColor());
        rectangle.put("fillColor",r.getFillColor());

        JSONObject rectangleObj = new JSONObject();
        rectangleObj.put("Rectangle",rectangle);
        return rectangleObj;
    }
    public static Rectangle parseShapeObject(JSONObject shape)
    {
        JSONObject shapeObj = (JSONObject) shape.get("Rectangle");
        Rectangle rectangle = new Rectangle();
        rectangle.setPosition((Point) shapeObj.get("position"));
        rectangle.setColor((Color) shapeObj.get("color"));
        rectangle.setFillColor((Color) shapeObj.get("fillColor"));
        return rectangle;
    }

    @Override
    public JSONObject shapeToJson(Shape shape) {
        return null;
    }

}
