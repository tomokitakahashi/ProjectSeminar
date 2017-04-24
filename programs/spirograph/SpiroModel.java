package spirograph;

public class SpiroModel extends Model
{
  // スピログラフで描かれた軌跡モデル
  public SpiroLocusModel spiroLocusModel;

  // スピログラフのスパーギアモデル
  public SpurModel spurModel;

  // スピログラフのピニオンモデル
  public PinionModel pinionModel;

  private Boolean isStop;

  public SpiroModel()
  {
    super();
    return;
  }

  public void setStop()
  {
    isStop = true;
    return;
  }

  public void setStart()
  {
    isStop = false;
    return;
  }
  
}
