package shapes;
import java.awt.*;

public interface Shape extends Moveable {
     void setPosition(Point position);
     Point getPosition();
     void setColor(Color color);
     Color getColor();
     void setFillColor(Color color);
     Color getFillColor();
     void draw(Graphics canvas);
}
