package shapes;

import java.awt.*;

public class Oval extends ShapeBase{
private int height,width;
public Oval(Point point, int height, int width){
    super(point);
    this.height=height;
    this.width=width;
}
    @Override
    public void draw(Graphics canvas) {
            canvas.setColor(getColor());
            canvas.drawOval(getPosition().x, getPosition().y, width,height);
            if (getFillColor()!=null) {
                canvas.setColor(getFillColor());
                canvas.fillOval(getPosition().x + 1, getPosition().y + 1, width - 2, height - 2);
            }
            }

    @Override
    public boolean contains(Point point) {
        Point center=new Point();
        center.x=getPosition().x+(width/2);
        center.y=getPosition().y+(height/2);
        return Math.abs(center.x - point.x) < (width / 2) && Math.abs(center.y - point.y) < (height / 2);
    }



    @Override
    public void moveTo(Point point) {
        Point dragPoint=this.getDraggingPoint();
        point.x+=(getPosition().x-dragPoint.x);
        point.y+= (getPosition().y-dragPoint.y);
        setPosition(point);
    }


    public String toString(){
        return "oval-" + uniqueKey1;
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
        //TODo:Tommorow
    }
}
