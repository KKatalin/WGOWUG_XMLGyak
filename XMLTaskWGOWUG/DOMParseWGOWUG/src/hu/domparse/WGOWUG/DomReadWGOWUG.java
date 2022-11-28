package hu.domparse.WGOWUG;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;




public class DomReadWGOWUG {
 
	public static void main(String[] args ) throws SAXException, IOException, ParserConfigurationException,
	TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		
			File xmlDoc = new File("XMLwgowug.xml");
			
			//példányosítjuk a  DocumentBuilderFactory osztályt a statikus newInstance() metódussal
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			dbFact.setValidating(true); //Validáljuk az xml-t
			
			
			// a DocumentBuilderFactory-bõl megkapjuk a DocumentBuilder-t.
			
			Document doc = dBuild.parse(xmlDoc);
			
			doc.getDocumentElement().normalize();

			//Gyökér elem kiíratása
			System.out.println("Gyökér elem neve: "+ doc.getDocumentElement().getNodeName());
			
			// a fa megadott névvel rendelkezõ csomópontjainak összegyûjtése
			// csomópontok halmaza, adattagja és egy metódusa van
			
			//read hotel element
			NodeList nList = doc.getElementsByTagName("Hotel");
			// A getElementsByTagName() metódus segítségével megkapjuk a hotel elem NodeList-jét
			// a listán for ciklussal megyünk végig
			for(int i=0; i<nList.getLength()-3;i++)
			{
				
				//Lekérjük a lista aktuális elemét
				Node nNode = nList.item(i);
				
				System.out.println("\nEgyed neve: " + nNode.getNodeName());
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					//elementté konvertáljuk a az aktuális elemet
					Element eElement = (Element) nNode; 
					 //Lekérjük az aktuális elem attribútumjának tartalmát
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
			
		
			 nList = doc.getElementsByTagName("Beszállító");
			// A getElementsByTagName() metódus segítségével megkapjuk a Beszállító elem NodeList-jét
			// a listán for ciklussal megyünk végig
			for(int i=0; i<nList.getLength();i++)
			{
				
				//Lekérjük a lista aktuális elemét
				Node nNode = nList.item(i);
				
				System.out.println("\nEgyed neve: " + nNode.getNodeName());
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					//elementté konvertáljuk a az aktuális elemet
					Element eElement = (Element) nNode; 
					 //Lekérjük az aktuális elem attribútumjának tartalmát
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
			 nList = doc.getElementsByTagName("Beszállítás");
				// A getElementsByTagName() metódus segítségével megkapjuk a Beszállítás elem NodeList-jét
				// a listán for ciklussal megyünk végig
				for(int i=0; i<nList.getLength();i++)
				{
					
					//Lekérjük a lista aktuális elemét
					Node nNode = nList.item(i);
					
					System.out.println("\nEgyed neve: " + nNode.getNodeName());
					
					if(nNode.getNodeType() == Node.ELEMENT_NODE)
					{
						//elementté konvertáljuk a az aktuális elemet
						Element eElement = (Element) nNode; 
						 //Lekérjük az aktuális elem attribútumjának tartalmát
						String beszadszam = eElement.getAttribute("K-Bref");
						String hid = eElement.getAttribute("K-Href");
						
						
						//Lekérjük az aktuális elem gyerekelemeinek tartalmát
						Node node1 = eElement.getElementsByTagName("Beszállított_dolgok").item(0);
						String beszdol = node1.getTextContent();
						
						Node node2 = eElement.getElementsByTagName("Dátum").item(0);
						String bdatum = node2.getTextContent();
						
						System.out.printf("Beszállító adószáma:  %s%n" , beszadszam);
						System.out.printf("Ebbe az ID-jû hotelbe szállítottak be:  %s%n" , hid);
						System.out.printf("Beszállított dolog:  %s%n" , beszdol);
						System.out.printf("Beszállítás ideje:  %s%n" , bdatum);
					}

		}
				
				 nList = doc.getElementsByTagName("Alkalmazott");
					// A getElementsByTagName() metódus segítségével megkapjuk az Alkalmazott elem NodeList-jét
					// a listán for ciklussal megyünk végig
					for(int i=0; i<nList.getLength();i++)
					{
						
						//Lekérjük a lista aktuális elemét
						Node nNode = nList.item(i);
						
						System.out.println("\nEgyed neve: " + nNode.getNodeName());
						
						if(nNode.getNodeType() == Node.ELEMENT_NODE)
						{
							//elementté konvertáljuk a az aktuális elemet
							Element eElement = (Element) nNode; 
							 //Lekérjük az aktuális elem attribútumjának tartalmát
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

					 nList = doc.getElementsByTagName("FogyasztásMérõ");
						// A getElementsByTagName() metódus segítségével megkapjuk az Fogyasztásmérõ elem NodeList-jét
						// a listán for ciklussal megyünk végig
						for(int i=0; i<nList.getLength();i++)
						{
							
							//Lekérjük a lista aktuális elemét
							Node nNode = nList.item(i);
							
							System.out.println("\nEgyed neve: " + nNode.getNodeName());
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE)
							{
								//elementté konvertáljuk a az aktuális elemet
								Element eElement = (Element) nNode; 
								 //Lekérjük az aktuális elem attribútumjának tartalmát
								String gyszam = eElement.getAttribute("GyáriSzám");
								String hid = eElement.getAttribute("K-GYSZref");
								
								
								//Lekérjük az aktuális elem gyerekelemeinek tartalmát
								Node node1 = eElement.getElementsByTagName("Szolgáltató").item(0);
								String szolg = node1.getTextContent();
								
								Node node2 = eElement.getElementsByTagName("Óraállás").item(0);
								String oraall = node2.getTextContent();
								
								Node node3 = eElement.getElementsByTagName("HitelesítõDátum").item(0);
								String hitdat = node3.getTextContent();
								
								
								System.out.printf("Fogyaztás mérõ gyári száma:  %s%n" , gyszam);
								System.out.printf("Ebbe az ID-jû hotelbe van a fogyasztásmérõ:  %s%n" , hid);
								System.out.printf("A fogyasztásmérõt biztosító szolgáltató:  %s%n" , szolg);
								System.out.printf("Óraállás:  %s%n" , oraall);
								System.out.printf("Leolvasás idõpontja:  %s%n" , hitdat);
								
							}

				}

						 nList = doc.getElementsByTagName("Foglalás");
							// A getElementsByTagName() metódus segítségével megkapjuk az Foglalás elem NodeList-jét
							// a listán for ciklussal megyünk végig
							for(int i=0; i<nList.getLength();i++)
							{
								
								//Lekérjük a lista aktuális elemét
								Node nNode = nList.item(i);
								
								System.out.println("\nEgyed neve: " + nNode.getNodeName());
								
								if(nNode.getNodeType() == Node.ELEMENT_NODE)
								{
									//elementté konvertáljuk a az aktuális elemet
									Element eElement = (Element) nNode; 
									 //Lekérjük az aktuális elem attribútumjának tartalmát
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

							 nList = doc.getElementsByTagName("Foglaló");
								// A getElementsByTagName() metódus segítségével megkapjuk az Foglaló elem NodeList-jét
								// a listán for ciklussal megyünk végig
								for(int i=3; i<nList.getLength();i++)
								{
									
									//Lekérjük a lista aktuális elemét
									Node nNode = nList.item(i);
									
									System.out.println("\nEgyed neve: " + nNode.getNodeName());
									
									if(nNode.getNodeType() == Node.ELEMENT_NODE)
									{
										//elementté konvertáljuk a az aktuális elemet
										Element eElement = (Element) nNode; 
										 //Lekérjük az aktuális elem attribútumjának tartalmát
										String fogszig = eElement.getAttribute("SzemélyigazolványSzám");
										String hid = eElement.getAttribute("K-szigref");
										
										
										//Lekérjük az aktuális elem gyerekelemeinek tartalmát
										Node node1 = eElement.getElementsByTagName("Telefonszám").item(0);
										String ftelsz = node1.getTextContent();
										
										Node node2 = eElement.getElementsByTagName("Irányítószám").item(0);
										String firsz = node2.getTextContent();
										
										Node node3 = eElement.getElementsByTagName("Város").item(0);
										String fvaros = node3.getTextContent();
										
										Node node4 = eElement.getElementsByTagName("Utca").item(0);
										String futca = node4.getTextContent();
										
										Node node5 = eElement.getElementsByTagName("Házszám").item(0);
										String fhsz = node5.getTextContent();
										
										
										
										//a cím gyerekelemeinek összefûzése egy stringbe
										String flcim = firsz + fvaros  + futca + fhsz ;
										
										
										Node node6 = eElement.getElementsByTagName("Név").item(0);
										String fnev = node6.getTextContent();
									
										System.out.printf("Foglaló személyigazolvány száma:  %s%n" , fogszig);
										System.out.printf("Ebbe az ID-jû hotelbe foglaltak szobát:  %s%n" , hid);
										System.out.printf("Fogló telefonszáma %s%n" , ftelsz );
										System.out.printf("Foglaló lakcíme:  %s%n" , flcim);
										System.out.printf("Foglaló teljes neve:  %s%n" , fnev);
										
										
									}
									
						} 
								 //kiírja egy txt fileba az xml doksit
								KiírásTXTFájlba(doc);
								
								
	}//Külön metódus az XML doksi kiíratásához TXT fájlba
	 private static void KiírásTXTFájlba(Document doc)
			    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
			        doc.getDocumentElement().normalize();
			        TransformerFactory transformerFactory = TransformerFactory.newInstance(); //Transzformer létrehozása
			        Transformer transformer = transformerFactory.newTransformer();
			        DOMSource source = new DOMSource(doc); //A Forrás és az Eredmény megadása
			        StreamResult result = new StreamResult(new File("XMLwgowug.txt")); //megadjuk, hogy a transzformátor hova írja ki az átalakítás eredményét
			        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //megfelelõen behúzza a kimenetet, mivel alapértelmezés szerint a behúzás nulla szóköz.
			        transformer.transform(source, result); //megmondjuk a transzformátornak, hogy mûködjön a forrásobjektumon, és adja ki a kimenetet az eredmény objektumnak
			       
			    }
	
	 
}

