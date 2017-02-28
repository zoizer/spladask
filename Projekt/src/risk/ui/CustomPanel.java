package risk.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class CustomPanel extends JPanel {
	private static final long serialVersionUID = -6934313203454347647L;
	private BufferedImage img = null;
	private List<ZoneButton> btns = new ArrayList<>();
	private TheMouseListener tml;
	
	public CustomPanel(BufferedImage img) {
		tml = new TheMouseListener();
		this.img = img;
		this.addMouseListener(tml);
	}
	
	public void attachBtn(ZoneButton btn) {
		btns.add(btn);
	}
	
	public void attachBtns(ArrayList<ZoneButton> btns) {
		this.btns = btns;
	}
	
	public void removeBtn(ZoneButton btn) {
		btns.remove(btn);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(img.getWidth(), img.getHeight());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		System.out.println(img.getWidth());
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this); // moved order!
        for(ZoneButton btn : btns) btn.paint(g);
    }
	
	private class TheMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				for(ZoneButton btn : btns) 
					if(btn.contains(e.getPoint())) {
						System.out.println("Left-clicked: " + btn.getID());
					}
			} else if(e.getButton() == MouseEvent.BUTTON3) {
				for(ZoneButton btn : btns) 
					if(btn.contains(e.getPoint())) {
						System.out.println("Right-clicked: " + btn.getID());
					}
			}
		}
	}
}
