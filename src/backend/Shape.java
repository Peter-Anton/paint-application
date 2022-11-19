package backend;

import java.awt.*;

public interface Shape {
    public void setPosition(Point position);
    public Point getPosition();
    /* colorize */
    public void setColor(Color color);
    public Color getColor();
    public void setFillColor(Color color);
    public Color getFillColor();
    /* redraw the shape on the canvas */
    public void draw(Graphics canvas);
    public void drawFill(Graphics canvas);
    public  String getName_key();

}
