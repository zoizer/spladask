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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclAttackFromEvent;
import risk.event.LclStopAttackFromEvent;
import risk.event.SvrAttackZoneEvent;
import risk.event.SvrNextTurnEvent;
import risk.event.SvrUpdateZoneEvent;
import risk.general.Map;
import risk.general.Zone;
import risk.model.Phase;
import risk.util.Delegate;
import risk.util.ErrorHandler;

public class LocalGameView extends AEventSystem implements IGameView {
	private Map map;
	private JFrame parent;
	private MouseAdapter mouseAdapter;
	private MapView mapView;
	private UIPanel uiPanel; // TEMP
	private String playerName;
	private Phase phase;
	private int startingStrength;
	private int selected;
	private List<Integer> neighbours;
	private final Color FILL_COLOR;
	private final Color OUTLINE_COLOR;
	
	public LocalGameView(Map map, JFrame jFrame, MouseAdapter mouseAdapter, String name, int startingStrength) {
		this.map = map;
		this.parent = jFrame;
		this.mouseAdapter = mouseAdapter;
		this.playerName = name;
		this.phase = Phase.ERROR_DO_NOT_USE;
		this.startingStrength = startingStrength;
		
		selected = -1;
		neighbours = null;
		FILL_COLOR = new Color(0.0f, 1.0f, 0.0f, 0.3f);
		OUTLINE_COLOR = new Color(1.0f, 0.0f, 0.0f, 1.0f);
		
		mapView = new MapView(this); // should attach to jFrame
		mapView.addMouseListener(this.mouseAdapter);
		
		uiPanel =  new UIPanel(new BorderLayout()); // TEMP
		uiPanel.setPlayer(this.playerName);
		uiPanel.setInitTrainableUnits(this.startingStrength);
        parent.add(uiPanel, BorderLayout.SOUTH); // TEMP
        
		parent.add(mapView, BorderLayout.NORTH);
		
		// mapView.setBorder(BorderFactory.createTitledBorder("Test")); // TEMPORARY, displays a border around the map, used to debug.
		
    	parent.pack();				// Probably wanna do this here.
    	parent.setVisible(true);	// ^
    	
    	attachListeners();
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
		attachListener(new Delegate(this, "svrUpdateZone"), EventType.SvrUpdateZoneEvent);
		attachListener(new Delegate(this, "lclAttackFrom"), EventType.LclAttackFromEvent);
		attachListener(new Delegate(this, "lclStopAttackFrom"), EventType.LclStopAttackFromEvent);
		attachListener(new Delegate(this, "svrAttackZone"), EventType.SvrAttackZoneEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
		detachListener(new Delegate(this, "svrUpdateZone"), EventType.SvrUpdateZoneEvent);
		detachListener(new Delegate(this, "lclAttackFrom"), EventType.LclAttackFromEvent);
		detachListener(new Delegate(this, "lclStopAttackFrom"), EventType.LclStopAttackFromEvent);
		detachListener(new Delegate(this, "svrAttackZone"), EventType.SvrAttackZoneEvent);
	}

	@Override
	public void destroy() {
		detachListeners();
		parent.remove(uiPanel);
		parent.remove(mapView);
	}
	
	public void svrNextTurn(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrNextTurnEvent);
		SvrNextTurnEvent e = (SvrNextTurnEvent) ev;
		
		
		//playersTurn = e.playersTurn.name;
		if (playerName.equals(e.playersTurn.name)) {
			uiPanel.resetTrainableUnits(); // my turn, update trainable units
			uiPanel.setYourTurn(true);
		} else uiPanel.setYourTurn(false);
		
		if (phase != e.phase) {
			phase = e.phase;
			uiPanel.setPhase(phase);
		}
		
		uiPanel.repaint();
		mapView.repaint();
	}
	
	public void svrUpdateZone(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrUpdateZoneEvent);
		SvrUpdateZoneEvent e = (SvrUpdateZoneEvent) ev;
		
		Zone old = map.getZone(e.zoneid);
		map.setZone(e.zone, e.zoneid);
		
		
		if (e.phase == Phase.INIT_PHASE) {
			if (old.getArmy() != e.zone.getArmy()) {
				// update army
				if (e.zone.hasOwner() && e.zone.getOwner().equals(playerName)) {
					uiPanel.setStrength(uiPanel.getStrength() + e.zone.getArmy() - old.getArmy());
					uiPanel.setInitTrainableUnits(uiPanel.getInitTrainableUnits() - e.zone.getArmy() + old.getArmy());
				}
			}
			
			if (old.hasOwner() != e.zone.hasOwner() || !old.getOwner().equals(e.zone.getOwner())) { // ownership changed.
				// new owner
				
				if ((old.hasOwner() && old.getOwner().equals(playerName)) || e.zone.hasOwner() && e.zone.getOwner().equals(playerName)) { // I was either old or new owner.
					// player owned or owns this zone now.
					int oldProd = old.getProduction();
					int newProd = e.zone.getProduction();
					
					if (old.hasOwner() && old.getOwner().equals(playerName)) {
						// Player was the old owner
						uiPanel.setProduce(uiPanel.getProduce() - oldProd);
					} else {
						// Player is the new owner
						uiPanel.setProduce(uiPanel.getProduce() + newProd);
					}
				}
			}
		} else if (e.phase == Phase.TRAIN_PHASE) {

			if (old.getArmy() != e.zone.getArmy()) {
				// update army
				if (e.zone.hasOwner() && e.zone.getOwner().equals(playerName)) {
					int diff = e.zone.getArmy() - old.getArmy();
					uiPanel.setStrength(uiPanel.getStrength() + diff);
					uiPanel.setTrainableUnits(uiPanel.getTrainableUnits() - diff);
				}
			}
		}
	
