package risk.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ZoneButton implements Serializable {
	private static final long serialVersionUID = -6146543193300454278L;
	private Polygon area;
	private Point center;
	private Rectangle outline;
	private List<String> msg;
	private int zoneID;
	private static Color FILL_COLOR = new Color(0.0f, 1.0f, 0.0f, 0.3f);
	private static Color OUTLINE_COLOR = new Color(1.0f, 0.0f, 0.0f, 1.0f);
	private static Color SELECT_COLOR = new Color(0.0f, 0.0f, 1.0f, 0.5f);
	private static Color NEIGHBOUR_COLOR = new Color(0.0f, 1.0f, 0.0f, 0.5f);
	private boolean select = false;
	private boolean neighbour = false;

	public ZoneButton(Polygon area, String name, int zoneID, int production) {
		this.area = area;
		this.zoneID = zoneID;
		outline = area.getBounds();
		center = new Point(outline.x + (outline.width / 2),outline.y + (outline.height / 2));
		msg = new ArrayList<String>();
		
		
		msg.add(name);
		msg.add("Production: " + production);
		//msg.add("" + zoneID); // TEMP
	}
	
	public void addMsg(String msg) {
		this.msg.add(msg);
	}
	
	public boolean contains(Point point) {
		return outline.contains(point) ? area.contains(point) : false;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public Rectangle getOutline() {
		return outline;
	}
	
	public int getID() {
		return zoneID;
	}
	
	public boolean isVisible() { return false; }
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(select) {
			g2d.setPaint(new GradientPaint(20,20,Color.green, 5, 5, FILL_COLOR, true));
		} else if(neighbour) {
			g2d.setPaint(new GradientPaint(20,20,Color.red, 5, 5, FILL_COLOR, true));
		} else {
			g2d.setColor(FILL_COLOR);
		}
		
		//g2d.setColor(select ? SELECT_COLOR : (neighbour ? NEIGHBOUR_COLOR : FILL_COLOR));
		g2d.fillPolygon(area);
		g2d.setColor(OUTLINE_COLOR); // team color maybe?
		g2d.setStroke(new BasicStroke(3));
		g2d.drawPolygon(area);
		
		//DrawOutline(g2d);
		//DrawCenter(g2d);
		DrawText(g2d);
	
		g2d.dispose();
	}
	
	public void Select(boolean b) {
		select = b;
	}
	
	public void Neighbour(boolean b) {
		neighbour = b;
	}
	
	public void ClearStatus() {
		select = false;
		neighbour = false;
	}
	
	@SuppressWarnings("unused")
	private void DrawOutline(Graphics2D g) {
		g.setColor(new Color(0.0f, 1.0f, 0.0f, 1.0f));
		g.drawRect(outline.x, outline.y, outline.width, outline.height);
	}
	
	@SuppressWarnings("unused")
	private void DrawCenter(Graphics2D g) {
		g.setColor(new Color(0.0f, 0.0f, 1.0f, 1.0f));
		Polygon p = new Polygon();
		p.addPoint(center.x - 5, center.y - 5);
		p.addPoint(center.x + 5, center.y + 5);
		p.addPoint(center.x, center.y);
		p.addPoint(center.x - 5, center.y + 5);
		p.addPoint(center.x + 5, center.y - 5);
		p.addPoint(center.x, center.y);
		g.drawPolygon(p);
	}
	
	@SuppressWarnings("unused")
	private void DrawText(Graphics2D g) { // TODO: CHANGE FUNCTION TO WRITE MULTIPLE LINES INSTEAD OF ONE MESS.
		// TODO: ADD TRANSPARANT DARK (or bright) BOX BEHIND TEXT TO MAKE IT EASIER TO READ.
		g.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		Font oldfont = g.getFont();
		Font font = oldfont.deriveFont(Font.BOLD);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		
		int j = (metrics.getHeight() - 2 * metrics.getAscent());
		int i = msg.size();
		
		for(String str : msg) {
		    int x = (outline.width - metrics.stringWidth(str)) / 2;
		    int y = ((outline.height - (metrics.getHeight() + 6) * i--) / 2) + metrics.getAscent();
		    g.setFont(font);
		    g.drawString(str, x + outline.x, y + outline.y);
		}
		
		g.setFont(oldfont);
	}
}
