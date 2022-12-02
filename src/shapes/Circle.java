package shapes;

import java.awt.*;

public class Circle extends ShapeBase{
private int diameter;
    public Circle(){}
public Circle(Point point,int diameter,String name){
    this.diameter=diameter;
    this.setPosition(point);
    setShapeName(name);
    setColor(Color.black);
}
    @Override
    public void draw(Graphics canvas) {
            canvas.setColor(getColor());
            canvas.drawOval(getPosition().x, getPosition().y, diameter,diameter);
    }

    public void drawFill(Graphics canvas)
    {
        canvas.setColor(getFillColor());
        canvas.fillOval(getPosition().x+1, getPosition().y+1, diameter-2,diameter-2);
    }

    @Override
    public boolean contains(Point point) {
        return false;
    }

    @Override
    public void moveTo(Point point) {
        setPosition(point);

    }
}
