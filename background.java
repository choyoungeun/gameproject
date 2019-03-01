package shoot;

import java.awt.Image;

public class background
implements Runnable
{
private int x;
private int y;
private boolean check;
public Image bgImg;

background(int x, int y)
{
  setX(x);
  setY(y);
  setCheck(true);
}

public void setX(int x) {
  this.x = x;
}

public int getX() {
  return this.x;
}

public void setY(int y) {
  this.y = y;
}

public int getY() {
  return this.y;
}
public void incY(int y) {
  this.y += y;
}

public void run() {
  while (this.check) {
    incY(1);
    if (getY() > 650)
      setY(-5790);
    try
    {
      Thread.sleep(15L); } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public void setCheck(boolean check) { this.check = check; }

public boolean isCheck()
{
  return this.check;
}
}