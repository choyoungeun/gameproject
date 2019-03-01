package shoot;

import java.util.Vector;

public class MyKey
{
  Vector<Integer> m_vKeys = new Vector();

  public void addKeyPressed(int key)
  {
    for (int i = 0; i < this.m_vKeys.size(); i++) {
      Integer a = (Integer)this.m_vKeys.elementAt(i);
      if (a.intValue() == key)
      {
        return;
      }
    }
    this.m_vKeys.add(new Integer(key));
  }
  public void addKeyReleased(int key) {
    for (int i = 0; i < this.m_vKeys.size(); i++) {
      Integer a = (Integer)this.m_vKeys.elementAt(i);
      if (a.intValue() == key) {
        this.m_vKeys.removeElementAt(i);
        return;
      }
    }
  }

  public boolean getKeyState(int key) { for (int i = 0; i < this.m_vKeys.size(); i++) {
      Integer a = (Integer)this.m_vKeys.elementAt(i);
      if (a.intValue() == key) {
        return true;
      }
    }
    return false;
  }
}
