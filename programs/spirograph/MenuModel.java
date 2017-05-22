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
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MenuModel extends Model
{
  /**
  * スピログラフのモデルを格納しておく
  **/
  private SpiroModel spiroModel;

  /**
  * ファイル保存用クラス
  **/
  private SpiroFile spiroFile;

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
    spiroFile = new SpiroFile(this);
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
    spiroFile.save(spiroModel,aFile);
    return;
  }

  public void load(File aFile)
  {
    spiroModel.isLoading = true;

    try {
      SpiroModel newModel = spiroFile.load(spiroModel,aFile);
      spiroModel.getSpiroView().loadSpiroModel(newModel);
    } catch(Exception anException)
    {

    }
    return;
  }

}
