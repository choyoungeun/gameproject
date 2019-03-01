package shoot;

import java.awt.Image;

public class enemyPlane extends plane
  implements Runnable
{
  public Image enemyImg;
  private int path = -1;
  int a = 1;
  int b;
  int right;
  int left;

  public enemyPlane(int x, int y)
  {
    super(x, y);
  }

  public void run() {
    while (true) {
      if ((getX() < -100) || (getX() > 550) || (getY() < -100) || (getY() > 700) || (getHp() <= 0)) {
        setVisible(false);
        setX(-50);
        setY(-50);
      }

      if (this.path == 0) {
        if (this.a == 1) {
          incX(getSpeed() * 2);
          incY(getSpeed() * 1);

          if (getX() > Math.random() * 30.0D + 400.0D)
          {
            this.a = 0;
          }
        } else if (this.a == 0) {
          incX(getSpeed() * -2);
          incY(getSpeed() * 1);

          if (getX() < Math.random() * 20.0D + 40.0D) {
            this.a = 1;
          }

        }

      }
      else if (this.path == 1) {
        incY(getSpeed() * 2);

        if (getY() > 200) {
          if (this.a == 1) {
            sleep(1500);
            this.a = 0;
          }
          if (this.right == 1) {
            incX(getSpeed() * 3);
            incY(getSpeed() * -1);
          } else if (this.left == 1) {
            incX(getSpeed() * -3);
            incY(getSpeed() * -1);
          }

        }

      }
      else if (this.path == 2) {
        if (this.right == 1) {
          incX(getSpeed() * 2);
          if ((getX() >= 149) && (getX() <= 150)) {
            sleep(1300);
          }
        }
        if (this.left == 1) {
          incX(getSpeed() * -2);
          if (getX() == 332) {
            sleep(1100);
          }
        }

      }
      else if (this.path == 3) {
        incY(getSpeed() * 1);
        if (getY() == 200) {
          sleep(5000);
        }
      }
      else if (this.path == 4) {
        int cr = 10;

        for (int i = 0; i < 180; i += 15) {
          sleep(45);
          double radian = 3.141592653589793D * i / 180.0D;

          int x = (int)(cr * Math.cos(radian));
          int y = (int)(cr * Math.sin(radian));

          setX(getX() + x);
          setY(getY() + y);
        }

        for (int i = 180; i > 0; i -= 15) {
          sleep(45);
          double radian = 3.141592653589793D * i / 180.0D;

          int x = (int)(cr * Math.cos(radian));
          int y = (int)(cr * Math.sin(radian));

          setX(getX() + x);
          setY(getY() + y);
        }

      }
      else if (this.path == 5) {
        int cr = 10;

        for (int i = 0; i < 180; i += 10) {
          sleep(45);
          double radian = 3.141592653589793D * i / 180.0D;

          int y = (int)(cr * Math.cos(radian));
          int x = (int)(cr * Math.sin(radian));

          if (this.right == 1) {
            setX(getX() + x);
            setY(getY() + y);
          }
          else if (this.left == 1) {
            setX(getX() - x);
            setY(getY() - y);
          }
        }

        for (int i = 180; i > 0; i -= 10) {
          sleep(45);
          double radian = 3.141592653589793D * i / 180.0D;

          int y = (int)(cr * Math.cos(radian));
          int x = (int)(cr * Math.sin(radian));

          if (this.right == 1) {
            setX(getX() + x);
            setY(getY() + y);
          }
          else if (this.left == 1) {
            setX(getX() - x);
            setY(getY() - y);
          }
        }
      }
      if (this.path == 6) {
        if (this.a == 1) {
          incX(getSpeed() * 3);
          incY(getSpeed() * 1);

          if (getX() > 500)
          {
            this.a = 0;
          }
        } else if (this.a == 0) {
          incX(getSpeed() * -3);
          incY(getSpeed() * 1);

          if (getX() < -50) {
            this.a = 1;
          }
        }
      }

      if (this.path == 7) {
        if (this.a == 1) {
          incY(getSpeed() * 3);
          if (getY() > 200) {
            sleep(2000);
            this.a = 2;
          }
        }
        else if (this.a == 2) {
          incX(getSpeed() * 3);
          incY(getSpeed() * -3);
          if (getX() > 330) {
            sleep(2000);
            this.a = 3;
          }
        }
        else if (this.a == 3) {
          incX(getSpeed() * -3);
          if (getX() < 10) {
            sleep(2000);
            this.a = 4;
          }
        }
        else if (this.a == 4) {
          incY(getSpeed() * 3);
          if (getY() > 180) {
            sleep(500);
            this.a = 5;
          }
        }
        else if (this.a == 5) {
          int cr = 10;

          for (int i = 0; i < 180; i += 10) {
            sleep(45);
            double radian = 3.141592653589793D * i / 180.0D;

            int y = (int)(cr * Math.cos(radian));
            int x = (int)(cr * Math.sin(radian));

            setX(getX() + x);
            setY(getY() + y);

            if (getX() > 330) {
              sleep(500);
              this.a = 6;
              break;
            }
          }

        }
        else if (this.a == 6) {
          int cr = 10;
          for (int i = 180; i > 0; i -= 10) {
            sleep(45);
            double radian = 3.141592653589793D * i / 180.0D;

            int y = (int)(cr * Math.cos(radian));
            int x = (int)(cr * Math.sin(radian));

            setX(getX() - x);
            setY(getY() - y);

            if (getX() < 10) {
              sleep(500);
              this.a = 7;
              break;
            }
          }
        }
        else if (this.a == 7) {
          int cr = 20;

          for (int i = 0; i < 180; i += 10) {
            sleep(45);
            double radian = 3.141592653589793D * i / 180.0D;

            int y = (int)(cr * Math.cos(radian));
            int x = (int)(cr * Math.sin(radian));

            setX(getX() + x);
            setY(getY() - y);

            if (getX() > 330) {
              sleep(500);
              this.a = 6;
              break;
            }
            if (getY() < 150) {
              sleep(2000);
              this.a = 8;
              break;
            }
          }
        }
        else if (this.a == 8)
        {
          for (int i = 0; i < 8; i++) {
            setX((int)(Math.random() * 350.0D + 10.0D));
            setY((int)(Math.random() * 300.0D));
            sleep(1000);
          }
          setX(150);
          setY(170);
          sleep(3000);
          this.a = ((int)(Math.random() * 7.0D));
        }
      }

      try
      {
        Thread.sleep(30L); } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  protected void sleep(int i) {
    try { Thread.sleep(i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void setState(int x, int y, int i) {
    setX(x);
    setY(y);
    setHp(i);
    setVisible(true);
  }

  public void setPath(int path) {
    this.path = path;
  }

  public int getPath() {
    return this.path;
  }
}