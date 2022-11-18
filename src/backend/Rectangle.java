package backend;

import java.awt.*;

public class Rectangle extends ShapeBase{
    int width;
    int height;
    public Rectangle(){}
    public Rectangle(int x,int y,int width,int height,String name){
        this.height=height;
        this.width=width;
        setPosition(new Point(x,y));
        setShapeName(name);
        setColor(Color.black);
        setFillColor(new Color(238,238,238,0));


    }
    @Override
    public void draw(Graphics canvas) {

        canvas.drawRect(getPosition().x,getPosition().y,width,height);

        }

    @Override
    public String[] data() {
        return new String[]{"x axis","y axis","width","height"};
    }
    public String[] squareData(){
        return new String[]{"x axis","y axis","width"};
    }
}
