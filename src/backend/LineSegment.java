package backend;

import frontend.Paint;

import java.awt.*;

public class LineSegment extends ShapeBase{
    private  int x2;
    private  int y2;


    public LineSegment(){}
    public LineSegment(int x1,int y1,int x2, int y2,String name){
        this.x2=x2;
        this.y2=y2;
        setPosition(new Point(x1,y1));
        setShapeName(name);
    }
    @Override
    public void draw(Graphics canvas) {
        canvas.drawLine(getPosition().x,getPosition().y,x2,y2);
    }

    @Override
    public String[] data() {
        return new String[]{"x1","y1","x2", "y2"};
    }
}
