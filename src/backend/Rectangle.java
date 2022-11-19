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
//        setColor(Color.black);
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
    public String[] data() {
        return new String[]{"x axis","y axis","width","height"};
    }
    public String[] squareData(){
        return new String[]{"x axis","y axis","width"};
    }
}
