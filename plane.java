package shoot;

public class plane
{
  private int imgLeft = 0;
  private int imgTop = 0;
  private int imgRight = 0;
  private int imgBottom = 0;
  private int x;
  private int y;
  private int hp;
  private int speed;
  private boolean visible = false;

  public plane(int x, int y) {
    this.x = x;
    this.y = y;
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

  public void incX(int x) {
    this.x += x;
  }

  public void incY(int y) {
    this.y += y;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int getHp() {
    return this.hp;
  }

  public void setImgLeft(int imgLeft) {
    this.imgLeft = imgLeft;
  }

  public int getImgLeft() {
    return this.imgLeft;
  }

  public void setImgTop(int imgTop) {
    this.imgTop = imgTop;
  }

  public int getImgTop() {
    return this.imgTop;
  }

  public void setImgRight(int imgRight) {
    this.imgRight = imgRight;
  }

  public int getImgRight() {
    return this.imgRight;
  }

  public void setImgBottom(int imgBottom) {
    this.imgBottom = imgBottom;
  }

  public int getImgBottom() {
    return this.imgBottom;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getSpeed() {
    return this.speed;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public boolean isVisible() {
    return this.visible;
  }
}