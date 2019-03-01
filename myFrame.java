package shoot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

class myFrame extends JFrame
implements Runnable, KeyListener, ActionListener
{
private int Width = 450;
private int Height = 650;

GameScreen canva = new GameScreen();
MyKey m_myKey = new MyKey();
Timer m_tKey;
Timer t_tKey;
int count = 0;

public myFrame()
{
  setSize(this.Width, this.Height);
  setDefaultCloseOperation(3);
  setResizable(false);
  setTitle("War In Space");
  addKeyListener(this);

  this.m_tKey = new Timer(20, this);
  this.m_tKey.start();
  this.t_tKey = new Timer(60, this);
  this.t_tKey.start();

  add(this.canva);
  setVisible(true);
}
public void run() {
  repaint();
}

public void actionPerformed(ActionEvent e) {
  if ((e.getSource() == this.t_tKey) && 
    (this.m_myKey.getKeyState(90)))
  {
    for (int i = 0; i < GameScreen.BULLET_MAX / 2; i++) {
      if (!this.canva.bul[i].isVisible())
      {
        this.canva.bul[i].setVisible(true);
        this.canva.bul[i].setX(this.canva.p.getX() + 16);
        this.canva.bul[i].setY(this.canva.p.getY());

        this.canva.bul[(i + 39)].setVisible(true);
        this.canva.bul[(i + 39)].setX(this.canva.p.getX() + 30);
        this.canva.bul[(i + 39)].setY(this.canva.p.getY());

        break;
      }
    }
  }

  if (e.getSource() == this.m_tKey)
  {
    if (this.m_myKey.getKeyState(38)) {
      this.canva.p.incY(-this.canva.p.getSpeed());
      if (this.count < 0) {
        this.count = 1;
      }
      else {
        this.count -= 1;
      }
    }
    if (this.m_myKey.getKeyState(40)) {
      this.canva.p.incY(this.canva.p.getSpeed());
      if (this.count < 0) {
        this.count = 1;
      }
      else {
        this.count -= 1;
      }
    }
    if (this.m_myKey.getKeyState(37)) {
      this.canva.p.incX(-this.canva.p.getSpeed());
      if (this.count < 0) {
        this.count = 1;
      }
      else {
        this.count -= 1;
      }
    }
    if (this.m_myKey.getKeyState(39)) {
      this.canva.p.incX(this.canva.p.getSpeed());
      if (this.count < 0) {
        this.count = 1;
      }
      else
        this.count -= 1;
    }
  }
}

public void keyPressed(KeyEvent e)
{
  this.m_myKey.addKeyPressed(e.getKeyCode());
}
public void keyReleased(KeyEvent e) {
  this.m_myKey.addKeyReleased(e.getKeyCode());
}

public void keyTyped(KeyEvent e)
{
}
}