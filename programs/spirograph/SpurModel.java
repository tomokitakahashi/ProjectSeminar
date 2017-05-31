package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;

/**
* スパーギアのモデル
*/
public class SpurModel extends GearModel
{
  /**
  * SpurMddelのコンストラクタ
  * 良好（2017年5月20日）
  * @param aCenterCoodinate 中心座標
  * @param aRadius 半径
  */
  public SpurModel(Point2D.Double aCenterCoodinate,double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    return;
  }

  /**
  * データのリセッター
  * 良好（2017年5月20日）
  */
  @Override
  public void dataReset()
  {
    previousRadius = radius;
    return;
  }

  /**
  * タップしたエリアが有効かどうかを判断するメソッド
  * 良好（2017年5月20日）
  * @param aPoint タップ座標
  */
  @Override
  public void judgePressArea(Point aPoint)
  {
    radiusAbjustEnabled = this.validateAroundTapArea(aPoint);
    centerMoveEnabled = this.validateCenterTapArea(aPoint);
    return;
  }
}
