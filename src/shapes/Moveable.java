package shapes;

import java.awt.*;

public interface Moveable  {
     void setDraggingPoint(Point point);
     Point getDraggingPoint();
     boolean contains(Point point);
     void moveTo(Point point);
}
