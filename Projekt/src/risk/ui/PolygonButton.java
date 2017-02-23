import javax.swing.JComponent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Cursor;

class PolygonButton extends JComponent implements MouseListener,
    MouseMotionListener {
  static public Color ACTIVE_COLOR = Color.red;
  static public Color INACTIVE_COLOR = Color.darkGray;
  protected String text;
  protected Polygon polygon;
  protected Rectangle rectangle;
  protected boolean isActive;
  protected static PolygonButton button;

  public PolygonButton(Polygon p, String text) {
    polygon = p;
    setText(text);

    setOpaque(false);

    addMouseListener(this);
    addMouseMotionListener(this);

    rectangle = new Rectangle(polygon.getBounds());
    rectangle.grow(1, 1);

    setBounds(rectangle);
    polygon.translate(-rectangle.x, -rectangle.y);
  }

  public void setText(String t) {
    text = t;
  }

  public String getText() {
    return text;
  }

  public void mouseMoved(MouseEvent e) {
    if (!rectangle.contains(e.getX(), e.getY()) || e.isConsumed()) {
      if (isActive) {
        isActive = false;
        repaint();
      }
      return; 
    }

    int x = e.getX() - rectangle.x;
    int y = e.getY() - rectangle.y;
    boolean active = polygon.contains(x, y);

    if (isActive != active)
      setState(active);
    if (active)
      e.consume();
  }

  public void mouseDragged(MouseEvent e) {
  }

  protected void setState(boolean active) {
    isActive = active;
    repaint();
    if (active) {
      if (button != null)
        button.setState(false);
      setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    } else {
      button = null;
      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
    mouseMoved(e);
  }

  public void mouseEntered(MouseEvent e) {
    mouseMoved(e);
  }

  public void paint(Graphics g) {
    g.setColor(isActive ? ACTIVE_COLOR : INACTIVE_COLOR);
    g.drawPolygon(polygon);
  }

}