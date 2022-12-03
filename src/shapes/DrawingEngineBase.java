package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class DrawingEngineBase extends JPanel implements DrawingEngine  {
private  ArrayList<ShapeBase> shapes=new ArrayList<>();
    @Override
    public void addShape(Shape shape) {
        shapes.add((ShapeBase) shape);
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.removeIf(f -> f.getName_key().equals(((ShapeBase)shape).getName_key()));
    }

    @Override
    public Shape[] getShapes() {
        return shapes.toArray(new Shape[0]);
    }


        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            shapes.forEach(shape -> shape.draw(graphics));
        }

    @Override
    public void refresh(Graphics canvas) {}

    public void refresh() {
            this.repaint();
        }

}
