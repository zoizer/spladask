package risk.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.*;
import javax.imageio.*;
import java.awt.Polygon;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.*;
import java.awt.*;

public class WorldMapInit  {
    JFrame f = new JFrame();
    
    
    public WorldMapInit()throws FileNotFoundException, IOException {
        
        f.setVisible(true);
        f.pack();
        //Initializes the map
        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("sistariskcolored.png")))));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //Initializes the buttons on the districts -- refactor

        ///NORTH AMERICA
        Polygon alaska = new Polygon();
        alaska.addPoint(42,60);
        alaska.addPoint(84,51);
        alaska.addPoint(101,164);
        alaska.addPoint(49,138);
        alaska.addPoint(21,164);
        alaska.addPoint(1,121);
        PolygonButton alaskaBtn = new PolygonButton(alaska, "alaska");
        f.getContentPane().add(alaskaBtn);
        
        Polygon nw = new Polygon();
        nw.addPoint(84, 51);
        nw.addPoint(191, 83);
        nw.addPoint(215,53);
        nw.addPoint(287,113);
        nw.addPoint(312, 127);
        nw.addPoint(96,128);
        PolygonButton nwBtn = new PolygonButton(nw, "northwest");
        f.getContentPane().add(nwBtn);
        
        Polygon greenland = new Polygon(); // tanken är att alla dessa ska tas bort efter att dom har lagts in som objekt i zones.txt
        greenland.addPoint(500, 2);
        greenland.addPoint(538, 35);
        greenland.addPoint(500,109);
        greenland.addPoint(500,139);
        greenland.addPoint(447,154);
        greenland.addPoint(416,198);
        greenland.addPoint(396,170);
        greenland.addPoint(412,139);
        greenland.addPoint(403,79);
        greenland.addPoint(372,58);
        greenland.addPoint(428,14);
        PolygonButton greenlandBtn = new PolygonButton(greenland, "greenland");
        f.getContentPane().add(greenlandBtn);
          
        Polygon n1 = new Polygon();
        n1.addPoint(96,128);
        n1.addPoint(197,128);  
        n1.addPoint(202,203);
        n1.addPoint(110,224);
        PolygonButton n1Btn = new PolygonButton(n1, "n1");
        f.getContentPane().add(n1Btn);
    
        Polygon n2 = new Polygon();
        n2.addPoint(197,128);
        n2.addPoint(312,128);  
        n2.addPoint(261,162);
        n2.addPoint(243,193);
        n2.addPoint(202,203);
        PolygonButton n2Btn = new PolygonButton(n2, "n2");
        f.getContentPane().add(n2Btn);
        
        Polygon quebec = new Polygon();
        quebec.addPoint(310,173);
        quebec.addPoint(347,194);
        quebec.addPoint(375,253);
        quebec.addPoint(294,228);  
        PolygonButton quebecBtn = new PolygonButton(quebec, "quebec");
        f.getContentPane().add(quebecBtn);
        
        Polygon westUS = new Polygon();
        westUS.addPoint(202,203);
        westUS.addPoint(191,346);
        westUS.addPoint(157,365);
        westUS.addPoint(102,313);
        westUS.addPoint(110,225); 
        PolygonButton westUSBtn = new PolygonButton(westUS, "westUS");
        f.getContentPane().add(westUSBtn);
        
        Polygon eastUS = new Polygon();
        eastUS.addPoint(202,203);
        eastUS.addPoint(244,193);
        eastUS.addPoint(294,229);
        eastUS.addPoint(375,253);
        eastUS.addPoint(250,337);
        eastUS.addPoint(192,346);
        PolygonButton eastUSBtn = new PolygonButton(eastUS, "eastUS");
        f.getContentPane().add(eastUSBtn);
        
        /*
        Polygon central = new Polygon();
        central.addPoint(267,418);
        central.addPoint(188,365);  
        central.addPoint(191,346);
        central.addPoint(156,365);
        central.addPoint(155,383);
        central.addPoint(184,391);
        central.addPoint(267,429);
        PolygonButton centralBtn = new PolygonButton(central, "centralamerica");
        f.getContentPane().add(centralBtn);
        
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("zones.txt"));
        out.writeObject(centralBtn);
        */
        
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("zones.txt")); //centralamerika finns inne än så länge
        for(int i=0; i<1; i++){
            Object object = in.readObject();
            if (object != null) {
                f.getContentPane().add((PolygonButton) object);
            }
        }
        
        //SOUTH AMERICA
        Polygon sa1 = new Polygon();
        sa1.addPoint(268,418);
        sa1.addPoint(328,414);
        sa1.addPoint(378,451);
        sa1.addPoint(313,469);
        sa1.addPoint(272,434);
        PolygonButton sa1Btn = new PolygonButton(sa1, "sa1");
        f.getContentPane().add(sa1Btn);
        
        Polygon brazil = new Polygon();
        brazil.addPoint(378,451);
        brazil.addPoint(428,475);
        brazil.addPoint(415,538);
        brazil.addPoint(392,542);
        brazil.addPoint(382,559);
        brazil.addPoint(313,469);
        PolygonButton brazilBtn = new PolygonButton(brazil, "brazil");
        f.getContentPane().add(brazilBtn);
        
        Polygon sa3 = new Polygon();
        sa3.addPoint(272,434);
        sa3.addPoint(313,469);
        sa3.addPoint(354,523);
        sa3.addPoint(296,523);
        sa3.addPoint(249,487);
        PolygonButton sa3Btn = new PolygonButton(sa3, "sa3");
        f.getContentPane().add(sa3Btn);
        
        Polygon argentina = new Polygon();
        argentina.addPoint(330,693);
        argentina.addPoint(297,667);
        argentina.addPoint(296,523);
        argentina.addPoint(355,523);
        argentina.addPoint(382,559);
        argentina.addPoint(330,626);
        PolygonButton argentinaBtn = new PolygonButton(argentina, "argentina");
        f.getContentPane().add(argentinaBtn);
        
        // AFRICA
        /*
        Polygon northAF = new Polygon();
        northAF.addPoint(330,693);
        northAF.addPoint(297,667);
        northAF.addPoint(296,523);
        northAF.addPoint(355,523);
        northAF.addPoint(355,523);
        northAF.addPoint(382,559);
        northAF.addPoint(330,626);
        PolygonButton northAFBtn = new PolygonButton(northAF, "north africa");
        f.getContentPane().add(northAFBtn);
        */
        
        WindowListener wndCloser = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      };
      f.addWindowListener(wndCloser);
      f.setVisible(true);
      f.pack();
      f.setResizable(true);  
    }
}
