package shoot;

import java.awt.Image;

public class bullet
implements Runnable
{
private int imgLeft = 0;
private int imgTop = 0;
private int imgRight = 10;
private int imgBottom = 16;
private int x;
private int y;
private int speedX = 0;
private int speedY = 0;
private boolean check = true;
private boolean visible = false;
public Image bulImg;
private int attackDamage = 1;
private int bullet_speed = -15;

public boolean s = true;

bullet(int x, int y, int speedY) {
  setX(x);
  setY(y);
  setSpeedY(speedY);
}

public void run()
{
  while (true)
  {
    if ((getY() < -10) || (getY() > 700) || (getX() < 0) || (getX() > 600)) {
      setVisible(false);
    }

    incY(this.speedY);
    incX(this.speedX);
    try {
      Thread.sleep(30L); } catch (InterruptedException localInterruptedException) {
    }
  }
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

public void incX(int vX) {
  this.x += vX;
}
public void incY(int vY) {
  this.y += vY;
}
void setSpeedY(int speedY) {
  this.speedY = speedY;
}
void setSpeedX(int speedX) {
  this.speedX = speedX;
}

public void setImgBottom(int imgBottom) {
  this.imgBottom = imgBottom;
}

public int getImgBottom() {
  return this.imgBottom;
}

public void setImgRight(int imgRight) {
  this.imgRight = imgRight;
}

public int getImgRight() {
  return this.imgRight;
}

public void setImgTop(int imgTop) {
  this.imgTop = imgTop;
}

public int getImgTop() {
  return this.imgTop;
}

public void setImgLeft(int imgLeft) {
  this.imgLeft = imgLeft;
}

public int getImgLeft() {
  return this.imgLeft;
}

public void setAttackDamage(int attackDamage) {
  this.attackDamage = attackDamage;
}

public int getAttackDamage() {
  return this.attackDamage;
}

public void setBullet_speed(int bullet_speed) {
  this.bullet_speed = bullet_speed;
}

public int getBullet_speed() {
  return this.bullet_speed;
}

public void setVisible(boolean visible) {
  this.visible = visible;
}

public boolean isVisible() {
  return this.visible;
}

public void setState(int x2, int y2) {
  setX(x2);
  setY(y2);
  setVisible(true);
}

public void setCheck(boolean check) {
  this.check = check;
}

public boolean isCheck() {
  return this.check;
}
}