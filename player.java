package shoot;

import java.awt.Image;

public class player extends plane
{
  public Image playerImg;
  private int life = 1;

  public player(int x, int y) {
    super(x, y);
    super.setSpeed(5);
  }

  public void setLife(int life)
  {
    this.life = life;
  }

  public int getLife() {
    return this.life;
  }
}