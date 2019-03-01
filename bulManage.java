package shoot;

import java.awt.Toolkit;

public class bulManage
implements Runnable
{
private boolean fire = false;
private int bullCount;
bullet[] bul;
Thread[] bulT;
int select = 0;
private int enemyX = 0;
private int enemyY = 0;

public bulManage(int bullCount) {
  this.bullCount = bullCount;

  this.bul = new bullet[bullCount];
  this.bulT = new Thread[bullCount];

  for (int i = 0; i < this.bullCount; i++) {
    this.bul[i] = new bullet(0, 0, 0);
    this.bulT[i] = new Thread(this.bul[i]);

    this.select = 0;

    this.bul[i].setX(0);
    this.bul[i].setY(0);
    this.bul[i].bulImg = Toolkit.getDefaultToolkit().getImage("img/embul1.gif");

    this.bulT[i].start();
  }
}

public void run() { int x = 1;
  boolean check = false;
  while (true) {
    if (isFire()) {
      for (int i = 0; i < this.bullCount; i++) {
        if ((getEnemyX() < -10) || (getEnemyX() > 700) || (getEnemyY() < 0) || (getEnemyY() > 600)) {
          setFire(false);
        }
        if (this.select == 0) {
          sleep(1500);

          this.bul[i].setX(getEnemyX());
          this.bul[i].setY(getEnemyY());
          this.bul[i].setSpeedX(0);
          this.bul[i].setSpeedY(6);
          this.bul[i].setVisible(true);
        }
        else if (this.select == 1) {
          sleep(500);
          this.bul[i].setX(getEnemyX());
          this.bul[i].setY(getEnemyY());
          this.bul[i].setSpeedX(x *= -1);
          this.bul[i].setSpeedY(5);
          this.bul[i].setVisible(true);
        }
        else if (this.select == 2) {
          sleep(500);
          this.bul[i].setX(getEnemyX());
          this.bul[i].setY(getEnemyY());
          this.bul[i].setSpeedX(1);
          this.bul[i].setSpeedY(5);
          this.bul[i].setVisible(true);
        }
        else if (this.select == -2) {
          sleep(500);
          this.bul[i].setX(getEnemyX());
          this.bul[i].setY(getEnemyY());
          this.bul[i].setSpeedX(-1);
          this.bul[i].setSpeedY(5);
          this.bul[i].setVisible(true);
        }
        else if (this.select == 3) {
          sleep(500);
          this.bul[i].setX(getEnemyX());
          this.bul[i].setY(getEnemyY());
          this.bul[i].setSpeedX(x *= -2);
          this.bul[i].setSpeedY(5);
          this.bul[i].setVisible(true);
        }
        else if (this.select == 4) {
          sleep(500);
          this.bul[i].setX(getEnemyX());
          this.bul[i].setY(getEnemyY());
          this.bul[i].setSpeedX(x = (int)(x * -(Math.random() * 3.0D)));
          this.bul[i].setSpeedY(5);
          this.bul[i].setVisible(true);
        }
        else if (this.select == 5) {
          sleep(120);
          this.bul[i].setX(getEnemyX());
          this.bul[i].setY(getEnemyY());
          this.bul[i].setSpeedX(x *= -1);
          this.bul[i].setSpeedY(5);
          this.bul[i].setVisible(true);
          check = true;
          setFire(true);
        }
      }
    }
    sleep(15);
  } }

public void setBullCount(int bullCount) {
  this.bullCount = bullCount;
}
public int getBullCount() {
  return this.bullCount;
}

public void sleep(int i) {
  try {
    Thread.sleep(i); } catch (InterruptedException e) {
    e.printStackTrace();
  }
}
public void setEnemyX(int enemyX) { this.enemyX = enemyX; }

public int getEnemyX() {
  return this.enemyX;
}
public void setEnemyY(int enemyY) {
  this.enemyY = enemyY;
}
public int getEnemyY() {
  return this.enemyY;
}
public void setFire(boolean fire) {
  this.fire = fire;
}
public boolean isFire() {
  return this.fire;
}
}