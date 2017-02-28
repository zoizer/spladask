package risk.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
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

	public ZoneButton(Polygon area, String name, int zoneID, int production) {
		this.area = area;
		this.zoneID = zoneID;
		outline = area.getBounds();
		center = new Point(outline.x + (outline.width / 2),outline.y + (outline.height / 2));
		msg = new ArrayList<String>();
		msg.add(name);
		msg.add("Production: " + production);
	}
	
	public void addMsg(String msg) {
		this.msg.add(msg);
	}
	
	public boolean contains(Point point) {
		return area.contains(point);
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
		g.setColor(new Color(0.0f, 1.0f, 0.0f, 0.3f));
		g.fillPolygon(area);
		g.setColor(new Color(1.0f, 0.0f, 0.0f, 1.0f));
		g.drawPolygon(area);
		
		//DrawOutline(g);
		//DrawCenter(g);
		DrawText(g);
	
	}
	
	@SuppressWarnings("unused")
	private void DrawOutline(Graphics g) {
		g.setColor(new Color(0.0f, 1.0f, 0.0f, 1.0f));
		g.drawRect(outline.x, outline.y, outline.width, outline.height);
	}
	
	@SuppressWarnings("unused")
	private void DrawCenter(Graphics g) {
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
	private void DrawText(Graphics g) { // TODO: CHANGE FUNCTION TO WRITE MULTIPLE LINES INSTEAD OF ONE MESS.
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
