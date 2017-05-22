package spirograph;

import java.lang.Double;
import java.io.IOException;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.awt.Color;
import java.io.File;
import java.awt.geom.Point2D;


public class SpiroFile extends Object {

  private MenuModel menuModel;

  public SpiroFile(MenuModel aMenuModel)
  {
    menuModel = aMenuModel;
    return;
  }

  public void save(SpiroModel aSpiroModel,File aFile)
  {
    SpurModel spurModel = aSpiroModel.getSpurModel();
    PinionModel pinionModel = aSpiroModel.getPinionModel();

    Document document = this.createDocumentBuilder().newDocument();
    Element spirograph = document.createElement("spirograph");
    document.appendChild(spirograph);

    // SpiroModel to XML
    Element spiro = document.createElement("spiroModel");
    spirograph.appendChild(spiro);
    Element axisDegree = document.createElement("axisDegree");
    axisDegree.appendChild(document.createTextNode(String.valueOf(aSpiroModel.degree())));
    spiro.appendChild(axisDegree);
    Element gearDistance = document.createElement("gearDistance");
    gearDistance.appendChild(document.createTextNode(String.valueOf(aSpiroModel.gearDistance())));
    spiro.appendChild(gearDistance);

    // SpurModel to XML
    Element spur = document.createElement("spurModel");
    spirograph.appendChild(spur);
    this.createGearXML(document,spur,spurModel);

    // PinionModel to XML
    Element pinion = document.createElement("pinionModel");
    spirograph.appendChild(pinion);
    this.createGearXML(document,pinion,pinionModel);

    Element pencilCoodinate = document.createElement("pencilCoodinate");
    Element pencilX = document.createElement("x");
    pencilX.appendChild(document.createTextNode(String.valueOf(pinionModel.pencilCoodinate().x)));
    pencilCoodinate.appendChild(pencilX);
    Element pencilY = document.createElement("y");
    pencilY.appendChild(document.createTextNode(String.valueOf(pinionModel.pencilCoodinate().y)));
    pencilCoodinate.appendChild(pencilY);
    pinion.appendChild(pencilCoodinate);


    // SpiroLocusModel to XML
    this.createLocusXML(document,spirograph,aSpiroModel.getSpiroLocusModel().locusList());
    this.write(aFile, document);
    return;
  }

  /**
  * ファイル読み込み用
  * @param aSpiroModel 現在使われているSpiroModel
  * @param aFile 選択されたファイル
  **/

