package risk.testing;

import java.awt.Polygon;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import risk.game.Zone;
import risk.ui.ZoneButton;


/**
 * GUI manages ALL graphics.
 * 
 * 
 * @author 		Emir Zivcic
 * @version 	02/03
 */
public class Editor {
	public static void CREATE_MAP(String name) {
		ArrayList<ZoneButton> gzone;
		HashMap<Integer, Zone> zone;
		
		gzone = new ArrayList<ZoneButton>();
		zone = new HashMap<Integer, Zone>();
		
		Polygon alaska = new Polygon();
        alaska.addPoint(42,60);
        alaska.addPoint(84,51);
        alaska.addPoint(101,164);
        alaska.addPoint(49,138);
        alaska.addPoint(21,164);
        alaska.addPoint(1,121);
        ZoneButton alaskaBtn = new ZoneButton(alaska, "Alaska", 1, 3);
        gzone.add(alaskaBtn);
        Zone z = new Zone(3);
        z.AddNeighbour(2,4,29);
        zone.put(1, z);
        
        Polygon nw = new Polygon();
        nw.addPoint(84, 51);
        nw.addPoint(191, 83);
        nw.addPoint(215,53);
        nw.addPoint(287,113);
        nw.addPoint(312, 127);
        nw.addPoint(96,128);
        ZoneButton nwBtn = new ZoneButton(nw, "Northwest", 2, 3);
        gzone.add(nwBtn);
        z = new Zone(3);
        z.AddNeighbour(1,4,5);
        zone.put(2, z);
        
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
        ZoneButton greenlandBtn = new ZoneButton(greenland, "greenland", 3, 3);
        gzone.add(greenlandBtn);
        z = new Zone(3);
        z.AddNeighbour(6,19);
        zone.put(3, z);
          
        Polygon n1 = new Polygon();
        n1.addPoint(96,128);
        n1.addPoint(197,128);  
        n1.addPoint(202,203);
        n1.addPoint(110,224);
        ZoneButton n1Btn = new ZoneButton(n1, "n1", 4, 3);
        gzone.add(n1Btn);
        z = new Zone(3);
        z.AddNeighbour(1,2,5,7);
        zone.put(4, z);
    
        Polygon n2 = new Polygon();
        n2.addPoint(197,128);
        n2.addPoint(312,128);  
        n2.addPoint(261,162);
        n2.addPoint(243,193);
        n2.addPoint(202,203);
        ZoneButton n2Btn = new ZoneButton(n2, "n2", 5, 3);
        gzone.add(n2Btn);
        z = new Zone(3);
        z.AddNeighbour(2,4,8);
        zone.put(5, z);
        
        Polygon quebec = new Polygon();
        quebec.addPoint(310,173);
        quebec.addPoint(347,194);
        quebec.addPoint(375,253);
        quebec.addPoint(294,228);  
        ZoneButton quebecBtn = new ZoneButton(quebec, "quebec", 6, 3);
        gzone.add(quebecBtn);
        z = new Zone(3);
        z.AddNeighbour(3,8);
        zone.put(6, z);
        
        Polygon westUS = new Polygon();
        westUS.addPoint(202,203);
        westUS.addPoint(191,346);
        westUS.addPoint(157,365);
        westUS.addPoint(102,313);
        westUS.addPoint(110,225); 
        ZoneButton westUSBtn = new ZoneButton(westUS, "westUS", 7, 3);
        gzone.add(westUSBtn);
        z = new Zone(3);
        z.AddNeighbour(4,8,42);
        zone.put(7, z);
        
        Polygon eastUS = new Polygon();
        eastUS.addPoint(202,203);
        eastUS.addPoint(244,193);
        eastUS.addPoint(294,229);
        eastUS.addPoint(375,253);
        eastUS.addPoint(250,337);
        eastUS.addPoint(192,346);
        ZoneButton eastUSBtn = new ZoneButton(eastUS, "eastUS", 8, 3);
        gzone.add(eastUSBtn);
        z = new Zone(3);
        z.AddNeighbour(5,6,7);
        zone.put(8, z);
        
        Polygon central = new Polygon();
        central.addPoint(267,418);
        central.addPoint(188,365);  
        central.addPoint(191,346);
        central.addPoint(156,365);
        central.addPoint(155,383);
        central.addPoint(184,391);
        central.addPoint(267,429);
        ZoneButton centralBtn = new ZoneButton(central, "centralamerica", 42, 3);
        gzone.add(centralBtn);
        z = new Zone(3);
        z.AddNeighbour(7,9);
        zone.put(42, z);
       
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
        ZoneButton sa1Btn = new ZoneButton(sa1, "sa1", 9, 3);
        gzone.add(sa1Btn);
        z = new Zone(3);
        z.AddNeighbour(42,11,10);
        zone.put(9, z);
        
        Polygon brazil = new Polygon();
        brazil.addPoint(378,451);
        brazil.addPoint(428,475);
        brazil.addPoint(415,538);
        brazil.addPoint(392,542);
        brazil.addPoint(382,559);
        brazil.addPoint(313,469);
        ZoneButton brazilBtn = new ZoneButton(brazil, "brazil", 10, 3);
        gzone.add(brazilBtn);
        z = new Zone(3);
        z.AddNeighbour(9,11,12,13);
        zone.put(10, z);
        
        Polygon sa3 = new Polygon();
        sa3.addPoint(272,434);
        sa3.addPoint(313,469);
        sa3.addPoint(354,523);
        sa3.addPoint(296,523);
        sa3.addPoint(249,487);
        ZoneButton sa3Btn = new ZoneButton(sa3, "sa3", 11, 3);
        gzone.add(sa3Btn);
        z = new Zone(3);
        z.AddNeighbour(9,10,12);
        zone.put(11, z);
        
        Polygon argentina = new Polygon();
        argentina.addPoint(330,693);
        argentina.addPoint(297,667);
        argentina.addPoint(296,523);
        argentina.addPoint(355,523);
        argentina.addPoint(382,559);
        argentina.addPoint(330,626);
        ZoneButton argentinaBtn = new ZoneButton(argentina, "argentina", 12, 3);
        gzone.add(argentinaBtn);
        z = new Zone(3);
        z.AddNeighbour(11,10);
        zone.put(12, z);
        
        // AFRICA
        Polygon northAF = new Polygon();
        northAF.addPoint(596,312);
        northAF.addPoint(683,337);
        northAF.addPoint(672,421);
        northAF.addPoint(577,430);
        northAF.addPoint(532,438);
        northAF.addPoint(503,400);
        northAF.addPoint(527,326);
        ZoneButton northAFBtn = new ZoneButton(northAF, "north africa", 13, 3);
        gzone.add(northAFBtn);
        z = new Zone(3);
        z.AddNeighbour(10,16,14,22);
        zone.put(13, z);
        
        Polygon af2 = new Polygon();
        af2.addPoint(683,337);
        af2.addPoint(742,419);
        af2.addPoint(672,421);
        ZoneButton af2Btn = new ZoneButton(af2, "af2", 14, 3);
        gzone.add(af2Btn);
        z = new Zone(3);
        z.AddNeighbour(13,15,32);
        zone.put(14, z);
        
        Polygon af3 = new Polygon();
        af3.addPoint(742,419);
        af3.addPoint(768,415);
        af3.addPoint(724,478);
        af3.addPoint(728,509);
        af3.addPoint(675,505);
        af3.addPoint(672,421);
        ZoneButton af3Btn = new ZoneButton(af3, "af3", 15, 3);
        gzone.add(af3Btn);
        z = new Zone(3);
        z.AddNeighbour(16,14,17,18);
        zone.put(15, z);
        
        Polygon af4 = new Polygon();
        af4.addPoint(672,422);
        af4.addPoint(675,506);
        af4.addPoint(626,503);
        af4.addPoint(606,441);
        af4.addPoint(579,430);
        ZoneButton af4Btn = new ZoneButton(af4, "af4", 16, 3);
        gzone.add(af4Btn);
        z = new Zone(3);
        z.AddNeighbour(13,15,17);
        zone.put(16, z);
        
        Polygon southAF = new Polygon();
        southAF.addPoint(626,503);
        southAF.addPoint(727,509);
        southAF.addPoint(688,528);
        southAF.addPoint(705,546);
        southAF.addPoint(645,586);
        southAF.addPoint(616,528);
        ZoneButton southAFBtn = new ZoneButton(southAF, "south africa", 17, 3);
        gzone.add(southAFBtn);
        z = new Zone(3);
        z.AddNeighbour(16,15,18);
        zone.put(17, z);
        
        Polygon madagascar = new Polygon();
        madagascar.addPoint(742,518);
        madagascar.addPoint(762,501);
        madagascar.addPoint(751,546);
        ZoneButton madagascarBtn = new ZoneButton(madagascar, "madagascar", 18, 3);
        gzone.add(madagascarBtn);
        z = new Zone(3);
        z.AddNeighbour(17,15);
        zone.put(18, z);
        
        //EUROPE
        Polygon iceland = new Polygon();
        iceland.addPoint(521,166);
        iceland.addPoint(525,173);
        iceland.addPoint(505,183);
        iceland.addPoint(491,169);
        ZoneButton icelandBtn = new ZoneButton(iceland, "iceland", 19, 3);
        gzone.add(icelandBtn);
        z = new Zone(3);
        z.AddNeighbour(3,20,25);
        zone.put(19, z);
        
        Polygon gb = new Polygon();
        gb.addPoint(553,213);
        gb.addPoint(553,257);
        gb.addPoint(574,243);
        ZoneButton gbBtn = new ZoneButton(gb, "great britain", 20, 3);
        gzone.add(gbBtn);
        z = new Zone(3);
        z.AddNeighbour(19,23);
        zone.put(20, z);
        
        
        Polygon northEU = new Polygon();
        northEU.addPoint(599,220);
        northEU.addPoint(606,238);
        northEU.addPoint(617,237);
        northEU.addPoint(671,201);
        northEU.addPoint(613,274);
        northEU.addPoint(585,243);
        ZoneButton northEUBtn = new ZoneButton(northEU, "north europe", 21, 3);
        gzone.add(northEUBtn);
        z = new Zone(3);
        z.AddNeighbour(25,24,23);
        zone.put(21, z);
        
        
        Polygon southEU = new Polygon();
        southEU.addPoint(551,271);
        southEU.addPoint(585,283);
        southEU.addPoint(555,311);
        southEU.addPoint(534,312);
        southEU.addPoint(534,290);
        southEU.addPoint(551,287);
        ZoneButton southEUBtn = new ZoneButton(southEU, "south europe", 22, 3);
        gzone.add(southEUBtn);
        z = new Zone(3);
        z.AddNeighbour(23,13);
        zone.put(22, z);
        
        Polygon westEU = new Polygon();
        westEU.addPoint(585,243);
        westEU.addPoint(613,274);
        westEU.addPoint(631,302);
        westEU.addPoint(552,270);
        ZoneButton westEUBtn = new ZoneButton(westEU, "west europe", 23, 3);
        gzone.add(westEUBtn);
        z = new Zone(3);
        z.AddNeighbour(20,21,22);
        zone.put(23, z);
        
        Polygon ukraine = new Polygon();
        ukraine.addPoint(671,200);
        ukraine.addPoint(672,286);
        ukraine.addPoint(653,313);
        ukraine.addPoint(628,278);
        ukraine.addPoint(613,274);
        ZoneButton ukraineBtn = new ZoneButton(ukraine, "ukraine", 24, 3);
        gzone.add(ukraineBtn);
        z = new Zone(3);
        z.AddNeighbour(21,31,32);
        zone.put(24, z);
        
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
        ZoneButton scandinaviaBtn = new ZoneButton(scandinavia, "scandinavia", 25, 3);
        gzone.add(scandinaviaBtn);
        z = new Zone(3);
        z.AddNeighbour(21,19,26);
        zone.put(25, z);
        
        //ASIA
        Polygon ural = new Polygon();
        ural.addPoint(672,200);
        ural.addPoint(705,147);
        ural.addPoint(740,146);
        ural.addPoint(780,101);
        ural.addPoint(796,153);
        ural.addPoint(880,169);
        ural.addPoint(874,234);
        ZoneButton uralBtn = new ZoneButton(ural, "ural", 26, 3);
        gzone.add(uralBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(26, z);
        
        Polygon siberia = new Polygon();
        siberia.addPoint(880,168);
        siberia.addPoint(796,153);
        siberia.addPoint(806,128);
        siberia.addPoint(787,100);
        siberia.addPoint(842,37);
        siberia.addPoint(888,44);
        siberia.addPoint(880,75);
        ZoneButton siberiaBtn = new ZoneButton(siberia, "siberia", 27, 3);
        gzone.add(siberiaBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(27, z);
        
        Polygon yakutsk = new Polygon();
        yakutsk.addPoint(880,168);
        yakutsk.addPoint(880,75);
        yakutsk.addPoint(985,65);
        yakutsk.addPoint(1056,162);
        ZoneButton yakutskBtn = new ZoneButton(yakutsk, "yakutsk", 28, 3);
        gzone.add(yakutskBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(28, z);
        
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
        ZoneButton kamchatkaBtn = new ZoneButton(kamchatka, "kamchatka", 29, 3);
        gzone.add(kamchatkaBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(29, z);
        
        Polygon jakutsk = new Polygon();
        jakutsk.addPoint(1056,162);
        jakutsk.addPoint(1052,192);
        jakutsk.addPoint(1074,197);
        jakutsk.addPoint(1084,223);
        jakutsk.addPoint(1077,261);
        jakutsk.addPoint(1060,268);
        jakutsk.addPoint(875,234);
        jakutsk.addPoint(880,169);
        ZoneButton jakutskBtn = new ZoneButton(jakutsk, "jakutsk", 30, 3);
        gzone.add(jakutskBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(30, z);
        
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
        ZoneButton afghanistanBtn = new ZoneButton(afghanistan, "afghanistan", 31, 3);
        gzone.add(afghanistanBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(31, z);
        
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
        ZoneButton middleEastBtn = new ZoneButton(middleEast, "middle east", 32, 3);
        gzone.add(middleEastBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(32, z);
        
        Polygon india = new Polygon();
        india.addPoint(852,324);
        india.addPoint(933,360);
        india.addPoint(879,423);
        india.addPoint(839,366);
        ZoneButton indiaBtn = new ZoneButton(india, "india", 33, 3);
        gzone.add(indiaBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(33, z);
        
        Polygon siam = new Polygon();
        siam.addPoint(934,361);
        siam.addPoint(1030,359);
        siam.addPoint(990,386);
        siam.addPoint(1003,407);
        siam.addPoint(990,421);
        siam.addPoint(965,406);
        ZoneButton siamBtn = new ZoneButton(siam, "siam", 34, 3);
        gzone.add(siamBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(34, z);
        
        Polygon china = new Polygon();
        china.addPoint(852,324);
        china.addPoint(1024,282);
        china.addPoint(1027,310);
        china.addPoint(1042,334);
        china.addPoint(1030,359);
        china.addPoint(933,360);
        ZoneButton chinaBtn = new ZoneButton(china, "china", 35, 3);
        gzone.add(chinaBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(35, z);
        
        Polygon mongolia = new Polygon();
        mongolia.addPoint(1060,268);
        mongolia.addPoint(1062,310);
        mongolia.addPoint(1024,282);
        mongolia.addPoint(852,324);
        mongolia.addPoint(875,234);
        ZoneButton mongoliaBtn = new ZoneButton(mongolia, "mongolia", 36, 3);
        gzone.add(mongoliaBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(36, z);
        
        Polygon japan = new Polygon();
        japan.addPoint(1094,245);
        japan.addPoint(1125,259);
        japan.addPoint(1080,313);
        ZoneButton japanBtn = new ZoneButton(japan, "japan", 37, 3);
        gzone.add(japanBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(37, z);
        
        //OCEANIA
        
        Polygon indonesia = new Polygon();
        indonesia.addPoint(1070,484);
        indonesia.addPoint(1053,498);
        indonesia.addPoint(991,482);
        ZoneButton indonesiaBtn = new ZoneButton(indonesia, "indonesia", 38, 3);
        gzone.add(indonesiaBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(38, z);
        
        Polygon newGuinea = new Polygon();
        newGuinea.addPoint(1088,457);
        newGuinea.addPoint(1147,473);
        newGuinea.addPoint(1161,492);
        newGuinea.addPoint(1132,492);
        ZoneButton newGuineaBtn = new ZoneButton(newGuinea, "new guinea", 39, 3);
        gzone.add(newGuineaBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(39, z);
        
        Polygon westAUS = new Polygon();
        westAUS.addPoint(1094,500);
        westAUS.addPoint(1094,601);
        westAUS.addPoint(1074,578);
        westAUS.addPoint(1017,598);
        westAUS.addPoint(1017,540);
        westAUS.addPoint(1072,512);
        westAUS.addPoint(1079,514);
        ZoneButton westAUSBtn = new ZoneButton(westAUS, "west autralia", 40, 3);
        gzone.add(westAUSBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(40, z);
        
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
        ZoneButton eastAUSBtn = new ZoneButton(eastAUS, "east australia", 41, 3);
        gzone.add(eastAUSBtn);
        z = new Zone(3);
        //z.AddNeighbour(); // NEIGHBOURS - VILKA MAN KAN NÅ FRÅN DENNA POSITION.
        zone.put(41, z);
		
        
        try {
        	FileOutputStream fileOut = new FileOutputStream(name + ".gui");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(gzone);
        	out.close();
        	fileOut.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
        	FileOutputStream fileOut = new FileOutputStream(name + ".dat");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(zone);
        	out.close();
        	fileOut.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
	}
}
