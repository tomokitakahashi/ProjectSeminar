// import Default framework
import java.io.File;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;
import javax.xml.bind.JAXB;

public class SaveTest2 {

    public static void main(String[] args) {
        Point2D.Double coodinate = new Point2D.Double(1.0,2.0);
        JAXB.marshal(coodinate, System.out);
    }
}
