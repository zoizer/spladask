package risk.view;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import risk.event.AEventSystem;
import risk.general.Map;
import risk.general.Zone;
import risk.ui.ControlPanel;

public class LocalGameView extends AEventSystem implements IGameView {
	private Map map;
	private JFrame parent;
	private MouseAdapter mouseAdapter;
	private MapView mapView;
	private ControlPanel ctrlPanel; // TEMP
	private String playerName;
	
	public LocalGameView(Map map, JFrame jFrame, MouseAdapter mouseAdapter, String name) {
		this.map = map;
		this.parent = jFrame;
		this.mouseAdapter = mouseAdapter;
		this.playerName = name;
		mapView = new MapView(this); // should attach to jFrame
		mapView.addMouseListener(this.mouseAdapter);
		
		ctrlPanel =  new ControlPanel(new BorderLayout()); // TEMP
		ctrlPanel.setPlayer(this.playerName);
        parent.add(ctrlPanel, BorderLayout.SOUTH); // TEMP
        
		parent.add(mapView, BorderLayout.NORTH);
		
		// mapView.setBorder(BorderFactory.createTitledBorder("Test")); // TEMPORARY, displays a border around the map, used to debug.
		
    	parent.pack();				// Probably wanna do this here.
    	parent.setVisible(true);	// ^
    	
    	attachListeners();
	}
	
	@Override
	public void attachListeners() {
		
	}

	@Override
	public void detachListeners() {
		
	}

	@Override
	public void destroy() {
		detachListeners();
		parent.remove(ctrlPanel);
		parent.remove(mapView);
	}
	
	private Dimension getMapDimensions() {
		return new Dimension(map.getImg().getWidth(), map.getImg().getHeight());
	}
	
	private void paintImage(Graphics g) {
        g.drawImage(map.getImg(), 0, 0, mapView); // TEMPORARY CONCEPT
	}
	
	private final boolean SELECTED = false;	// TODO replace with real.
	private final boolean NEIGHBOUR = false;
	private static Color FILL_COLOR = new Color(0.0f, 1.0f, 0.0f, 0.3f);
	private static Color OUTLINE_COLOR = new Color(1.0f, 0.0f, 0.0f, 1.0f);
	
	private void paintZones(Graphics g) {
		final int count = map.getZoneCount();
        for(int i = 0; i < count; i++) {
        	Zone z = map.getZone(i);
        	
        	Graphics2D g2d = (Graphics2D)g.create();
    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    		
    		if(SELECTED) {
    			g2d.setPaint(new GradientPaint(20,20,Color.green, 5, 5, FILL_COLOR, true));
    		} else if(NEIGHBOUR) {
    			g2d.setPaint(new GradientPaint(20,20,Color.red, 5, 5, FILL_COLOR, true));
    		} else {
    			g2d.setColor(FILL_COLOR);
    		}
    		
    		//g2d.setColor(select ? SELECT_COLOR : (neighbour ? NEIGHBOUR_COLOR : FILL_COLOR));
    		g2d.fillPolygon(z.getPolygon());
    		g2d.setColor(OUTLINE_COLOR); // team color maybe?
    		g2d.setStroke(new BasicStroke(3));
    		g2d.drawPolygon(z.getPolygon());
    		
    		//DrawOutline(g2d);
    		//DrawCenter(g2d);
    		drawZoneText(g2d, z, i);
    	
    		g2d.dispose();
        }
	}
	
	private void drawZoneText(Graphics2D g, Zone z, int id) {
		g.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		Font oldfont = g.getFont();
		Font font = oldfont.deriveFont(Font.BOLD);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		
		//int j = (metrics.getHeight() - 2 * metrics.getAscent());
		int count = 2; // count of lines of text.
	    drawZoneTextLine(g, z.getName(), count--, z.getOutline(), metrics, font);
	    drawZoneTextLine(g, z.getOwner(), count--, z.getOutline(), metrics, font);
	    //drawZoneTextLine(g, "" + id, count--, z.getOutline(), metrics, font);
		
		g.setFont(oldfont);
	}
	
	private void drawZoneTextLine(Graphics2D g, String str, int index, Rectangle outline, FontMetrics metrics, Font font) {
		int x = (outline.width - metrics.stringWidth(str)) / 2;
	    int y = ((outline.height - (metrics.getHeight() + 6) * index) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(str, x + outline.x, y + outline.y);
	}
	
	private void paintMap(Graphics g) {
		paintImage(g);
		paintZones(g);
	}
	
	private class MapView extends JPanel {
		private static final long serialVersionUID = -5605412038304313665L;
		private LocalGameView parent;
		
		public MapView(LocalGameView parent) {
			this.parent = parent;
		}
		
		@Override
		public Dimension getPreferredSize() {
			return parent.getMapDimensions();
		}
		
		@Override
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        parent.paintMap(g);
	    }
		
	}
}
