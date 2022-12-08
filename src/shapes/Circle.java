package shapes;

import org.json.simple.JSONObject;

import java.awt.*;

public class Circle extends ShapeBase{
private int radius;
    public Circle(){}
public Circle(Point point,int radius){
    this.radius=radius;
    this.setPosition(point);
    setShapeName("circle");

}
    @Override
    public void draw(Graphics canvas) {
            canvas.setColor(getColor());
            canvas.drawOval(getPosition().x, getPosition().y, radius*2,radius*2);
            if (getFillColor()!=null) {
                canvas.setColor(getFillColor());
                canvas.fillOval(getPosition().x + 1, getPosition().y + 1, (radius * 2) - 2, (radius * 2) - 2);
            }
            }





    @Override
    public boolean contains(Point point) {
        Point center=new Point();
        center.x=this.getPosition().x+radius;
        center.y=this.getPosition().y+radius;
        return point.distance(center) < radius;
    }

    @Override
    public void moveTo(Point point) {
        Point dragPoint=this.getDraggingPoint();
        point.x+=(getPosition().x-dragPoint.x);
        point.y+= (getPosition().y-dragPoint.y);
        setPosition(point);
    }



    public static Circle parseShapeObject(JSONObject shape)
    {
        JSONObject shapeObj = (JSONObject) shape.get("Circle");
        Circle circle = new Circle();
        int radius = (int) shapeObj.get("radius");
        Point position = (Point) shapeObj.get("position");
        Color color = (Color) shapeObj.get("color");
        Color fillColor = (Color) shapeObj.get("fillColor");
        circle.radius = radius;
        circle.setColor(color);
        circle.setFillColor(fillColor);
        circle.setPosition(position);
        return circle;
    }


    public JSONObject shapeToJson(Circle c) {
        JSONObject circle = new JSONObject();
        circle.put("radius",c.radius);
        circle.put("position",c.getPosition());
        circle.put("color",c.getColor());
        circle.put("fillColor",c.getFillColor());

        JSONObject circleObj = new JSONObject();
        circleObj.put("Circle",circle);
        return circleObj;
    }

}
