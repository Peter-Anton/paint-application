package backend;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class ShapeBase implements Shape{
    private Point position;
    private Map<String, Double> properties=new HashMap<>();
    private Color color;
    private Color fillColor;
    private   String name_key;
    private final Random random=new Random();
    private String ShapeName;

    @Override
    public void setPosition(Point position) {this.position = position;}

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }

    @Override
    public void setColor(Color color) {this.color=color;}
    @Override
    public Color getColor() {
        return color;
    }
    @Override
    public void setFillColor(Color color) {this.fillColor=color;}

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    abstract public void draw(Graphics canvas) ;
    abstract public  String[] data();

    public void generateKey(){
        int Key1=random.nextInt(100);
        name_key=ShapeName+"_"+Key1;
    }
    public String getName_key() {
        return name_key;
    }
    public void setShapeName(String shapeName) {
        ShapeName = shapeName;
    }

}
