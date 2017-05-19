package spirograph;

import java.util.ArrayList;
import java.awt.Color;
import java.io.File;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

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

public class MenuModel extends Model
{
  /**
  * スピログラフのモデルを格納しておく
  **/
  private SpiroModel spiroModel;

  /**
  * メニューに表示させるタイトルリスト
  **/
  private ArrayList<String> buttonTitleList;

  /**
  * スピログラフの軌跡の色を保持しておく
  **/
  private Color selectedColor;

  /**
  * MenuModel のコンストラクタ
  * @param aSpiroModel スピログラフのモデル
  **/
  public MenuModel(SpiroModel aSpiroModel)
  {
    super();
    selectedColor = Color.black;
    spiroModel = aSpiroModel;
    spiroModel.setMenuModel(this);
    buttonTitleList = new ArrayList<String>() {
      {
        add("Start");
        add("Stop");
        add("Save");
        add("Load");
        add("Clear");
        add("Position");
      }
    };
    return;
  }

  /**
  * スピログラフの選択されている色を格納する
  * @param aColor 選択色
  **/
  public void setSelectedColor(Color aColor)
  {
    selectedColor = aColor;
    return;
  }

  /**
  * 選択されている色を応答する
  **/
  public Color getSelectedColor()
  {
    return selectedColor;
  }

  public boolean showDialogEnabled()
  {
    return spiroModel.isStop();
  }

  /**
  * 表示するボタンのタイトルリストを応答する
  **/
  public ArrayList<String> getButtonTitleList()
  {
    return buttonTitleList;
  }

  /**
  * セーブ指示メソッド
  * @param aFileName ファイル名
  **/
  public void save(File aFile)
  {
    SpurModel spurModel = spiroModel.getSpurModel();
    PinionModel pinionModel = spiroModel.getPinionModel();

    Document document = this.createDocument();
    Element spirograph = document.createElement("spirograph");
    document.appendChild(spirograph);

    // SpiroModel to XML
    Element spiro = document.createElement("spiroModel");
    spirograph.appendChild(spiro);
    Element axisDegree = document.createElement("axisDegree");
    axisDegree.appendChild(document.createTextNode(String.valueOf(spiroModel.degree())));
    spiro.appendChild(axisDegree);
    Element gearDistance = document.createElement("gearDistance");
    gearDistance.appendChild(document.createTextNode(String.valueOf(spiroModel.gearDistance())));
    spiro.appendChild(gearDistance);

    // SpurModel to XML
    Element spur = document.createElement("spurModel");
    spirograph.appendChild(spur);
    this.createGearXML(document,spur,spurModel);

    // PinionModel to XML
    Element pinion = document.createElement("pinionModel");
    spirograph.appendChild(pinion);
    this.createGearXML(document,pinion,pinionModel);

    // Element pencilCoodinate = document.createElement("pencilCoodinate");
    // Element pencilX = document.createElement("x");
    // pencilX.appendChild(pinionModel.pencilCoo);
    // Element pencilY = document.createElement("y");



    // SpiroLocusModel to XML
    this.createLocusXML(document,spirograph);
    write(aFile, document);
    return;
  }


  /**
  * 描画済みの軌跡データのXMLを生成するメソッド
  * @param aDocument ドキュメント
  * @param parent 親ノードの要素
  **/
  private void createLocusXML(Document aDocument,Element parent)
  {
    ArrayList<Point2D.Double> locusList = spiroModel.getSpiroLocusModel().locusList();

    Element locus = aDocument.createElement("locus");
    parent.appendChild(locus);
    for(Integer index = 0; index < locusList.size(); index++)
    {
      Element coodinate = aDocument.createElement("coodinate");
      Point2D.Double locusCoodinate = locusList.get(index);
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
  * ファイルロード指示メソッド
  * @param aFileName ファイル名
  **/
  public void load(String aFileName)
  {

    return;
  }

  /**
  * MARK: 以下 XMLに関連する操作
  **/


  /**
  * ドキュメントインスタンスの生成
  * MEMO: 例外処理のため別メソッドにしている
  **/
  private Document createDocument()
  {
    DocumentBuilder documentBuilder = null;
    try {
      documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch(ParserConfigurationException anException)
    {
      anException.printStackTrace();
    }
    return documentBuilder.newDocument();
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
