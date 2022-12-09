package shapes;

import java.awt.*;
import org.json.simple.JSONObject;
public class Oval extends ShapeBase{
private int height,width;
public Oval(){
    super(new Point());
};
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
    public Point resize(Point cornerPoint, Point dragedPoint) {
        Point[] points=getPoint();
        if (points[0].equals(cornerPoint)) {
            width+=(getPosition().x-dragedPoint.x);
            height+=(getPosition().y-dragedPoint.y);
            setPosition(dragedPoint);
            return getPoint()[0];
        }
        if (points[1].equals(cornerPoint)) {
            width+=(getPosition().x-dragedPoint.x);
            height=(getPosition().y-dragedPoint.y);
            return getPoint()[1];

        }
        if (points[2].equals(cornerPoint)) {
            width+=(cornerPoint.x-dragedPoint.x);
            height+=(getPosition().y-dragedPoint.y);
            return getPoint()[2];

        }
        if (points[3].equals(cornerPoint)) {
            this.width=Math.abs(width+(dragedPoint.x-points[3].x));
            this.height=Math.abs(height+(dragedPoint.y-points[3].y));
            return getPoint()[3];
        }
        return null;
    }

    public void parseShapeObject(JSONObject shape)
    {
        super.parseShapeObject(shape);

        int height = ((Long) shape.get("height")).intValue();
        int width = ((Long) shape.get("width")).intValue();

        this.height = height;
        this.width=width;

    }


    public JSONObject shapeToJson() {
        JSONObject oval = super.shapeToJson();
        oval.put("height",this.height);
        oval.put("width",this.width);
        return oval;
    }
}
