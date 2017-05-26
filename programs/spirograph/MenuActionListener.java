package spirograph;

import java.awt.Color;
/**
* メニューのアクションをスピログラフのコントローラに伝達するインターフェース
**/
public interface MenuActionListener {
  /**
  * アニメーションスタートメソッド
  **/
  public void animationStart();

  /**
  * アニメーションストップメソッド
  **/
  public void animationStop();

  /**
  * 色変更メソッド
  **/
  public void changedColor(Color aColor);

  /**
  * 内接外接変更メソッド
  **/
  public void changedPosition();

  /**
  * スピログラフの色を虹色にするメソッド
  **/
  public void changeColorRainbow();

  /**
  *　クリアメソッド
  **/
  public void clear();
}
