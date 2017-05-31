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

/**
* メニュー用のモデル
*/
public class MenuModel extends Model
{
  /**
  * スピログラフのモデルを格納しておく
  * 良好（2017年5月20日）
  */
  private SpiroModel spiroModel;

  /**
  * ファイル保存用クラス
  * 良好（2017年5月20日）
  */
  private SpiroFile spiroFile;

  /**
  * スピログラフの軌跡の色を保持しておく
  * 良好（2017年5月20日）
  */
  private Color selectedColor;

  /**
  * MenuModel のコンストラクタ
  * 良好（2017年5月20日）
  * @param aSpiroModel スピログラフのモデル
  */
  public MenuModel(SpiroModel aSpiroModel)
  {
    super();
    selectedColor = Color.black;
    spiroModel = aSpiroModel;
    spiroFile = new SpiroFile();
    return;
  }

  /**
  * スピログラフの選択されている色を格納する
  * 良好（2017年5月20日）
  * @param aColor 選択色
  */
  public void setSelectedColor(Color aColor)
  {
    selectedColor = aColor;
    return;
  }

  /**
  * 選択されている色を応答する
  * 良好（2017年5月20日）
  */
  public Color getSelectedColor()
  {
    return selectedColor;
  }

  /**
  * ダイアログが表示できるかを応答する
  * 良好（2017年5月20日）
  */
  public Boolean showDialogEnabled()
  {
    return spiroModel.isStop();
  }

  /**
  * セーブ指示メソッド
  * 良好（2017年5月20日）
  * @param aFileName ファイル名
  */
  public void save(File aFile)
  {
    spiroFile.save(spiroModel,aFile);
    return;
  }

  /**
  * ロード指示メソッド
  * 良好（2017年5月20日）
  * @param aFile ロードするファイル
  */
  public void load(File aFile)
  {
    try {
      SpiroModel newModel = spiroFile.load(spiroModel,aFile);
      spiroModel.getSpiroView().loadSpiroModel(newModel);
    } catch(Exception anException)
    {
      anException.printStackTrace();
    }
    return;
  }

}