  public void load(SpiroModel aSpiroModel,File aFile) throws SAXException, IOException, ParserConfigurationException
  {
		Document document = this.createDocumentBuilder().parse(aFile.getPath());
    Element root = document.getDocumentElement();
    NodeList children = root.getChildNodes();

    for(Integer index = 0;index < children.getLength();index++)
    {
      Node childNode = children.item(index);
      if (childNode.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element)childNode;
        if(element.getNodeName().equals("spiroModel"))
        {
          this.loadSpiroModel(aSpiroModel,childNode);
        } else if(element.getNodeName().equals("spurModel"))
        {
          this.loadGearModel(aSpiroModel.getSpurModel(),childNode);
        }  else if(element.getNodeName().equals("pinionModel"))
        {
          this.loadGearModel(aSpiroModel.getPinionModel(),childNode);
        }  else if(element.getNodeName().equals("locus"))
        {
          this.loadLocusModel(aSpiroModel.getSpiroLocusModel(),childNode);
        }
      }
    }
    return;
  }

  private void loadLocusModel(SpiroLocusModel aSpiroLocusModel,Node aNode)
  {
    aSpiroLocusModel.clear();
    NodeList list = aNode.getChildNodes();
    for(Integer index = 0;index < list.getLength();index++)
    {
      Node childNode = list.item(index);
      if(childNode.getNodeType() == Node.ELEMENT_NODE)
      {
        if(childNode.getNodeName().equals("coodinate"))
        {
          aSpiroLocusModel.locusList().add(this.loadCoodinate(childNode));
        }
      }
    }
    return;
  }


  private void loadSpiroModel(SpiroModel aSpiroModel,Node aNode)
  {
    NodeList aNodeList = aNode.getChildNodes();
    for(Integer index = 0; index < aNodeList.getLength();index ++)
    {
      Node childNode = aNodeList.item(index);
      if(childNode.getNodeType() == Node.ELEMENT_NODE)
      {
        if(childNode.getNodeName().equals("axisDegree"))
        {
          String text = childNode.getNodeValue();
          System.out.println(text);
          aSpiroModel.axisDegree(Double.valueOf(text));
        } else if(childNode.getNodeName().equals("gearDistance"))
        {
          aSpiroModel.gearDistance(Double.valueOf(childNode.getNodeValue()));
        }
      }
    }
    return;
  }

  private void loadGearModel(GearModel aGearModel,Node aNode)
  {
    NodeList aNodeList = aNode.getChildNodes();
    for(Integer index = 0; index < aNodeList.getLength();index ++)
    {
      Node childNode = aNodeList.item(index);
      if(childNode.getNodeType() == Node.ELEMENT_NODE)
      {
        if(childNode.getNodeName().equals("centerCoodinate"))
        {
          aGearModel.centerCoodinate(this.loadCoodinate(childNode));
        } else if(childNode.getNodeName().equals("radius"))
        {
          aGearModel.radius(Double.valueOf(childNode.getNodeValue()));
        } else if(childNode.getNodeName().equals("tapAreaCoodinateList"))
        {
          NodeList list = childNode.getChildNodes();
          aGearModel.tapAreaCoodinateList().set(0,this.loadCoodinate(list.item(0)));
          aGearModel.tapAreaCoodinateList().set(1,this.loadCoodinate(list.item(1)));
          aGearModel.tapAreaCoodinateList().set(2,this.loadCoodinate(list.item(2)));
          aGearModel.tapAreaCoodinateList().set(3,this.loadCoodinate(list.item(3)));
        } else if(childNode.getNodeName().equals("pencilCoodinate"))
        {
          PinionModel pinionModel = (PinionModel)aGearModel;
          pinionModel.pencilCoodinate(this.loadCoodinate(childNode));
        }
      }
    }
    return;
  }

  private Point2D.Double loadCoodinate(Node aNode)
  {
    Point2D.Double coodinate = new Point2D.Double(Double.valueOf(aNode.getChildNodes().item(0).getNodeValue()),Double.valueOf(aNode.getChildNodes().item(1).getNodeValue()));
    return coodinate;
  }


  /**
  * ドキュメントインスタンスの生成
  * MEMO: 例外処理のため別メソッドにしている
  **/
  private DocumentBuilder createDocumentBuilder()
  {
    DocumentBuilder documentBuilder = null;
    try {
      documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch(ParserConfigurationException anException)
    {
      anException.printStackTrace();
    }
    return documentBuilder;
  }

  /**
  * ギアのデータのXMLを生成するメソッド
  * @param aDocument ドキュメント
  * @param gearElement ギア要素
  * @param aGearModel 格納したいギアのモデル
  **/
  private void createGearXML(Document aDocument,Element gearElement,GearModel aGearModel)
  {
    // For CenterCoodinate
    Element centerCoodinate = aDocument.createElement("centerCoodinate");
    gearElement.appendChild(centerCoodinate);
    Element centerX = aDocument.createElement("x");
    centerX.appendChild(aDocument.createTextNode(String.valueOf(aGearModel.centerCoodinate().x)));
    Element centerY = aDocument.createElement("y");
    centerY.appendChild(aDocument.createTextNode(String.valueOf(aGearModel.centerCoodinate().y)));
    centerCoodinate.appendChild(centerX);
    centerCoodinate.appendChild(centerY);

    // For Radius
    Element radius = aDocument.createElement("radius");
    radius.appendChild(aDocument.createTextNode(String.valueOf(aGearModel.radius())));
    gearElement.appendChild(radius);

    // For TapArea
    Element tapAreaCoodinateList = aDocument.createElement("tapAreaCoodinateList");
    gearElement.appendChild(tapAreaCoodinateList);
    for(Integer index = 0; index < aGearModel.tapAreaCoodinateList().size();index++)
    {
      Element coodinate = aDocument.createElement("coodinate");
      Point2D.Double areaCoodinate = aGearModel.tapAreaCoodinateList().get(index);
      Element x = aDocument.createElement("x");
      x.appendChild(aDocument.createTextNode(String.valueOf(areaCoodinate.x)));
      Element y = aDocument.createElement("y");
      y.appendChild(aDocument.createTextNode(String.valueOf(areaCoodinate.y)));
      coodinate.appendChild(x);
      coodinate.appendChild(y);
      tapAreaCoodinateList.appendChild(coodinate);
    }
    return;
  }

  /**
  * 描画済みの軌跡データのXMLを生成するメソッド
  * @param aDocument ドキュメント
  * @param parent 親ノードの要素
  **/
  private void createLocusXML(Document aDocument,Element parent, ArrayList<Point2D.Double> aLocusList)
  {

    Element locus = aDocument.createElement("locus");
    parent.appendChild(locus);
    for(Integer index = 0; index < aLocusList.size(); index++)
    {
      Element coodinate = aDocument.createElement("coodinate");
      Point2D.Double locusCoodinate = aLocusList.get(index);
      Element x = aDocument.createElement("x");
      x.appendChild(aDocument.createTextNode(String.valueOf(locusCoodinate.x)));
      Element y = aDocument.createElement("y");
      y.appendChild(aDocument.createTextNode(String.valueOf(locusCoodinate.y)));
      coodinate.appendChild(x);
      coodinate.appendChild(y);
      locus.appendChild(coodinate);
    }
    return;
  }

  /**
  * XMLファイルに出力するメソッド
  * @param file 書き込みファイル
  * @param document 生成ドキュメント
  **/
  private static boolean write(File file, Document document) {
     // Transformerインスタンスの生成
     Transformer transformer = null;
     try {
          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          transformer = transformerFactory.newTransformer();
     } catch (TransformerConfigurationException anException) {
          anException.printStackTrace();
          return false;
     }
     // Transformerの設定
     transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //改行指定
     transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // インデント設定指定
     transformer.setOutputProperty("encoding", "Shift_JIS"); // エンコーディング
     // XMLファイルの作成
     try {
          transformer.transform(new DOMSource(document), new StreamResult(file));
     } catch (TransformerException anException) {
          anException.printStackTrace();
          return false;
     }
     return true;
   }

}
