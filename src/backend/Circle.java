package backend;

import java.awt.*;

public class Circle extends ShapeBase{
private int diameter;
    public Circle(){}
public Circle(int x,int y,int diameter,String name){
    this.diameter=diameter;
    this.setPosition(new Point(x,y));
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
    public String[] data() {
        return new String[]{"x","y","diameter"};
    }
}
