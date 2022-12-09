package shapes;
import java.awt.*;
public interface Resize {
    Point[] getPoint();
    Point resize(Point CornerPoint,Point dragedPoint);
}
