package shapes;

import java.awt.*;

public class Rectangle extends ShapeBase{
    int width;
    int height;
    public Rectangle(){}
    public Rectangle(Point point,int width,int height,String name){
        this.height=height;
        this.width=width;
        this.setPosition(point);
        setShapeName(name);
        setColor(Color.black);
    }
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawRect(getPosition().x,getPosition().y,width,height);
        }

        public void drawFill(Graphics canvas)
        {
            canvas.setColor(getFillColor());
            canvas.fillRect(getPosition().x+1,getPosition().y+1,width-1,height-1);
        }


    @Override
    public boolean contains(Point point) {
        Point point1=new Point(getPosition().x,getPosition().y);
        java.awt.Rectangle rectangle= new java.awt.Rectangle(getPosition().x,getPosition().y,width,height);
        return rectangle.contains(point);
            }

    @Override
    public void moveTo(Point point) {
        setPosition(point);
    }
}
