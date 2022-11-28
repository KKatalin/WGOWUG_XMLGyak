package hu.domparse.WGOWUG;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryWGOWUG {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		
			File inputFile = new File("XMLwgowug.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			// a DocumentBuilderFactory-bõl megkapjuk a DocumentBuilder-t.
			
						Document doc = dBuilder.parse(inputFile);
						
						doc.getDocumentElement().normalize();

						//Gyökér elem kiíratása
						System.out.println("Gyökér elem neve: "+ doc.getDocumentElement().getNodeName() +"\n");
						
						System.out.println("------------------------------------------------------------------------------");
						
						System.out.println("1.Lekérdzekés:" +"\n"+"Azokat a fogyasztásmérõknek az adatait kéri le amiknek a szolgáltatója az MVM:  \n");
						//Kérdezze le azokat a fogyasztásmérõket amiket az MVM szolgáltat
						NodeList nList = doc.getElementsByTagName("FogyasztásMérõ");
						
						
						for (int temp =0; temp < nList.getLength(); temp++) {
							Node nNode = nList.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("Szolgáltató").item(0);
									String szolg  = node0.getTextContent();
									//Ha igen, akkor kiíratom az adataikat
									if("MVM".equals(szolg)) 
									{String gyszam = eElement.getAttribute("GyáriSzám");
									String hid = eElement.getAttribute("K-GYSZref");
									
									
									//Lekérjük az aktuális elem gyerekelemeinek tartalmát
									Node node1 = eElement.getElementsByTagName("Szolgáltató").item(0);
									String szolg1 = node1.getTextContent();
									
									Node node2 = eElement.getElementsByTagName("Óraállás").item(0);
									String oraall = node2.getTextContent();
									
									Node node3 = eElement.getElementsByTagName("HitelesítõDátum").item(0);
									String hitdat = node3.getTextContent();
									
									
									System.out.printf("Fogyaztás mérõ gyári száma:  %s%n" , gyszam);
									System.out.printf("Ebbe az ID-jû hotelbe van a fogyasztásmérõ:  %s%n" , hid);
									System.out.printf("A fogyasztásmérõt biztosító szolgáltató:  %s%n" , szolg1);
									System.out.printf("Óraállás:  %s%n" , oraall);
									System.out.printf("Leolvasás idõpontja:  %s%n" , hitdat);
										
									}
								
							
								
							}
						}	System.out.println("------------------------------------------------------------------------------");
						
						System.out.println("2.Lekérdzekés:" +"\n"+"Azokat a beszállítók adatait kéri le akik Budapestrõl szállítják az árukat a hotelbokba:  \n");
						//Kérdezze le azokat a Beszállítókat akik Budapestrõl szállítanak be
						NodeList nList1 = doc.getElementsByTagName("Beszállító");
						
						
						for (int temp =0; temp < nList1.getLength(); temp++) {
							Node nNode = nList1.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("Város").item(0);
									String bp  = node0.getTextContent();
									//Ha igen, akkor kiíratom az adataikat
									if("Budapest".equals(bp)) {
										
									String adszam = eElement.getAttribute("Adószám");
									
									
									//Lekérjük az aktuális elem gyerekelemeinek tartalmát
									Node node1 = eElement.getElementsByTagName("Név").item(0);
									String besznev = node1.getTextContent();
									
									Node node2 = eElement.getElementsByTagName("Irányítószám").item(0);
									String birsz = node2.getTextContent();
									
									Node node3 = eElement.getElementsByTagName("Város").item(0);
									String bvaros = node3.getTextContent();
									
									Node node4 = eElement.getElementsByTagName("Utca").item(0);
									String butca = node4.getTextContent();
									
									Node node5 = eElement.getElementsByTagName("Házszám").item(0);
									String bhsz = node5.getTextContent();
									
									Node node6 = eElement.getElementsByTagName("Elérhetõség").item(0);
									String beler = node6.getTextContent();
									
									//a cím gyerekelemeinek összefûzése egy stringbe
									String bcim = birsz + bvaros  + butca + bhsz ;
									
									System.out.printf("Beszállító adószáma:  %s%n" , adszam);
									System.out.printf("Beszállító neve:  %s%n" , besznev);
									System.out.printf("Beszállító címe:  %s%n" , bcim);
									System.out.printf("Beszállítõ elérhetõsége:  %s%n" , beler);
									}
								
							
								
							}
						}	System.out.println("------------------------------------------------------------------------------");
						System.out.println("3.Lekérdzekés:" +"\n"+"Azokat a foglalások adatait listázza ki amik a Király Hotel nevû hotelba történtek:  \n");
						//Kérdezze le azokat a foglalásokat amik a kriály hotelbe történtek
						NodeList nList11 = doc.getElementsByTagName("Foglalás");
						
						
						for (int temp =0; temp < nList11.getLength(); temp++) {
							Node nNode = nList11.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("Hotel").item(0);
									String khot  = node0.getTextContent();
									//Ha igen, akkor kiíratom az adataikat
									if("Király hotel".equals(khot)) {
										
									
									String fogid = eElement.getAttribute("FoglalásID");
									String hid = eElement.getAttribute("K-Fref");
									
									
									//Lekérjük az aktuális elem gyerekelemeinek tartalmát
									Node node1 = eElement.getElementsByTagName("SzobaSzám").item(0);
									String szszam = node1.getTextContent();
									
									Node node11 = eElement.getElementsByTagName("SzobaSzám1").item(0);
									String szszam1 = node11.getTextContent();
									
									Node node111 = eElement.getElementsByTagName("SzobaSzám2").item(0);
									String szszam111 = node111.getTextContent();
									
									Node node2 = eElement.getElementsByTagName("Hotel").item(0);
									String hot = node2.getTextContent();
									
									Node node3 = eElement.getElementsByTagName("Foglaló").item(0);
									String fogl = node3.getTextContent();
									
									Node node4 = eElement.getElementsByTagName("Dátum").item(0);
									String fdat = node4.getTextContent();
									
									
									
									System.out.printf("Foglalás ID:  %s%n" , fogid);
									System.out.printf("Ebbe az ID-jû hotelbe foglaltak szobát:  %s%n" , hid);
									System.out.printf("Foglalt szobák száma:  %s%n %s%n %s%n" , szszam, szszam1, szszam111 );
									System.out.printf("Ebbe a nevû hotelbe foglaltak szobákat:  %s%n" , hot);
									System.out.printf("Foglaló teljes neve:  %s%n" , fogl);
									System.out.printf("Foglalás ideje:  %s%n" , fdat);
									
									}
								
							
								
							}
						}System.out.println("------------------------------------------------------------------------------");
						System.out.println("4.Lekérdzekés:" +"\n"+"Azokat a Hotelek adatait listázza ki amik éjjel nappal nyitva vannak:  \n");
						//Kérdezze le azokat a hoteleket amik állandóan nyitva vannak
						NodeList nList111 = doc.getElementsByTagName("Hotel");
						
						
						for (int temp =0; temp < nList111.getLength()-3; temp++) {
							Node nNode = nList111.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("Nyitvatartás").item(0);
									String nyt  = node0.getTextContent();
									//Ha igen, akkor kiíratom az adataikat
									if("00-24".equals(nyt)) {
										
										String hid = eElement.getAttribute("HotelId");
										
										//Lekérjük az aktuális elem gyerekelemeinek tartalmát
										Node node1 = eElement.getElementsByTagName("Elérhetõség").item(0);
										String elerhetoseg = node1.getTextContent();
										
										Node node2 = eElement.getElementsByTagName("Nyitvatartás").item(0);
										String nyitvatartas = node2.getTextContent();
										
										Node node3 = eElement.getElementsByTagName("Név").item(0);
										String hotelneve = node3.getTextContent();
										
										System.out.printf("Hotel ID:  %s%n" , hid);
										System.out.printf("Hotel Elérhetõsége:  %s%n" , elerhetoseg);
										System.out.printf("Hotel Nyitvatartása:  %s%n" , nyitvatartas);
										System.out.printf("Hotel Neve:  %s%n" , hotelneve);
									}
								
							
								
							}
						}System.out.println("------------------------------------------------------------------------------");
						System.out.println("5.Lekérdzekés:" +"\n"+"Azokat az alkalmazottak adatait listázza akik takarítói munkabeosztásba vannak:  \n");
						//Kérdezze le azokat az alkalmazottakat akik takarítók
						NodeList nList1111 = doc.getElementsByTagName("Alkalmazott");
						
						
						for (int temp =0; temp < nList1111.getLength(); temp++) {
							Node nNode = nList1111.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("Beosztás").item(0);
									String beo  = node0.getTextContent();
									//Ha igen, akkor kiíratom az adataikat
									if("Takarító".equals(beo)) {
										
										String alkszem = eElement.getAttribute("SzemélyigazolványSzám");
										String hid = eElement.getAttribute("k-szigref");
										
										
										//Lekérjük az aktuális elem gyerekelemeinek tartalmát
										Node node1 = eElement.getElementsByTagName("Keresztnév").item(0);
										String knev = node1.getTextContent();
										
										Node node2 = eElement.getElementsByTagName("Vezetéknév").item(0);
										String vnev = node2.getTextContent();
										
										//név összefûzése
										String nev = vnev + knev ;
										
										Node node3 = eElement.getElementsByTagName("SzületésiIdõ").item(0);
										String szido = node3.getTextContent();
										
										Node node4 = eElement.getElementsByTagName("SzületésiHely").item(0);
										String szhely = node4.getTextContent();
										
										Node node5 = eElement.getElementsByTagName("Irányítószám").item(0);
										String airsz = node5.getTextContent();
										
										Node node6 = eElement.getElementsByTagName("Város").item(0);
										String avaros = node6.getTextContent();
										
										Node node7 = eElement.getElementsByTagName("Utca").item(0);
										String autca = node7.getTextContent();
										
										Node node8 = eElement.getElementsByTagName("Házszám").item(0);
										String ahsz = node8.getTextContent();
										
										//a cím gyerekelemeinek összefûzése egy stringbe
										String alcim = airsz + avaros  + autca + ahsz ;
										
										Node node9 = eElement.getElementsByTagName("Beosztás").item(0);
										String beosz = node9.getTextContent();
										
										
										System.out.printf("Alkalmazott személyigazolvány száma:  %s%n" , alkszem);
										System.out.printf("Ebbe az ID-jû hotelbe dolgozik az alkalmazott:  %s%n" , hid);
										System.out.printf("Alkalmazott teljes neve:  %s%n" , nev);
										System.out.printf("Alkalmazott születési ideje:  %s%n" , szido);
										System.out.printf("Alkalmazott születési helye:  %s%n" , szhely);
										System.out.printf("Alkalmazott lakcíme:  %s%n" , alcim);
										System.out.printf("Alkalmazott beosztása:  %s%n" , beosz);
									}
								
							
								
							}
						}
						
		}
	

	}


