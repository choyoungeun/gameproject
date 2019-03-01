package shoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JPanel;

public class GameScreen extends JPanel
  implements Runnable
{
  private int Width;
  private int Height;
  static int BULLET_MAX = 80;//총알 횟수
  static int ENEMY_MAX = 100;//적 출현 횟수
  private int gameScore = 0;//게임점수
  private int cnt = 1;//적을 처치한 수
  
  Image expoImg;
  Image gameOver;
  Image gameStart;
  Image gameClear;
  Image bossCaption;
  Image minibar;
  boolean checkOver = false;
  boolean checkStart = false;
  boolean checkClear = false;
  boolean checkCaption = false;

  Font f = new Font("", 1, 20);
  background bg1;
  background bg2;
  Image off;
  Graphics offG;
  player p;
  double vX;
  double vY;
  bullet[] bul = new bullet[BULLET_MAX];
  enemyPlane boss;
  Thread bossT;
  bulManage bossBulM;
  Thread bossBulMgT;
  enemyPlane[] em2 = new enemyPlane[ENEMY_MAX];
  Thread[] enemy2T = new Thread[ENEMY_MAX];
  bulManage[] bulM = new bulManage[ENEMY_MAX];
  Thread[] bulMgT = new Thread[ENEMY_MAX];
  Thread main;
  Thread bg1T;
  Thread bg2T;
  Thread[] shootThread = new Thread[100];
private Object bossS;

  public GameScreen() {
    this.Width = getWidth();
    this.Height = getHeight();

    setUnit();

    this.main = new Thread(this);
    this.main.start();
  }

  public void init() {
    this.off = createImage(getWidth(), getHeight());
    this.offG = this.off.getGraphics();
  }

  public void run() {
    while (true) {
      repaint();
      try {
        Thread.sleep(15L); } catch (InterruptedException e) {
        e.printStackTrace(); } 
    }
  }

  public void paint(Graphics g) { super.paint(g);
    g.drawImage(this.off, 0, 0, getWidth(), getHeight(), this);
    this.offG.fillRect(0, 0, this.Width, this.Height);

    setPaint(g);

    this.offG.drawImage(this.off, 0, 0, this);

    manage(); }

  public void manage()
  {
    for (int i = 0; i < ENEMY_MAX; i++) {
      if (this.em2[i].isVisible()) {
        this.bulM[i].setEnemyX(this.em2[i].getX() + 25);
        this.bulM[i].setEnemyY(this.em2[i].getY() + 5);
      }
      else if (!this.em2[i].isVisible()) {
        this.bulM[i].setFire(false);
        this.bulM[i].setEnemyX(-50);
        this.bulM[i].setEnemyY(-50);
      }
    }
    if (this.boss.isVisible()) {
      this.bossBulM.setEnemyX(this.boss.getX() + 60);
      this.bossBulM.setEnemyY(this.boss.getY() + 50);
    }
    else if (!this.boss.isVisible()) {
      this.bossBulM.setEnemyX(-100);
      this.bossBulM.setEnemyY(-100);
    }
  }

  public void update(Graphics g) {
    paint(g);
  }

  public void setUnit()
  {
    this.bg1 = new background(-25, -5130);
    this.bg2 = new background(-25, -5800);
    this.bg1.bgImg = Toolkit.getDefaultToolkit().getImage("img/background.jpg");
    this.bg2.bgImg = this.bg1.bgImg;

    this.expoImg = Toolkit.getDefaultToolkit().getImage("img/expo.gif");
    this.gameOver = Toolkit.getDefaultToolkit().getImage("img/gameover.gif");
    this.gameStart = Toolkit.getDefaultToolkit().getImage("img/gamestart.gif");
    this.gameClear = Toolkit.getDefaultToolkit().getImage("img/gameclear.gif");
    this.bossCaption = Toolkit.getDefaultToolkit().getImage("img/caption.gif");
    this.minibar = Toolkit.getDefaultToolkit().getImage("img/minip.gif");

    this.p = new player(200, 500);
    this.p.playerImg = Toolkit.getDefaultToolkit().getImage("img/p1.gif");
    this.p.setHp(10);
    this.p.setImgRight(40);
    this.p.setImgBottom(40);
    this.p.setVisible(true);

    for (int i = 0; i < BULLET_MAX; i++) {
      this.bul[i] = new bullet(this.p.getX() + 15, this.p.getY(), 0);
      this.bul[i].bulImg = Toolkit.getDefaultToolkit().getImage("img/bul1.gif");

      this.shootThread[i] = new Thread(this.bul[i]);
      this.shootThread[i].start();
    }

    this.boss = new enemyPlane(0, 0);
    this.boss.setSpeed(2);
    this.boss.setHp(-10);
    this.boss.enemyImg = Toolkit.getDefaultToolkit().getImage("img/boss.gif");
    this.boss.setImgRight(100);
    this.boss.setImgBottom(130);

    this.bossBulM = new bulManage(100);
    this.bossBulM.setEnemyX(this.boss.getX());
    this.bossBulM.setEnemyY(this.boss.getY());

    for (int i = 0; i < ENEMY_MAX; i++) {
      this.em2[i] = new enemyPlane(0, 0);
      this.em2[i].setSpeed(2);
      this.em2[i].setImgRight(40);
      this.em2[i].setImgBottom(40);
      this.em2[i].setHp(-4);

      if (i <= 20) {
        this.em2[i].enemyImg = Toolkit.getDefaultToolkit().getImage("img/em.gif");
      }
      else if ((i < 40) && (i > 20)) {
        this.em2[i].enemyImg = Toolkit.getDefaultToolkit().getImage("img/em.gif");
      }
      else if ((40 <= i) && (i < 50)) {
        this.em2[i].enemyImg = Toolkit.getDefaultToolkit().getImage("img/em.gif");
      }
      else if ((50 <= i) && (i < 55)) {
        this.em2[i].enemyImg = Toolkit.getDefaultToolkit().getImage("img/mid.gif");
      }
      else if ((i <= 55) && (i < ENEMY_MAX)) {
        this.em2[i].enemyImg = Toolkit.getDefaultToolkit().getImage("img/em.gif");
      }
    }

    for (int i = 0; i < ENEMY_MAX; i++) {
      if ((i >= 50) && (i < 55))
        this.bulM[i] = new bulManage(50);
      else {
        this.bulM[i] = new bulManage(10);
      }
      this.bulM[i].setEnemyX(this.em2[i].getX());
      this.bulM[i].setEnemyY(this.em2[i].getY());
    }

    for (int i = 0; i < ENEMY_MAX; i++) {
      this.enemy2T[i] = new Thread(this.em2[i]);
      this.enemy2T[i].start();

      this.bulMgT[i] = new Thread(this.bulM[i]);
      this.bulMgT[i].start();
    }

    this.bossT = new Thread(this.boss);
    this.bossBulMgT = new Thread(this.bossBulM);

    this.bossT.start();
    this.bossBulMgT.start();

    this.bg1T = new Thread(this.bg1);
    this.bg2T = new Thread(this.bg2);
    this.bg1T.start();
    this.bg2T.start();

    this.p.setLife(1);
  }

  public void setPaint(Graphics g)
  {
    this.offG.drawImage(this.bg1.bgImg, this.bg1.getX(), this.bg1.getY(), this);
    this.offG.drawImage(this.bg2.bgImg, this.bg2.getX(), this.bg2.getY(), this);
    this.offG.setColor(Color.BLUE);
    this.offG.setFont(this.f);

    this.offG.drawString("Score : " + this.gameScore, 50, 50);

    this.offG.drawImage(this.minibar, 300, 594, this);

    for (int i = 0; i < this.p.getHp(); i++) {
      this.offG.setColor(new Color(1 + i * 5, 200 - i * 10, 255 - i * 20));
      this.offG.drawRect(329, 594, 101, 21);

      this.offG.setColor(new Color(10 + i * 3, 150 - i * 12, 255 - i * 9));
      this.offG.fillRect(330 + i * 10, 595, 10, 20);
    }

    if (this.checkOver) {
      this.offG.drawImage(this.gameOver, -30, 50, this);
    }
    if (this.checkStart) {
      this.offG.drawImage(this.gameStart, -30, 50, this);
    }
    if (this.checkClear) {
      this.offG.drawImage(this.gameClear, -30, 50, this);
    }
    if (this.checkCaption) {
      this.offG.drawImage(this.bossCaption, -30, 50, this);
    }

    if (this.p.isVisible()) {
      this.offG.drawImage(this.p.playerImg, this.p.getX(), this.p.getY(), this);
    }
    if (this.p.getX() < 0)
      this.p.setX(0);
    else if (this.p.getX() > 390)
      this.p.setX(390);
    else if (this.p.getY() < 0)
      this.p.setY(0);
    else if (this.p.getY() > 550) {
      this.p.setY(550);
    }
    if (this.p.getLife() == 1) {
      for (int i = 0; i < ENEMY_MAX; i++) {
        if ((this.em2[i].getX() < this.p.getX() + 15 + this.p.getImgRight() - 15) && 
          (this.p.getX() + 15 < this.em2[i].getX() + this.em2[i].getImgRight()) && 
          (this.em2[i].getY() < this.p.getY() + this.p.getImgBottom() - 15) && 
          (this.p.getY() + 15 < this.em2[i].getY() + this.em2[i].getImgBottom()))
        {
          this.p.setHp(this.p.getHp() - 1);
          break;
        }
      }

    }

    if (this.p.getHp() == 0) {
      this.p.setVisible(false);
      this.p.setHp(-10);

      this.checkOver = true;

      System.out.println("플레이어가 사망하셨습니다.");
    }

    for (int i = 0; i < ENEMY_MAX; i++) {
      for (int j = 0; j < this.bulM[i].getBullCount(); j++) {
        if (this.bulM[i].bul[j].isVisible())
        {
          if ((this.bulM[i].bul[j].getX() < this.p.getX() + this.p.getImgRight() - 15) && 
            (this.p.getX() + 15 < this.bulM[i].bul[j].getX() + this.bulM[i].bul[j].getImgRight()) && 
            (this.bulM[i].bul[j].getY() < this.p.getY() + this.p.getImgBottom() - 15) && 
            (this.p.getY() + 15 < this.bulM[i].bul[j].getY() + this.bulM[i].bul[j].getImgBottom()))
          {
            if (this.p.getHp() > 0) {
              this.bulM[i].bul[j].setVisible(false);

              this.p.setHp(this.p.getHp() - this.bulM[i].bul[j].getAttackDamage());
              System.out.println("플레이어 hp :" + this.p.getHp());
            }
          }
        }
      }
    }

    for (int j = 0; j < this.bossBulM.getBullCount(); j++) {
      if ((this.bossBulM.bul[j].isVisible()) && 
        (this.bossBulM.bul[j].getX() < this.p.getX() + this.p.getImgRight() - 15) && 
        (this.p.getX() + 15 < this.bossBulM.bul[j].getX() + this.bossBulM.bul[j].getImgRight()) && 
        (this.bossBulM.bul[j].getY() < this.p.getY() + this.p.getImgBottom() - 15) && 
        (this.p.getY() + 15 < this.bossBulM.bul[j].getY() + this.bossBulM.bul[j].getImgBottom()))
      {
        if (this.p.getHp() > 0) {
          this.bossBulM.bul[j].setVisible(false);

          this.p.setHp(this.p.getHp() - this.bossBulM.bul[j].getAttackDamage());
          System.out.println("플레이어 hp :" + this.p.getHp());
        }
      }

    }

    for (int i = 0; i < BULLET_MAX; i++) {
      if (!this.p.isVisible()) {
        this.bul[i].setVisible(false);
      }

      if (this.bul[i].isVisible()) {
        this.bul[i].incY(-10);
        
        if ((this.bul[i].getX() < this.boss.getX() + this.boss.getImgRight()) && 
          (this.boss.getX() < this.bul[i].getX() + this.bul[i].getImgRight()) && 
          (this.bul[i].getY() < this.boss.getY() + this.boss.getImgBottom()) && 
          (this.boss.getY() < this.bul[i].getY() + this.bul[i].getImgBottom()))
        {
          if (this.boss.getHp() > 0) {
            this.gameScore += 10;
            this.bul[i].setVisible(false);

            this.boss.setHp(this.boss.getHp() - this.bul[i].getAttackDamage());
          }
        }

        for (int j = 0; j < ENEMY_MAX; j++)
        {
          if ((this.bul[i].getX() < this.em2[j].getX() + this.em2[j].getImgRight()) && 
            (this.em2[j].getX() < this.bul[i].getX() + this.bul[i].getImgRight()) && 
            (this.bul[i].getY() < this.em2[j].getY() + this.em2[j].getImgBottom()) && 
            (this.em2[j].getY() < this.bul[i].getY() + this.bul[i].getImgBottom()))
          {
            if (this.em2[j].getHp() > 0) {
              this.gameScore += 10;
              this.bul[i].setVisible(false);
              this.em2[j].setHp(this.em2[j].getHp() - this.bul[i].getAttackDamage());

            }
          }
        }
      }

      if (this.bul[i].getY() < -5) {
        this.bul[i].setVisible(false);
      }
    }

    for (int i = 0; i < BULLET_MAX; i++) {
      if (this.bul[i].isVisible()) {
        this.offG.drawImage(this.bul[i].bulImg, this.bul[i].getX(), this.bul[i].getY(), this);
      }
    }
    for (int i = 0; i < ENEMY_MAX; i++) {
      for (int j = 0; j < this.bulM[i].getBullCount(); j++)
      {
        if (this.bulM[i].bul[j].isVisible()) {
          this.offG.drawImage(this.bulM[i].bul[j].bulImg, this.bulM[i].bul[j].getX(), this.bulM[i].bul[j].getY(), this);
        }
      }
    }

    for (int i = 0; i < this.bossBulM.getBullCount(); i++) {
      if (this.bossBulM.bul[i].isVisible()) {
        this.offG.drawImage(this.bossBulM.bul[i].bulImg, this.bossBulM.bul[i].getX(), this.bossBulM.bul[i].getY(), this);
      }
    }

    if (this.boss.isVisible()) {
      this.offG.drawImage(this.boss.enemyImg, this.boss.getX(), this.boss.getY(), this);
    }

    if (this.boss.getHp() == 0) {
      this.gameScore += 10000;
      this.boss.setVisible(false);
      this.boss.setHp(-10);

      this.checkClear = true;
    }

    for (int i = 0; i < ENEMY_MAX; i++) {
      if (this.em2[i].isVisible()) {
        this.offG.drawImage(this.em2[i].enemyImg, this.em2[i].getX(), this.em2[i].getY(), this);
      }

      if ((this.em2[i].getHp() <= 0) && (this.em2[i].getHp() > -3))
      {
        this.gameScore += 100;
        
        System.out.println("적을 "+cnt+"마리 처치했습니다.");
        cnt++;

        if (((this.em2[50].getHp() <= 0) && (this.em2[50].getHp() > -3)) || 
          ((this.em2[51].getHp() <= 0) && (this.em2[51].getHp() > -3)) || 
          ((this.em2[52].getHp() <= 0) && (this.em2[52].getHp() > -3)) || 
          ((this.em2[53].getHp() <= 0) && (this.em2[53].getHp() > -3)) || (
          (this.em2[54].getHp() <= 0) && (this.em2[54].getHp() > -3))) {
          this.gameScore += 1000;
          System.out.println("중보 소멸");
        }

        this.em2[i].setVisible(false);
        this.em2[i].setHp(-5);


        this.offG.drawImage(this.expoImg, this.em2[i].getX() - 20, this.em2[i].getY() - 30, this);
      }
    }
  }


  public void stage1()
  {
    int nomalHP = 4;


    this.checkStart = true;

    sleep(5000);
    this.checkStart = false;

    sleep(6000);

    for (int i = 0; i < 4; i++) {
      sleep(1000);
      this.em2[i].setState(0, 10, nomalHP);
      this.em2[i].setPath(0);
      this.bulM[i].setFire(true);
      this.bulM[(i + 4)].select = 1;

      sleep(500);
      this.em2[(i + 4)].setState(450, 10, nomalHP);
      this.em2[(i + 4)].setPath(0);
      this.bulM[(i + 4)].setFire(true);
      this.bulM[i].select = 1;
    }
    sleep(7000);

    for (int i = 8; i < 12; i++) {
      sleep(1000);
      this.em2[i].setState(100, -5, nomalHP);
      this.em2[i].setPath(1);
      this.em2[i].right = 1;
      this.bulM[i].setFire(true);
      this.bulM[i].select = 2;

      sleep(500);
      this.em2[(i + 5)].setState(350, -5, nomalHP);
      this.em2[(i + 5)].setPath(1);
      this.em2[(i + 5)].left = 1;
      this.bulM[(i + 5)].setFire(true);
      this.bulM[(i + 5)].select = -2;
    }

    sleep(6000);
    for (int i = 18; i < 23; i++) {
      sleep(900);
      this.em2[i].setState(-10, 130, nomalHP);
      this.em2[i].setPath(2);
      this.em2[i].right = 1;
      this.bulM[i].setFire(true);

      sleep(600);
      this.em2[(i + 5)].setState(500, 50, nomalHP);
      this.em2[(i + 5)].setPath(2);
      this.em2[(i + 5)].left = 1;
      this.bulM[(i + 5)].setFire(true);
    }

    this.em2[50].setState(50, -50, nomalHP + 50);
    this.em2[50].setPath(3);
    this.bulM[50].setFire(true);
    this.bulM[50].select = 2;

    sleep(1000);

    this.em2[51].setState(350, -50, nomalHP + 50);
    this.em2[51].setPath(3);
    this.bulM[51].setFire(true);
    this.bulM[51].select = -2;
    sleep(6000);

    this.em2[52].setState(100, -50, nomalHP + 50);
    this.em2[52].setPath(3);
    this.bulM[52].setFire(true);
    this.bulM[52].select = 2;
    sleep(1000);

    this.em2[53].setState(300, -50, nomalHP + 50);
    this.em2[53].setPath(3);
    this.bulM[53].setFire(true);
    this.bulM[53].select = -2;
    sleep(4000);

    this.em2[54].setState(200, -50, nomalHP + 50);
    this.em2[54].setPath(3);
    this.bulM[54].setFire(true);
    this.bulM[54].select = 1;

    sleep(3000);
    for (int i = 0; i < 7; i++) {
      sleep(900);
      this.em2[i].setState(-10, 150, nomalHP);
      this.em2[i].setPath(2);
      this.em2[i].right = 1;
      this.bulM[i].setFire(true);
      this.bulM[i].select = 1;

      sleep(600);
      this.em2[(i + 7)].setState(500, 50, nomalHP);
      this.em2[(i + 7)].setPath(2);
      this.em2[(i + 7)].left = 1;
      this.bulM[(i + 7)].setFire(true);
      this.bulM[(i + 7)].select = 1;
    }

    sleep(4000);
    for (int i = 37; i < 41; i++) {
      sleep(1000);
      this.em2[i].setState(100, -40, nomalHP);
      this.em2[i].setPath(4);
      this.bulM[i].setFire(true);
      this.bulM[i].select = 2;

      this.em2[(i - 20)].setState(-10, 50, nomalHP);
      this.em2[(i - 20)].setPath(2);
      this.em2[(i - 20)].right = 1;
      this.bulM[(i - 20)].setFire(true);

      sleep(500);
      this.em2[(i + 4)].setState(330, -40, nomalHP);
      this.em2[(i + 4)].setPath(4);
      this.bulM[(i + 4)].setFire(true);
      this.bulM[i].select = -2;

      this.em2[(i + 40)].setState(400, 50, nomalHP);
      this.em2[(i + 40)].setPath(2);
      this.em2[(i + 40)].left = 1;
      this.bulM[(i + 40)].setFire(true);
    }

    sleep(5000);

    for (int i = 8; i < 23; i++) {
      sleep(1500);

      this.em2[i].setState((int)(Math.random() * 350.0D) + 30, (int)(Math.random() * 300.0D), nomalHP);
      this.em2[i].setPath((int)(Math.random() * 6.0D) + 1);
      this.bulM[i].setFire(true);
      this.bulM[i].select = ((int)(Math.random() * 6.0D - 2.0D));

      int check = (int)(Math.random() * 1.0D);
      if (check == 0)
        this.em2[i].right = 1;
      if (check == 1) {
        this.em2[i].left = 1;
      }
    }
    sleep(7000);

    for (int i = 23; i < 31; i++) {
      this.em2[i].setState(-50, 150, nomalHP);
      this.em2[i].setPath(5);
      this.em2[i].right = 1;
      this.bulM[i].setFire(true);
      this.bulM[i].select = 3;

      this.em2[(i + 17)].setState(400, 200, nomalHP);
      this.em2[(i + 17)].setPath(5);
      this.em2[(i + 17)].left = 1;
      this.bulM[(i + 17)].setFire(true);
      this.bulM[(i + 17)].select = 3;
      sleep(1400);
    }
    sleep(3000);

    for (int i = 0; i < 10; i++) {
      this.em2[i].setState(250, -20, nomalHP);
      this.em2[i].setPath(6);
      this.bulM[i].setFire(true);
      this.bulM[i].select = 2;
      sleep(300);
    }

    sleep(2000);
    for (int i = 15; i < 26; i++) {
      this.em2[i].setState(10, -20, nomalHP);
      this.em2[i].setPath(6);
      this.bulM[i].setFire(true);
      this.bulM[i].select = -2;
      sleep(300);
    }

    this.em2[50].setState(50, -50, nomalHP + 50);
    this.em2[50].setPath(3);
    this.bulM[50].setFire(true);
    this.bulM[50].select = 2;
    sleep(1000);

    this.em2[51].setState(400, -50, nomalHP + 50);
    this.em2[51].setPath(3);
    this.bulM[51].setFire(true);
    this.bulM[51].select = -2;
    sleep(6000);

    this.em2[52].setState(100, -50, nomalHP + 50);
    this.em2[52].setPath(3);
    this.bulM[52].setFire(true);
    this.bulM[52].select = 2;
    sleep(1000);

    this.em2[53].setState(350, -50, nomalHP + 50);
    this.em2[53].setPath(3);
    this.bulM[53].setFire(true);
    this.bulM[53].select = -2;
    sleep(4000);

    this.em2[54].setState(230, -50, nomalHP + 50);
    this.em2[54].setPath(3);
    this.bulM[54].setFire(true);
    this.bulM[54].select = 4;

    sleep(15000);

    sleep(10000);

    this.checkCaption = true;

    sleep(2000);
    this.checkCaption = false;
    sleep(2000);

    sleep(5000);

    this.boss.setState(100, -100, nomalHP + 1200);
    this.boss.setPath(7);
    this.bossBulM.setFire(true);
    this.bossBulM.select = 5;

    sleep(30000);

    this.em2[50].setState(50, -50, nomalHP + 50);
    this.em2[50].setPath(3);
    this.bulM[50].setFire(true);
    this.bulM[50].select = 2;
    sleep(1000);

    this.em2[51].setState(400, -50, nomalHP + 50);
    this.em2[51].setPath(3);
    this.bulM[51].setFire(true);
    this.bulM[51].select = -2;
    sleep(6000);

    this.em2[52].setState(100, -50, nomalHP + 50);
    this.em2[52].setPath(3);
    this.bulM[52].setFire(true);
    this.bulM[52].select = 2;
    sleep(1000);

    this.em2[53].setState(350, -50, nomalHP + 50);
    this.em2[53].setPath(3);
    this.bulM[53].setFire(true);
    this.bulM[53].select = -2;
    sleep(4000);

    this.em2[54].setState(230, -50, nomalHP + 50);
    this.em2[54].setPath(3);
    this.bulM[54].setFire(true);
    this.bulM[54].select = 4;
  }
  public void sleep(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}