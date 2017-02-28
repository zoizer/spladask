package risk.ui;

import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class WorldMapInit  {
    JFrame f = new JFrame();
    
    
    public WorldMapInit()throws FileNotFoundException, IOException, ClassNotFoundException {
        
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
        
        Polygon greenland = new Polygon();
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
        
        /*Polygon central = new Polygon();
        central.addPoint(267,418);
        central.addPoint(188,365);  
        central.addPoint(191,346);
        central.addPoint(156,365);
        central.addPoint(155,383);
        central.addPoint(184,391);
        central.addPoint(267,429);
        
        
        PolygonButton centralBtn = new PolygonButton(central, "centralamerica");
        f.getContentPane().add(centralBtn);
        */
       
        //ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("zones.txt"));
        //out.writeObject(centralBtn);
        
        /*ObjectInputStream in = new ObjectInputStream(new FileInputStream("zones.txt")); 
        
        for(int i=0; i<1; i++){
            Object object = in.readObject();
            if (object != null) {
                f.getContentPane().add((PolygonButton) object);
            }
        }*/
        
        
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
        Polygon northAF = new Polygon();
        northAF.addPoint(596,312);
        northAF.addPoint(683,337);
        northAF.addPoint(672,421);
        northAF.addPoint(577,430);
        northAF.addPoint(532,438);
        northAF.addPoint(503,400);
        northAF.addPoint(527,326);
        PolygonButton northAFBtn = new PolygonButton(northAF, "north africa");
        f.getContentPane().add(northAFBtn);
        
        Polygon af2 = new Polygon();
        af2.addPoint(683,337);
        af2.addPoint(742,419);
        af2.addPoint(672,421);
        PolygonButton af2Btn = new PolygonButton(af2, "af2");
        f.getContentPane().add(af2Btn);
        
        Polygon af3 = new Polygon();
        af3.addPoint(742,419);
        af3.addPoint(768,415);
        af3.addPoint(724,478);
        af3.addPoint(728,509);
        af3.addPoint(675,505);
        af3.addPoint(672,421);
        PolygonButton af3Btn = new PolygonButton(af3, "af3");
        f.getContentPane().add(af3Btn);
        
        Polygon af4 = new Polygon();
        af4.addPoint(672,422);
        af4.addPoint(675,506);
        af4.addPoint(626,503);
        af4.addPoint(606,441);
        af4.addPoint(579,430);
        PolygonButton af4Btn = new PolygonButton(af4, "af4");
        f.getContentPane().add(af4Btn);
        
        Polygon southAF = new Polygon();
        southAF.addPoint(626,503);
        southAF.addPoint(727,509);
        southAF.addPoint(688,528);
        southAF.addPoint(705,546);
        southAF.addPoint(645,586);
        southAF.addPoint(616,528);
        PolygonButton southAFBtn = new PolygonButton(southAF, "south africa");
        f.getContentPane().add(southAFBtn);
        
        Polygon madagascar = new Polygon();
        madagascar.addPoint(742,518);
        madagascar.addPoint(762,501);
        madagascar.addPoint(751,546);
        PolygonButton madagascarBtn = new PolygonButton(madagascar, "madagascar");
        f.getContentPane().add(madagascarBtn);
        
        //EUROPE
        Polygon iceland = new Polygon();
        iceland.addPoint(521,166);
        iceland.addPoint(525,173);
        iceland.addPoint(505,183);
        iceland.addPoint(491,169);
        PolygonButton icelandBtn = new PolygonButton(iceland, "iceland");
        f.getContentPane().add(icelandBtn);
        
        Polygon gb = new Polygon();
        gb.addPoint(553,213);
        gb.addPoint(553,257);
        gb.addPoint(574,243);
        PolygonButton gbBtn = new PolygonButton(gb, "great britain");
        f.getContentPane().add(gbBtn);
        
        
        Polygon northEU = new Polygon();
        northEU.addPoint(599,220);
        northEU.addPoint(606,238);
        northEU.addPoint(617,237);
        northEU.addPoint(671,201);
        northEU.addPoint(613,274);
        northEU.addPoint(585,243);
        PolygonButton northEUBtn = new PolygonButton(northEU, "north europe");
        f.getContentPane().add(northEUBtn);
        
        
        Polygon southEU = new Polygon();
        southEU.addPoint(551,271);
        southEU.addPoint(585,283);
        southEU.addPoint(555,311);
        southEU.addPoint(534,312);
        southEU.addPoint(534,290);
        southEU.addPoint(551,287);
        PolygonButton southEUBtn = new PolygonButton(southEU, "south europe");
        f.getContentPane().add(southEUBtn);
        
        Polygon westEU = new Polygon();
        westEU.addPoint(585,243);
        westEU.addPoint(613,274);
        westEU.addPoint(631,302);
        westEU.addPoint(552,270);
        PolygonButton westEUBtn = new PolygonButton(westEU, "west europe");
        f.getContentPane().add(westEUBtn);
        
        Polygon ukraine = new Polygon();
        ukraine.addPoint(671,200);
        ukraine.addPoint(672,286);
        ukraine.addPoint(653,313);
        ukraine.addPoint(628,278);
        ukraine.addPoint(613,274);
        PolygonButton ukraineBtn = new PolygonButton(ukraine, "ukraine");
        f.getContentPane().add(ukraineBtn);
        
        Polygon scandinavia = new Polygon();
        scandinavia.addPoint(588,217);
        scandinavia.addPoint(588,203);
        scandinavia.addPoint(580,196);
        scandinavia.addPoint(605,178);
        scandinavia.addPoint(608,161);
        scandinavia.addPoint(650,133);
        scandinavia.addPoint(697,151);
        scandinavia.addPoint(689,163);
        scandinavia.addPoint(671,157);
        scandinavia.addPoint(687,175);
        scandinavia.addPoint(669,174);
        scandinavia.addPoint(671,200);
        scandinavia.addPoint(638,196);
        scandinavia.addPoint(649,169);
        scandinavia.addPoint(634,169);
        scandinavia.addPoint(618,226);
        scandinavia.addPoint(600,212);
        PolygonButton scandinaviaBtn = new PolygonButton(scandinavia, "scandinavia");
        f.getContentPane().add(scandinaviaBtn);
        
        //ASIA
        Polygon ural = new Polygon();
        ural.addPoint(672,200);
        ural.addPoint(705,147);
        ural.addPoint(740,146);
        ural.addPoint(780,101);
        ural.addPoint(796,153);
        ural.addPoint(880,169);
        ural.addPoint(874,234);
        PolygonButton uralBtn = new PolygonButton(ural, "ural");
        f.getContentPane().add(uralBtn);
        
        Polygon siberia = new Polygon();
        siberia.addPoint(880,168);
        siberia.addPoint(796,153);
        siberia.addPoint(806,128);
        siberia.addPoint(787,100);
        siberia.addPoint(842,37);
        siberia.addPoint(888,44);
        siberia.addPoint(880,75);
        PolygonButton siberiaBtn = new PolygonButton(siberia, "siberia");
        f.getContentPane().add(siberiaBtn);
        
        Polygon yakutsk = new Polygon();
        yakutsk.addPoint(880,168);
        yakutsk.addPoint(880,75);
        yakutsk.addPoint(985,65);
        yakutsk.addPoint(1056,162);
        PolygonButton yakutskBtn = new PolygonButton(yakutsk, "yakutsk");
        f.getContentPane().add(yakutskBtn);
        
        Polygon kamchatka = new Polygon();
        kamchatka.addPoint(986,65);
        kamchatka.addPoint(986,52);
        kamchatka.addPoint(1103,55);
        kamchatka.addPoint(1094,44);
        kamchatka.addPoint(1172,46);
        kamchatka.addPoint(1155,138);
        kamchatka.addPoint(1145,130);
        kamchatka.addPoint(1136,165);
        kamchatka.addPoint(1145,176);
        kamchatka.addPoint(1142,206);
        kamchatka.addPoint(1116,180);
        kamchatka.addPoint(1121,159);
        kamchatka.addPoint(1056,161);
        PolygonButton kamchatkaBtn = new PolygonButton(kamchatka, "kamchatka");
        f.getContentPane().add(kamchatkaBtn);
        
        Polygon jakutsk = new Polygon();
        jakutsk.addPoint(1056,162);
        jakutsk.addPoint(1052,192);
        jakutsk.addPoint(1074,197);
        jakutsk.addPoint(1084,223);
        jakutsk.addPoint(1077,261);
        jakutsk.addPoint(1060,268);
        jakutsk.addPoint(875,234);
        jakutsk.addPoint(880,169);
        PolygonButton jakutskBtn = new PolygonButton(jakutsk, "jakutsk");
        f.getContentPane().add(jakutskBtn);
        
        Polygon afghanistan = new Polygon();
        afghanistan.addPoint(874,235);
        afghanistan.addPoint(851,324);
        afghanistan.addPoint(779,314);
        afghanistan.addPoint(757,263);
        afghanistan.addPoint(747,267);
        afghanistan.addPoint(751,291);
        afghanistan.addPoint(725,292);
        afghanistan.addPoint(689,270);
        afghanistan.addPoint(672,286);
        afghanistan.addPoint(672,200);
        PolygonButton afghanistanBtn = new PolygonButton(afghanistan, "afghanistan");
        f.getContentPane().add(afghanistanBtn);
        
        Polygon middleEast = new Polygon();
        middleEast.addPoint(673,294);
        middleEast.addPoint(751,291);
        middleEast.addPoint(754,311);
        middleEast.addPoint(851,324);
        middleEast.addPoint(839,366);
        middleEast.addPoint(752,342);
        middleEast.addPoint(765,365);
        middleEast.addPoint(802,367);
        middleEast.addPoint(780,393);
        middleEast.addPoint(746,406);
        middleEast.addPoint(688,334);
        middleEast.addPoint(705,314);
        middleEast.addPoint(669,312);
        PolygonButton middleEastBtn = new PolygonButton(middleEast, "middle east");
        f.getContentPane().add(middleEastBtn);
        
        Polygon india = new Polygon();
        india.addPoint(852,324);
        india.addPoint(933,360);
        india.addPoint(879,423);
        india.addPoint(839,366);
        PolygonButton indiaBtn = new PolygonButton(india, "india");
        f.getContentPane().add(indiaBtn);
        
        Polygon siam = new Polygon();
        siam.addPoint(934,361);
        siam.addPoint(1030,359);
        siam.addPoint(990,386);
        siam.addPoint(1003,407);
        siam.addPoint(990,421);
        siam.addPoint(965,406);
        PolygonButton siamBtn = new PolygonButton(siam, "siam");
        f.getContentPane().add(siamBtn);
        
        Polygon china = new Polygon();
        china.addPoint(852,324);
        china.addPoint(1024,282);
        china.addPoint(1027,310);
        china.addPoint(1042,334);
        china.addPoint(1030,359);
        china.addPoint(933,360);
        PolygonButton chinaBtn = new PolygonButton(china, "china");
        f.getContentPane().add(chinaBtn);
        
        Polygon mongolia = new Polygon();
        mongolia.addPoint(1060,268);
        mongolia.addPoint(1062,310);
        mongolia.addPoint(1024,282);
        mongolia.addPoint(852,324);
        mongolia.addPoint(875,234);
        PolygonButton mongoliaBtn = new PolygonButton(mongolia, "mongolia");
        f.getContentPane().add(mongoliaBtn);
        
        Polygon japan = new Polygon();
        japan.addPoint(1094,245);
        japan.addPoint(1125,259);
        japan.addPoint(1080,313);
        PolygonButton japanBtn = new PolygonButton(japan, "japan");
        f.getContentPane().add(japanBtn);
        
        //OCEANIA
        
        Polygon indonesia = new Polygon();
        indonesia.addPoint(1070,484);
        indonesia.addPoint(1053,498);
        indonesia.addPoint(991,482);
        PolygonButton indonesiaBtn = new PolygonButton(indonesia, "indonesia");
        f.getContentPane().add(indonesiaBtn);
        
        Polygon newGuinea = new Polygon();
        newGuinea.addPoint(1088,457);
        newGuinea.addPoint(1147,473);
        newGuinea.addPoint(1161,492);
        newGuinea.addPoint(1132,492);
        PolygonButton newGuineaBtn = new PolygonButton(newGuinea, "new guinea");
        f.getContentPane().add(newGuineaBtn);
        
        Polygon westAUS = new Polygon();
        westAUS.addPoint(1094,500);
        westAUS.addPoint(1094,601);
        westAUS.addPoint(1074,578);
        westAUS.addPoint(1017,598);
        westAUS.addPoint(1017,540);
        westAUS.addPoint(1072,512);
        westAUS.addPoint(1079,514);
        PolygonButton westAUSBtn = new PolygonButton(westAUS, "west autralia");
        f.getContentPane().add(westAUSBtn);
        
        Polygon eastAUS = new Polygon();
        eastAUS.addPoint(1094,500);
        eastAUS.addPoint(1121,526);
        eastAUS.addPoint(1125,526);
        eastAUS.addPoint(1133,499);
        eastAUS.addPoint(1143,525);
        eastAUS.addPoint(1170,567);
        eastAUS.addPoint(1140,625);
        eastAUS.addPoint(1114,625);
        eastAUS.addPoint(1094,602); 
        PolygonButton eastAUSBtn = new PolygonButton(eastAUS, "east australia");
        f.getContentPane().add(eastAUSBtn);
        
        
        
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