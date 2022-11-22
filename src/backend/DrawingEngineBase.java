package backend;

import java.awt.*;
import java.util.ArrayList;


public class DrawingEngineBase implements DrawingEngine  {
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

    @Override
    public void refresh(Graphics canvas) {
        canvas.clearRect(0, 0, 9999, 9999);
        for (ShapeBase shape: shapes) {
            shape.draw(canvas);
            if(shape.getFillColor() != null){
                shape.drawFill(canvas);
        }
        }
    }
}
