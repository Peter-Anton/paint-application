package shapes;

import java.awt.*;

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
        System.out.println("fi outline color");
    }
    @Override
    public Color getColor() {
        if (color==null)
            return Color.black;
        return color;
    }
    @Override
    public void setFillColor(Color color) {this.fillColor=color;
        System.out.println("et7t fill color");}

    @Override
    public Color getFillColor() {
        System.out.println("fi fill color");
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
}
