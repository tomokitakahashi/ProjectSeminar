// import Default framework
import java.io.File;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;

//import framework for xml writing
import javax.xml.transform.OutputKeys;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SaveTest {
  private ArrayList<Point2D.Double> list = new ArrayList<Point2D.Double>();

  public void run(){
    // Documentインスタンスの生成
    DocumentBuilder documentBuilder = null;
    try {
      documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }

    // XML文書の作成
    Document document = documentBuilder.newDocument();
    Element locus = document.createElement("locus");
    document.appendChild(locus);
    for(Integer index = 0; index < list.size();index++){
      Element coodinate = document.createElement("coodinate");
      Point2D.Double test = list.get(index);
      Element x = document.createElement("x");
      x.appendChild(document.createTextNode(String.valueOf(test.x)));
      Element y = document.createElement("y");
      y.appendChild(document.createTextNode(String.valueOf(test.y)));
      coodinate.appendChild(x);
      coodinate.appendChild(y);
      locus.appendChild(coodinate);
    }
    // XMLファイルの作成
    File file = new File("Data.xml");
    write(file, document);

    return;
  }


  public static boolean write(File file, Document document) {

       // Transformerインスタンスの生成
       Transformer transformer = null;
       try {
            TransformerFactory transformerFactory = TransformerFactory
                      .newInstance();
            transformer = transformerFactory.newTransformer();
       } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return false;
       }
       // Transformerの設定
       transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //改行指定
       transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
       transformer.setOutputProperty("encoding", "Shift_JIS"); // エンコーディング

       // XMLファイルの作成
       try {
            transformer.transform(new DOMSource(document), new StreamResult(
                      file));
       } catch (TransformerException e) {
            e.printStackTrace();
            return false;
       }
       return true;
  }

  public void initiarize(){
    list.add(new Point2D.Double(1.0,2.0));
    list.add(new Point2D.Double(2.0,1.0));
  }

  public static void main(String[] args){
    SaveTest instance = new SaveTest();
    instance.initiarize();
    instance.run();
    return;
  }
}
