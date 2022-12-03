package shapes;

import java.awt.*;

public class Circle extends ShapeBase{
private int radius;
    public Circle(){}
public Circle(Point point,int radius,String name){
    this.radius=radius;
    this.setPosition(point);
    setShapeName(name);
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
        setPosition(point);
    }

}
