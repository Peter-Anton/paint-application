package shapes;

import org.json.simple.JSONObject;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class ShapeBase implements Shape{
    private Point position;
    private Color color;
    private Color fillColor;
    private   String name_key;
    private String ShapeName;
    private  static int Key1=0;
    private Point draggingPoint;
    @Override
    public void setPosition(Point position) {this.position = position;}

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setColor(Color color) {
        this.color=color;
    }
    @Override
    public Color getColor() {
        if (color==null)
            return Color.black;
        return color;
    }
    @Override
    public void setFillColor(Color color) {this.fillColor=color;}

    @Override
    public Color getFillColor() {
        return fillColor;

    }

    public void generateKey(){
        name_key=ShapeName+"_"+String.format("%02d",Key1++);
    }
    public String getName_key() {
        return name_key;
    }
    public void setShapeName(String shapeName) {
        ShapeName = shapeName;
    }

    @Override
    public void setDraggingPoint(Point point) {
        draggingPoint=point;
    }

    @Override
    public Point getDraggingPoint() {
        return draggingPoint;
    }
        public static Shape parseShapeObject(JSONObject shape)
    {
        String type = (String) shape.get("type");
//        try
//        {};
//         Class shapeClass = Class.forName(type);
//
//            Constructor<Shape> constructor = shapeClass.getConstructor();
//            Shape shape1 = (Shape) Constructor.newInstance();
//
//            shape1.shapeToJson(shape);

//            return shape1;

            return null;
//        } catch (ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//            return null;
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }

    }

        public abstract JSONObject shapeToJson(Shape shape) {}

}
