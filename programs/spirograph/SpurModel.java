package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;

public class SpurModel extends GearModel
{
  /**
  * SpurMddelのコンストラクタ
  * @param aCenterCoodinate 中心座標
  * @param aRadius 半径
  **/
  public SpurModel(Point2D.Double aCenterCoodinate,double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    return;
  }

  /**
  * データのリセッター
  **/
  @Override
  public void dataReset()
  {
    previousRadius = radius;
    return;
  }

  /**
  * タップしたエリアが有効かどうかを判断するメソッド
  * @param aPoint タップ座標
  **/
  @Override
  public void judgePressArea(Point aPoint)
  {
    previousCenterCoodinate = centerCoodinate;
    radiusAbjustEnabled = this.validateAroundTapArea(aPoint);
    centerMoveEnabled = this.validateCenterTapArea(aPoint);
    return;
  }
}
