package spirograph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.Point;

public class SpurModel extends GearModel
{
  // SpurMddelのコンストラクタ
  public SpurModel(Point2D.Double aCenterCoodinate,double aRadius)
  {
    super(aCenterCoodinate,aRadius);
    return;
  }

  // データのリセッター
  @Override
  public void dataReset()
  {
    previousRadius = radius;
    return;
  }

  // タップしたエリアが有効かどうかを判断するメソッド
  @Override
  public void judgePressArea(Point aPoint)
  {
    previousCenterCoodinate = centerCoodinate;
    double tapRange = SpiroConstruct.TAP_AREA_RADIUS*2*SpiroConstruct.TAP_AREA_RADIUS*2;
    double centerTapPoint = (centerCoodinate.x - aPoint.x) * (centerCoodinate.x - aPoint.x) + (centerCoodinate.y - aPoint.y) * (centerCoodinate.y - aPoint.y);
    for(Integer index = 0; index < tapAreaCoodinateList.size(); index++)
    {
      Point2D.Double coodinate = tapAreaCoodinateList.get(index);
      double tapPoint = (coodinate.x - aPoint.x) * (coodinate.x - aPoint.x) + (coodinate.y - aPoint.y) * (coodinate.y - aPoint.y);
      if(tapPoint <= tapRange)
      {
        radiusAbjustEnabled = true;
        return;
      }
    }
    if(centerTapPoint <= tapRange)
    {
      centerMoveEnabled = true;
    }
    return;
  }
}