		mapView.repaint();
	}
	
	public void lclAttackFrom(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclAttackFromEvent);
		LclAttackFromEvent e = (LclAttackFromEvent) ev;
		selected = e.zoneId;
		neighbours = map.getZone(e.zoneId).getNeighbours();
		mapView.repaint();
	}
	
	public void lclStopAttackFrom(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclStopAttackFromEvent);
	//	LclAttackFromEvent e = (LclAttackFromEvent) ev;
		if (selected != -1) {
			selected = -1;
			neighbours = null;
			mapView.repaint();
		}
	}
	
	public void svrAttackZone(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrAttackZoneEvent);
		SvrAttackZoneEvent e = (SvrAttackZoneEvent) ev;
		
		if (e.attack) { // attack
			Zone oldDst = map.getZone(e.dstid);
			if (e.dst.hasOwner() && e.dst.getOwner().equals(e.src.getOwner())) { // success attack
				
				if (oldDst.hasOwner() && oldDst.getOwner().equals(playerName)) { // I was attacked.
					uiPanel.setStrength(uiPanel.getStrength() - e.lostArmyDst); // reduce my total strength
					uiPanel.setProduce(uiPanel.getProduce() - oldDst.getProduction()); // reduce my total production
					
				} else if (e.src.hasOwner() && e.src.getOwner().equals(playerName)) { // I attacked!
					uiPanel.setStrength(uiPanel.getStrength() - e.lostArmySrc); // reduce my total strength (unit may have been lost in battle)
					uiPanel.setProduce(uiPanel.getProduce() + e.dst.getProduction()); // add my total production
				}
				
				
				
			} else { // failed attack.
				if (oldDst.hasOwner() && oldDst.getOwner().equals(playerName)) { // I was attacked.
					uiPanel.setStrength(uiPanel.getStrength() - e.lostArmyDst); // reduce my total strength
					
				} else if (e.src.hasOwner() && e.src.getOwner().equals(playerName)) { // I attacked!
					uiPanel.setStrength(uiPanel.getStrength() - e.lostArmySrc); // reduce my total strength (unit may have been lost in battle)
				}
			}

			uiPanel.resetTrainableUnits();
			uiPanel.repaint();
			
			if (e.winner != null) {
				uiPanel.setWinner(e.winner);
			}
			
			map.setZone(e.src, e.srcid);
			map.setZone(e.dst, e.dstid);
			
		} else { // move
			map.setZone(e.src, e.srcid);
			map.setZone(e.dst, e.dstid);
		}
		
		mapView.repaint();
	}
	
	private Dimension getMapDimensions() {
		return new Dimension(map.getImg().getWidth(), map.getImg().getHeight());
	}
	
	private void paintImage(Graphics g) {
        g.drawImage(map.getImg(), 0, 0, mapView); // TEMPORARY CONCEPT
	}
	
	private void paintZones(Graphics g) {
		final int count = map.getZoneCount();
        for(int i = 0; i < count; i++) {
        	Zone z = map.getZone(i);
        	
        	Graphics2D g2d = (Graphics2D)g.create();
    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    		
    		if(i == selected) {
    			g2d.setPaint(new GradientPaint(20,20,Color.green, 5, 5, FILL_COLOR, true));
    		} else if(neighbours != null && neighbours.contains(i)) {
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
    		//drawZoneText(g2d, z);
    	
    		g2d.dispose();
        }
	}
	
	private void paintZonesText(Graphics g) {
		final int count = map.getZoneCount();
        for(int i = 0; i < count; i++) {
        	Zone z = map.getZone(i);
        	
        	Graphics2D g2d = (Graphics2D)g.create();
    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    		
    		g2d.setColor(OUTLINE_COLOR); // team color maybe?
    		
    		//DrawOutline(g2d);
    		//DrawCenter(g2d);
    		drawZoneText(g2d, z);
    	
    		g2d.dispose();
        }
	}
	
	private void drawZoneText(Graphics2D g, Zone z) {
		g.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		Font oldfont = g.getFont();
		Font font = oldfont.deriveFont(Font.BOLD);
		font = font.deriveFont(9.0f);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		
		//int j = (metrics.getHeight() - 2 * metrics.getAscent());
		int count = 3; // count of lines of text.
	    drawZoneTextLine(g, z.getName(), count--, z.getOutline(), metrics, font);
	    drawZoneTextLine(g, z.getOwner(), count--, z.getOutline(), metrics, font);
	    drawZoneTextLine(g, "Army: " + z.getArmy(), count--, z.getOutline(), metrics, font);
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
		paintZonesText(g);
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
