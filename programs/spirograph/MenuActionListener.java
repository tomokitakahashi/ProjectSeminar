package spirograph;

import java.awt.Color;
/**
* メニューのアクションをスピログラフのコントローラに伝達するインターフェース
**/
public interface MenuActionListener {
  /**
  * アニメーションスタートメソッド
  * 良好（2017年5月20日）
  */
  public void animationStart();

  /**
  * アニメーションストップメソッド
  * 良好（2017年5月20日）
  */
  public void animationStop();

  /**
  * 色変更メソッド
  * 良好（2017年5月20日）
  * @param aColor カラー
  */
  public void changedColor(Color aColor);

  /**
  * 内接外接変更メソッド
  * 良好（2017年5月20日）
  */
  public void changedPosition();

  /**
  * スピログラフの色を虹色にするメソッド
  * 良好（2017年5月20日）
  */
  public void changeColorRainbow();

  /**
  * クリアメソッド
  * 良好（2017年5月20日）  
  */
  public void clear();
}
