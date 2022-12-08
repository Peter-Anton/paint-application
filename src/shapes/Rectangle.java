package shapes;

import java.awt.*;

public class Rectangle extends ShapeBase{
    int width;
    int height;
    public Rectangle(Point point,int width,int height){
        super(point);
        this.height=height;
        this.width=width;

    }
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawRect(getPosition().x,getPosition().y,width,height);
        if(getFillColor()!=null)
        {canvas.setColor(getFillColor());
        canvas.fillRect(getPosition().x+1,getPosition().y+1,width-1,height-1);
        }

    }

    @Override
    public boolean contains(Point point) {
        java.awt.Rectangle rectangle= new java.awt.Rectangle(getPosition().x,getPosition().y,width,height);
        return rectangle.contains(point);
            }

    @Override
    public void moveTo(Point point) {
        Point dragPoint=this.getDraggingPoint();
        point.x+=(getPosition().x-dragPoint.x);
        point.y+= (getPosition().y-dragPoint.y);
        setPosition(point);
    }

    public String toString(){
        return "rectangle-" + uniqueKey1;
    }

    @Override
    public Point[] getPoint() {
        return new Point[]{getPosition(),
                new Point(getPosition().x+width,getPosition().y),
                new Point(getPosition().x,getPosition().y+height),
                new Point(getPosition().x+width,getPosition().y+height)
        };
    }

    @Override
    public void resize(Point CornerPoint, Point dragedPoint) {
        //TODO:tommorow
    }
}
