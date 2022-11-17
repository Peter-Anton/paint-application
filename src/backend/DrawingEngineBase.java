package backend;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DrawingEngineBase implements DrawingEngine  {
private  ArrayList<Shape> shapes=new ArrayList<>();

//    private final Graphics canvas;
    private static final Random random=new Random();

    public DrawingEngineBase(Graphics canvas) {
//        this.canvas = canvas;
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.removeIf(f -> f.getName_key().equals(shape.getName_key()));
    }

    @Override
    public Shape[] getShapes() {
        return shapes.toArray(new Shape[0]);
    }

    @Override
    public void refresh(Graphics canvas) {
        canvas.clearRect(0, 0, 9999, 9999);
        for (Shape shape: shapes) {
//            canvas.setColor(shape.getColor());

            shape.draw(canvas);
        }
    }
}
