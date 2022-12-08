package shapes;
import java.awt.*;
public interface Resize {
    Point[] getPoint();
    void resize(Point CornerPoint,Point dragedPoint);
}
