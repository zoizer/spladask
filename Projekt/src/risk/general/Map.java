package risk.general;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Map implements Serializable {
	private static final long serialVersionUID = 3553692940163187630L;
	private ArrayList<Zone> zones;
	private Image img;
	
	private Map(ArrayList<Zone> zones, Image img) {
		this.zones = zones;
		this.img = img;
	}
	
	public static Map loadMap(String name) {
		Map map = null;
		try {
			FileInputStream fileIn = new FileInputStream(name + ".map");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			map = (Map)in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			// Map not found.
		}
		
		return map;
	}
	
	public static void createMap(String name) {
		ArrayList<Zone> zones = new ArrayList<Zone>();
		
        Polygon central = new Polygon();
        central.addPoint(267,418);
        central.addPoint(188,365);  
        central.addPoint(191,346);
        central.addPoint(156,365);
        central.addPoint(155,383);
        central.addPoint(184,391);
        central.addPoint(267,429);
		zones.add(new Zone("Central America", central, Arrays.asList(7,9), 3));
		
		Polygon alaska = new Polygon();
        alaska.addPoint(42,60);
        alaska.addPoint(84,51);
        alaska.addPoint(101,164);
        alaska.addPoint(49,138);
        alaska.addPoint(21,164);
        alaska.addPoint(1,121);
		zones.add(new Zone("Alaska", alaska, Arrays.asList(2,4,29), 3));
        
        Polygon nw = new Polygon();
        nw.addPoint(84, 51);
        nw.addPoint(191, 83);
        nw.addPoint(215,53);
        nw.addPoint(287,113);
        nw.addPoint(312, 127);
        nw.addPoint(96,128);
		zones.add(new Zone("NW Territory", nw, Arrays.asList(1,4,5), 3));
        
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
		zones.add(new Zone("Greenland", greenland, Arrays.asList(6,19), 3));
          
        Polygon n1 = new Polygon();
        n1.addPoint(96,128);
        n1.addPoint(197,128);  
        n1.addPoint(202,203);
        n1.addPoint(110,224);
		zones.add(new Zone("Alberta", n1, Arrays.asList(1,2,5,7), 3));
    
        Polygon n2 = new Polygon();
        n2.addPoint(197,128);
        n2.addPoint(312,128);  
        n2.addPoint(261,162);
        n2.addPoint(243,193);
        n2.addPoint(202,203);
		zones.add(new Zone("Ontario", n2, Arrays.asList(2,4,8), 3));
        
        Polygon quebec = new Polygon();
        quebec.addPoint(310,173);
        quebec.addPoint(347,194);
        quebec.addPoint(375,253);
        quebec.addPoint(294,228);  
		zones.add(new Zone("Quebec", quebec, Arrays.asList(3, 8), 3));
        
        Polygon westUS = new Polygon();
        westUS.addPoint(202,203);
        westUS.addPoint(191,346);
        westUS.addPoint(157,365);
        westUS.addPoint(102,313);
        westUS.addPoint(110,225); 
		zones.add(new Zone("West US", westUS, Arrays.asList(4,8,0), 3));
        
        Polygon eastUS = new Polygon();
        eastUS.addPoint(202,203);
        eastUS.addPoint(244,193);
        eastUS.addPoint(294,229);
        eastUS.addPoint(375,253);
        eastUS.addPoint(250,337);
        eastUS.addPoint(192,346);
		zones.add(new Zone("East US", eastUS, Arrays.asList(5,6,7), 3));
        
       
        
        //SOUTH AMERICA
        Polygon sa1 = new Polygon();
        sa1.addPoint(268,418);
        sa1.addPoint(328,414);
        sa1.addPoint(378,451);
        sa1.addPoint(313,469);
        sa1.addPoint(272,434);
		zones.add(new Zone("Venezuela", sa1, Arrays.asList(0,11,10), 3));
        
        Polygon brazil = new Polygon();
        brazil.addPoint(378,451);
        brazil.addPoint(428,475);
        brazil.addPoint(415,538);
        brazil.addPoint(392,542);
        brazil.addPoint(382,559);
        brazil.addPoint(313,469);
		zones.add(new Zone("Brazil", brazil, Arrays.asList(9,11,12,13), 3));
        
        Polygon sa3 = new Polygon();
        sa3.addPoint(272,434);
        sa3.addPoint(313,469);
        sa3.addPoint(354,523);
        sa3.addPoint(296,523);
        sa3.addPoint(249,487);
		zones.add(new Zone("Peru", sa3, Arrays.asList(9,10,12), 3));
        
        Polygon argentina = new Polygon();
        argentina.addPoint(330,693);
        argentina.addPoint(297,667);
        argentina.addPoint(296,523);
        argentina.addPoint(355,523);
        argentina.addPoint(382,559);
        argentina.addPoint(330,626);
		zones.add(new Zone("Argentina", argentina, Arrays.asList(11,10), 3));
        
        // AFRICA
        Polygon northAF = new Polygon();
        northAF.addPoint(596,312);
        northAF.addPoint(683,337);
        northAF.addPoint(672,421);
        northAF.addPoint(577,430);
        northAF.addPoint(532,438);
        northAF.addPoint(503,400);
        northAF.addPoint(527,326);
		zones.add(new Zone("North Africa", northAF, Arrays.asList(10,16,14,22), 3));
        
        Polygon af2 = new Polygon();
        af2.addPoint(683,337);
        af2.addPoint(742,419);
        af2.addPoint(672,421);
		zones.add(new Zone("NE Africa", af2, Arrays.asList(13,15,32), 3));
        
        Polygon af3 = new Polygon();
        af3.addPoint(742,419);
        af3.addPoint(768,415);
        af3.addPoint(724,478);
        af3.addPoint(728,509);
        af3.addPoint(675,505);
        af3.addPoint(672,421);
		zones.add(new Zone("SE Africa", af3, Arrays.asList(16,14,17,18), 3));
        
        Polygon af4 = new Polygon();
        af4.addPoint(672,422);
        af4.addPoint(675,506);
        af4.addPoint(626,503);
        af4.addPoint(606,441);
        af4.addPoint(579,430);
		zones.add(new Zone("Congo", af4, Arrays.asList(13,15,17), 3));
        
        Polygon southAF = new Polygon();
        southAF.addPoint(626,503);
        southAF.addPoint(727,509);
        southAF.addPoint(688,528);
        southAF.addPoint(705,546);
        southAF.addPoint(645,586);
        southAF.addPoint(616,528);
		zones.add(new Zone("South Africa", southAF, Arrays.asList(16,15,18), 3));
        
        Polygon madagascar = new Polygon();
        madagascar.addPoint(742,518);
        madagascar.addPoint(762,501);
        madagascar.addPoint(751,546);
		zones.add(new Zone("Madagascar", madagascar, Arrays.asList(17,15), 3));
        
        //EUROPE
        Polygon iceland = new Polygon();
        iceland.addPoint(521,166);
        iceland.addPoint(525,173);
        iceland.addPoint(505,183);
        iceland.addPoint(491,169);
		zones.add(new Zone("Iceland", iceland, Arrays.asList(3,20,25), 3));
        
        Polygon gb = new Polygon();
        gb.addPoint(553,213);
        gb.addPoint(553,257);
        gb.addPoint(574,243);
		zones.add(new Zone("Great Britain", gb, Arrays.asList(19,23), 3));
        
        
        Polygon northEU = new Polygon();
        northEU.addPoint(599,220);
        northEU.addPoint(606,238);
        northEU.addPoint(617,237);
        northEU.addPoint(671,201);
        northEU.addPoint(613,274);
        northEU.addPoint(585,243);
		zones.add(new Zone("North Europe", northEU, Arrays.asList(25,24,23), 3));
        
        
        Polygon southEU = new Polygon();
        southEU.addPoint(551,271);
        southEU.addPoint(585,283);
        southEU.addPoint(555,311);
        southEU.addPoint(534,312);
        southEU.addPoint(534,290);
        southEU.addPoint(551,287);
		zones.add(new Zone("South Europe", southEU, Arrays.asList(23,13), 3));
        
        Polygon westEU = new Polygon();
        westEU.addPoint(585,243);
        westEU.addPoint(613,274);
        westEU.addPoint(631,302);
        westEU.addPoint(552,270);
		zones.add(new Zone("West Europe", westEU, Arrays.asList(20,21,22), 3));
        
        Polygon ukraine = new Polygon();
        ukraine.addPoint(671,200);
        ukraine.addPoint(672,286);
        ukraine.addPoint(653,313);
        ukraine.addPoint(628,278);
        ukraine.addPoint(613,274);
		zones.add(new Zone("Ukraine", ukraine, Arrays.asList(21,31,32), 3));
        
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
		zones.add(new Zone("Scandinavia", scandinavia, Arrays.asList(21,19,26), 3));
        
        //ASIA
        Polygon ural = new Polygon();
        ural.addPoint(672,200);
        ural.addPoint(705,147);
        ural.addPoint(740,146);
        ural.addPoint(780,101);
        ural.addPoint(796,153);
        ural.addPoint(880,169);
        ural.addPoint(874,234);
		zones.add(new Zone("Ural", ural, Arrays.asList(25,27,30,31), 3));
        
        Polygon siberia = new Polygon();
        siberia.addPoint(880,168);
        siberia.addPoint(796,153);
        siberia.addPoint(806,128);
        siberia.addPoint(787,100);
        siberia.addPoint(842,37);
        siberia.addPoint(888,44);
        siberia.addPoint(880,75);
		zones.add(new Zone("Siberia", siberia, Arrays.asList(26,28), 3));
        
        Polygon yakutsk = new Polygon();
        yakutsk.addPoint(880,168);
        yakutsk.addPoint(880,75);
        yakutsk.addPoint(985,65);
        yakutsk.addPoint(1056,162);
		zones.add(new Zone("Yakutsk", yakutsk, Arrays.asList(27,29,30), 3));
        
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
		zones.add(new Zone("Kamchatka", kamchatka, Arrays.asList(1,28), 3));
        
        Polygon jakutsk = new Polygon();
        jakutsk.addPoint(1056,162);
        jakutsk.addPoint(1052,192);
        jakutsk.addPoint(1074,197);
        jakutsk.addPoint(1084,223);
        jakutsk.addPoint(1077,261);
        jakutsk.addPoint(1060,268);
        jakutsk.addPoint(875,234);
        jakutsk.addPoint(880,169);
		zones.add(new Zone("Manchuria", jakutsk, Arrays.asList(26,28,36,37), 3));
        
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
		zones.add(new Zone("Kazakhstan", afghanistan, Arrays.asList(24,26,36,32), 3));
        
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
		zones.add(new Zone("Middle East", middleEast, Arrays.asList(24,31,33,14), 3));
        
        Polygon india = new Polygon();
        india.addPoint(852,324);
        india.addPoint(933,360);
        india.addPoint(879,423);
        india.addPoint(839,366);
		zones.add(new Zone("India", india, Arrays.asList(32,34,35), 3));
        
        Polygon siam = new Polygon();
        siam.addPoint(934,361);
        siam.addPoint(1030,359);
        siam.addPoint(990,386);
        siam.addPoint(1003,407);
        siam.addPoint(990,421);
        siam.addPoint(965,406);
		zones.add(new Zone("Siam", siam, Arrays.asList(33,35,38,39), 3));
        
        Polygon china = new Polygon();
        china.addPoint(852,324);
        china.addPoint(1024,282);
        china.addPoint(1027,310);
        china.addPoint(1042,334);
        china.addPoint(1030,359);
        china.addPoint(933,360);
		zones.add(new Zone("China", china, Arrays.asList(33,34,36,37), 3));
        
        Polygon mongolia = new Polygon();
        mongolia.addPoint(1060,268);
        mongolia.addPoint(1062,310);
        mongolia.addPoint(1024,282);
        mongolia.addPoint(852,324);
        mongolia.addPoint(875,234);
		zones.add(new Zone("Mongolia", mongolia, Arrays.asList(30,31,35,37), 3));
        
        Polygon japan = new Polygon();
        japan.addPoint(1094,245);
        japan.addPoint(1125,259);
        japan.addPoint(1080,313);
		zones.add(new Zone("Japan", japan, Arrays.asList(30,35,36), 3));
        
        //OCEANIA
        
        Polygon indonesia = new Polygon();
        indonesia.addPoint(1070,484);
        indonesia.addPoint(1053,498);
        indonesia.addPoint(991,482);
		zones.add(new Zone("Indonesia", indonesia, Arrays.asList(34,39,40), 3));
        
        Polygon newGuinea = new Polygon();
        newGuinea.addPoint(1088,457);
        newGuinea.addPoint(1147,473);
        newGuinea.addPoint(1161,492);
        newGuinea.addPoint(1132,492);
		zones.add(new Zone("New Guinea", newGuinea, Arrays.asList(34,38,41), 3));
        
        Polygon westAUS = new Polygon();
        westAUS.addPoint(1094,500);
        westAUS.addPoint(1094,601);
        westAUS.addPoint(1074,578);
        westAUS.addPoint(1017,598);
        westAUS.addPoint(1017,540);
        westAUS.addPoint(1072,512);
        westAUS.addPoint(1079,514);
		zones.add(new Zone("West Australia", westAUS, Arrays.asList(38,41), 3));
        
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
		zones.add(new Zone("East Australia", eastAUS, Arrays.asList(39,40), 3));
		
		try {
			BufferedImage bi;
			bi = ImageIO.read(new File("sistariskcolored.png"));
			Image img = new Image();
			img.set(bi);
			
			Map map = new Map(zones, img);
			
			FileOutputStream fileOut = new FileOutputStream(name + ".map");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(map);
        	out.close();
        	fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			// COULD NOT FIND IMAGE BASE.
		}
		
	}
	
	public void saveMap(String name) {
		try {
        	FileOutputStream fileOut = new FileOutputStream(name + ".map");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(this);
        	out.close();
        	fileOut.close();
        } catch(Exception e) {
        	e.printStackTrace();
        	// Unknown error.
        }
	}
	
	public void setZone(Zone z, int i) {
		synchronized (zones) {
			zones.set(i, z);
		}
	}
	
	public Zone getZone(int index) {
		return zones.get(index);
	}
	
	public Zone getZone(Point p) {
		synchronized (zones) {
			for (Zone z : zones) {
				if (z.contains(p)) return z;
			}
		}
		
		return null;
	}
	
	public int getZoneId(Point p) {
		synchronized (zones) {
			for (int i = 0; i < zones.size(); i++) {
				if (zones.get(i).contains(p)) return i;
			}
		}
		
		return -1;
	}
	
	public int getZoneCount() {
		return zones.size();
	}
	
	public BufferedImage getImg() {
		return img.get();
	}
}
