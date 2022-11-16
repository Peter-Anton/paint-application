package backend;

import java.awt.*;

public class Circle extends ShapeBase{
private int diameter;
    public Circle(){};
public Circle(int x,int y,int diameter,String name){
    this.diameter=diameter;
    this.setPosition(new Point(x,y));
    setShapeName(name);
}
    @Override
    public void draw(Graphics canvas) {
canvas.drawOval(getPosition().x, getPosition().y, diameter,diameter);
     if (this.getFillColor()!=null){
         canvas.setColor(getFillColor());
         canvas.fillOval(getPosition().x, getPosition().y, diameter,diameter);

      }
}

    @Override
    public String[] data() {
        return new String[]{"x","y","diameter"};
    }
}
