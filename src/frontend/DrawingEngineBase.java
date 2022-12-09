package frontend;

import shapes.Shape;
import shapes.ShapeBase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class DrawingEngineBase extends JPanel implements DrawingEngine  {
    private  Shape selctedShape;
    private  ArrayList<ShapeBase> shapes=new ArrayList<>();
    @Override
    public void addShape(shapes.Shape shape) {
        shapes.add((ShapeBase) shape);
        refresh();
    }

    @Override
    public void removeShape(shapes.Shape shape) {
        shapes.removeIf(f -> f.toString().equals(shape.toString()));
    }

    @Override
    public shapes.Shape[] getShapes() {
        return shapes.toArray(new Shape[0]);
    }
    public void setShape(Shape shape)
    {
        this.selctedShape=shape;
    }


        public void paintComponent (Graphics graphics)
        {
            super.paintComponent(graphics);
            shapes.forEach(shape -> shape.draw(graphics));

            if (selctedShape!=null){
                for (Point point:selctedShape.getPoint()) {

                    graphics.fillOval(point.x-5,point.y-5,15,15);
            }

            }
        }
    @Override
    public void refresh(Graphics canvas) {}

    public void refresh() {
            this.repaint();
        }

}
