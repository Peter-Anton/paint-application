package frontend;

import shapes.Shape;

import java.awt.*;

public interface DrawingEngine {
    /* manage shapes objects */
    public void addShape(shapes.Shape shape);
    public void removeShape(shapes.Shape shape);
    /* return the created shapes objects */
    public Shape[] getShapes();
    /* redraw all shapes on the canvas */
    public void refresh(Graphics canvas);
}
