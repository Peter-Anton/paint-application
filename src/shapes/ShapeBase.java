package shapes;

import java.awt.*;

public abstract class ShapeBase implements Shape,Resize{
    private Point position;
    private Color color;
    private Color fillColor;
    private  static int Key1=0;
    protected int uniqueKey1 = Key1 ++;
    private Point draggingPoint;
    public ShapeBase(Point position){
    setPosition(position);
    }
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

    @Override
    public void setDraggingPoint(Point point) {
        draggingPoint=point;
    }

    @Override
    public Point getDraggingPoint() {
        return draggingPoint;
    }

    @Override
   public Object clone() throws CloneNotSupportedException {
        ShapeBase clone= (ShapeBase) super.clone();
        clone.uniqueKey1=Key1++;
        return clone;
    }

    @Override
    public abstract Point[] getPoint();
}
