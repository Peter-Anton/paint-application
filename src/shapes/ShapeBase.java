package shapes;

import org.json.simple.JSONArray;
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

        public  Shape parseShapeObject(JSONObject jsonObject)
        {
            Point position  = new Point();
            position.x = ((Long) jsonObject.get("positionx")).intValue();
            position.y = ((Long) jsonObject.get("positiony")).intValue();
            this.position = position;
            if(jsonObject.get("colorR") != null && jsonObject.get("colorG") != null && jsonObject.get("colorB")!= null) {
                System.out.println("R-->"+jsonObject.get("colorR"));
                System.out.println("G-->"+jsonObject.get("colorG"));
                System.out.println("B-->"+jsonObject.get("colorB"));
                Color color = new Color(((Long) jsonObject.get("colorR")).intValue(), ((Long) jsonObject.get("colorG")).intValue(), ((Long) jsonObject.get("colorB")).intValue());
                this.color = color;
            }
            if(jsonObject.get("fillColorR") != null && jsonObject.get("fillColorG") != null && jsonObject.get("fillColorB")!= null) {
                System.out.println("fR-->"+jsonObject.get("fillColorR"));
                System.out.println("fG-->"+jsonObject.get("fillColorG"));
                System.out.println("fB-->"+jsonObject.get("fillColorB"));
                Color fillColor = new Color(((Long) jsonObject.get("fillColorR")).intValue(), ((Long) jsonObject.get("fillColorG")).intValue(), ((Long) jsonObject.get("fillColorB")).intValue());
                this.fillColor = fillColor;
            }
          return this;

        }






        public static Shape parseShapeObject2(JSONObject shapeJson)
    {

        String type = (String) shapeJson.get("type");
        try {
            Class shapeClass = Class.forName(type);

            Constructor<Shape> constructor = shapeClass.getConstructor();
            Shape shape = (Shape) constructor.newInstance();

            ((ShapeBase) shape).parseShapeObject(shapeJson);

            return shape;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

        public  JSONObject shapeToJson()
        {
            JSONObject shape = new JSONObject();
            shape.put("positionx",this.position.x);
            shape.put("positiony",this.position.y);
            shape.put("type",this.getClass().getName());
            if(this.color != null) {
                shape.put("colorR", this.color.getRed());
                shape.put("colorG", this.color.getGreen());
                shape.put("colorB", this.color.getBlue());
            }
            if(this.fillColor != null) {
                shape.put("fillColorR", this.fillColor.getRed());
                shape.put("fillColorG", this.fillColor.getGreen());
                shape.put("fillColorB", this.fillColor.getBlue());
            }
            return shape;

        }

}
