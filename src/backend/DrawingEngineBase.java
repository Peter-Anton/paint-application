package backend;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class DrawingEngineBase implements DrawingEngine  {
private  ArrayList<Shape> shapes=new ArrayList<>();
Graphics canvas;
    public DrawingEngineBase(Graphics canvas) {
        this.canvas=canvas;
    }

    private static final Random random=new Random();

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        refresh();
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.removeIf( f-> f.getName_key().equals(shape.getName_key()));
//        refresh(canvas);
    }

    @Override
    public Shape[] getShapes() {
        return shapes.toArray(new Shape[0]);
    }

    @Override
    public void refresh(Graphics canvas) {
        canvas.clearRect(0, 0, 9999, 9999);
        for (Shape shape: shapes) {
            if(shape.getColor() != null)
            {
                canvas.setColor(shape.getColor());
            }

            shape.draw(canvas);
        }
    }

    public void refresh() {
        refresh(canvas);
    }
}
