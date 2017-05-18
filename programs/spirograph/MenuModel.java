package spirograph;

import java.util.ArrayList;
import java.awt.Color;

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
  public void save(String aFileName)
  {

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
}
