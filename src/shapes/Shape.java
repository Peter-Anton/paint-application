package shapes;
import org.json.simple.JSONObject;

import java.awt.*;

public interface Shape extends Moveable,Resize,Cloneable {
     void setPosition(Point position);
     Point getPosition();
     void setColor(Color color);
     Color getColor();
     void setFillColor(Color color);
     Color getFillColor();
     void draw(Graphics canvas);
     JSONObject shapeToJson();
}
