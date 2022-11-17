package backend;

import java.awt.*;

public class Rectangle extends ShapeBase{
    int width;
    int height;
    public Rectangle(){};
    public Rectangle(int x,int y,int width,int height,String name){
        this.height=height;
        this.width=width;
        setPosition(new Point(x,y));
        setShapeName(name);


    }
    @Override
    public void draw(Graphics canvas) {
        canvas.drawRect(getPosition().x,getPosition().y,width,height);
        if (this.getProperties().get("")==null)
            return;

        if (this.getProperties().get("border color")==1.0){
            canvas.setColor(getColor());
            canvas.drawRect(getPosition().x,getPosition().y,width,height);

        }
        if (this.getProperties().get("fill color")==2.0){
            canvas.setColor(getFillColor());
            canvas.fillRect(getPosition().x, getPosition().y, width,height);}
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
