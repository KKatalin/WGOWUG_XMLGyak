package hu.domparse.WGOWUG;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

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

public class DomModifyWGOWUG {

	public static void main(String[] args) throws TransformerConfigurationException,
	TransformerFactoryConfigurationError, TransformerException {
	    String filePath = "XMLwgowug.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder dBuilder;
    
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Módosítja a Beszállításnál a beszállított dolgokat, mindegyiket ajándéktárggyá módosítja 
            Módosít(doc);
            
            //Elsõ foglaló nevének átírása
            Módosít2(doc);

            // A foglalásból törli a hotel nevét
            Töröl(doc);
            
            // az alkalmazottak címét törli
            Töröl2(doc);

            // hozzáad egy "cím" elemet a hotelhoz
            Hozzáad(doc);
            
            // hozzáad egy "gyártó" elemet a fogyasztásmérõhöz
            Hozzáad2(doc);
          
            KiírásXMLFájlba(doc); 
           
            Kiír(doc);
            
            Kiír2(doc);

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();

	}

}
				private static void Kiír2(Document doc) throws TransformerException {
					System.out.printf("\n" + "-----------------------------------------------------------"
							+ "-------------------------------------------------------" + "\n"+ "Teljes módosult XML Doksi kiíratása:" + "\n"  + "\n");
					
					Transformer transformer = TransformerFactory.newInstance().newTransformer(); 
					//Transzformer létrehozása
					transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 
					//UTF 8 kódolású legyen , hogy olvasható legyen
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");  
					//megfelelõen behúzza a kimenetet, mivel alapértelmezés szerint a behúzás nulla szóköz.
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); 
					DOMSource source = new DOMSource(doc); //A Forrás és az Eredmény megadása
					Writer stringWriter = new StringWriter();
					StreamResult streamResult = new StreamResult(stringWriter); 
					//megadjuk, hogy a transzformátor hova írja ki az átalakítás eredményét
					transformer.transform(source, streamResult);     
					//megmondjuk a transzformátornak, hogy mûködjön a forrásobjektumon, és adja ki a kimenetet az eredmény objektumnak    
					String result = stringWriter.toString();
					System.out.printf(result);
					
				}
	
			     //cím Elem hozzáadása a hotelhez
			    private static void Hozzáad(Document doc) {
			    	NodeList Turizmus = doc.getElementsByTagName("Hotel");
			        Element Hotel = null;
			        
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	Hotel = (Element) Turizmus.item(i); 
			            Element id = doc.createElement("Cím");
			            id.appendChild(doc.createTextNode("3571 Arnót József Attilla út 76."));
			            Hotel.appendChild(id);
			        }
			    }
			    
			    private static void Hozzáad2(Document doc) {
			    	NodeList Turizmus = doc.getElementsByTagName("FogyasztásMérõ");
			        Element FogyasztásMérõ = null;
			        
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	FogyasztásMérõ = (Element) Turizmus.item(i); 
			            Element id = doc.createElement("Gyártó");
			            id.appendChild(doc.createTextNode("Electryc Fogyasztásmérõ KFT"));
			            FogyasztásMérõ.appendChild(id);
			        }
			       
			    }

			    //Egy elem törlése foglalásból a hotel-t ( hotel neve)
			    private static void Töröl(Document doc) {
			    	NodeList Turizmus = doc.getElementsByTagName("Foglalás");
			        Element Foglalás = null;
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	Foglalás = (Element) Turizmus.item(i);
			            Node cim = Foglalás.getElementsByTagName("Hotel").item(0);
			            Foglalás.removeChild(cim);
			        }
			    }
			    
			    //Egy elem törlése az alkalmazott egyedbõl, alkalmazottnak a lakcímét törli
			    private static void Töröl2(Document doc) {
			    	NodeList Turizmus = doc.getElementsByTagName("Alkalmazott");
			        Element Alkalmazott = null;
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	Alkalmazott = (Element) Turizmus.item(i);
			            Node Lakcím = Alkalmazott.getElementsByTagName("Lakcím").item(0);
			            Alkalmazott.removeChild(Lakcím);
			        }
			    }

			    //Beszállítás elsõ elemének frissítése, módosítása
			    private static void Módosít(Document doc) {
			        NodeList Turizmus = doc.getElementsByTagName("Beszállítás");
			        Element Beszállítás = null;
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	Beszállítás = (Element) Turizmus.item(i);
			            Node Beszállított_dolgok = Beszállítás.getElementsByTagName("Beszállított_dolgok").item(0).getFirstChild();
			            Beszállított_dolgok.setNodeValue("Ajándék tárgy");
			        }
			        }
			    
			    //Mészáros Kitti nevû foglaló átírása Fehérné Nagy klárává
			        private static void Módosít2(Document doc) {
				        NodeList Turizmus = doc.getElementsByTagName("Foglaló");
				        Element Foglaló = null;
				        // loop for each user
				        for (int i = 3; i < Turizmus.getLength()-2; i++) {
				        	Foglaló = (Element) Turizmus.item(i);
				            Node Név = Foglaló.getElementsByTagName("Név").item(0).getFirstChild();
				            Név.setNodeValue("Fehérné Nagy Klára");
				        }
				        
				        
			    }

		 			private static void KiírásXMLFájlba(Document doc)
		 				    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		 				        doc.getDocumentElement().normalize();
		 				        TransformerFactory transformerFactory = TransformerFactory.newInstance(); 
		 				        //Transzformer létrehozása
		 				        Transformer transformer = transformerFactory.newTransformer();
		 				        DOMSource source = new DOMSource(doc);
		 				        //A Forrás és az Eredmény megadása
		 				        StreamResult result = new StreamResult(new File("Updated_XMLwgowug.xml"));  
		 				        //megadjuk, hogy a transzformátor hova írja ki az átalakítás eredményét
		 				        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
		 				        //megfelelõen behúzza a kimenetet, mivel alapértelmezés szerint a behúzás nulla szóköz.
		 				        transformer.transform(source, result); 
		 				        //megmondjuk a transzformátornak, hogy mûködjön a forrásobjektumon, és adja ki a kimenetet az eredmény objektumnak
		 				       
		 				    }
			        
			    	private static void Kiír(Document doc) throws ParserConfigurationException, SAXException, IOException {
		 				
		 				
		 				File xmlDoc = new File("Updated_XMLwgowug.xml");
		 				
		 				//példányosítjuk a  DocumentBuilderFactory osztályt a statikus newInstance() metódussal
		 				DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
		 				DocumentBuilder dBuild = dbFact.newDocumentBuilder();
		 				// a DocumentBuilderFactory-bõl megkapjuk a DocumentBuilder-t.
		 				
		 				Document doc1 = dBuild.parse(xmlDoc);
		 				
		 				doc1.getDocumentElement().normalize();
		 				
		 				System.out.println("1.Módosítás: Hozzáad egy cím elemet a hotelhoz");
		 				
		 				NodeList nList = doc.getElementsByTagName("Hotel");
		 				// A getElementsByTagName() metódus segítségével megkapjuk a hotel elem NodeList-jét
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
		 						String hid = eElement.getAttribute("HotelId");
		 						
		 						System.out.printf("Hotel ID:  %s%n" , hid);
		 						System.out.println("Elérhetõség:"+eElement.getElementsByTagName("Elérhetõség").item(0).getTextContent()
		 								+"\n"+ "Nyitvatartás:"+eElement.getElementsByTagName("Nyitvatartás").item(0).getTextContent()
		 								+"\n"+ "Név:"+eElement.getElementsByTagName("Név").item(0).getTextContent()+"\n"+ "Cím:"
		 								+eElement.getElementsByTagName("Cím").item(0).getTextContent());
		 						
		 			
		 					} 
		 			
		 				}
		 				System.out.println("------------------------------------------------------------------------------");
		 				System.out.println("2.Módosítás: Frissíti,átírja a Beszállításnál a beszállított dolgokat");
		 				
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
		 						
		 						System.out.printf("Beszállító adószáma:  %s%n" , beszadszam);
		 						System.out.printf("Ebbe az ID-jû hotelbe szállítottak be:  %s%n" , hid);
		 						System.out.println("Beszállított dolgok:"+eElement.getElementsByTagName("Beszállított_dolgok").item(0).getTextContent()
		 								+"\n"+ "Dátum:"+eElement.getElementsByTagName("Dátum").item(0).getTextContent());
		 					}

		 		}
		 				System.out.println("------------------------------------------------------------------------------");
		 				System.out.println("3. Módosítás: Az alkalmazottak címét törli");	
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
									
									System.out.printf("Alkalmazott személyigazolvány száma:  %s%n" , alkszem);
									System.out.printf("Ebbe az ID-jû hotelbe dolgozik az alkalmazott:  %s%n" , hid);
									System.out.println("Keresztnév:"+eElement.getElementsByTagName("Keresztnév").item(0).getTextContent()+"\n"
									+ "Vezetéknév:"+eElement.getElementsByTagName("Vezetéknév").item(0).getTextContent()
											+"\n"+"SzületésiIdõ:"+eElement.getElementsByTagName("SzületésiIdõ").item(0).getTextContent()+" \n"
											+" \n"+"SzületésiHely: "+eElement.getElementsByTagName("SzületésiHely").item(0).getTextContent()
											/* Ha kivesszük a kommenteket,akkor hibát dob, mert az az elem már nem létezik amit ki akarunk íratni vagyis a cím
											+" \n"+"Irányítószám: "+eElement.getElementsByTagName("Irányítószám").item(0).getTextContent()
											+" \n"+"Város: "+eElement.getElementsByTagName("Város").item(0).getTextContent()
											+" \n"+"Utca: "+eElement.getElementsByTagName("Utca").item(0).getTextContent()
											+" \n"+"Házszám: "+eElement.getElementsByTagName("Házszám").item(0).getTextContent()*/
											+" \n"+"Beosztás: "+eElement.getElementsByTagName("Beosztás").item(0).getTextContent());
									
								}

					}
							System.out.println("------------------------------------------------------------------------------");
			 				System.out.println("4. Módosítás: hozzáad egy gyártó elemet a fogyasztásmérõhöz");	
			 				

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
										
										
										System.out.printf("Fogyaztás mérõ gyári száma:  %s%n" , gyszam);
										System.out.printf("Ebbe az ID-jû hotelbe van a fogyasztásmérõ:  %s%n" , hid);
										System.out.println("Szolgáltató:"+eElement.getElementsByTagName("Szolgáltató").item(0).getTextContent()+
												"\n"+ "Óraállás:"+eElement.getElementsByTagName("Óraállás").item(0).getTextContent()
												+"\n"+"HitelesítõDátum:"+eElement.getElementsByTagName("HitelesítõDátum").item(0).getTextContent()+" \n"
												+" \n"+"Gyártó: "+eElement.getElementsByTagName("Gyártó").item(0).getTextContent());
										
									}

						}
								System.out.println("------------------------------------------------------------------------------");
				 				System.out.println("5. Módosítás:  A foglalásból törli a hotel nevét");	
				 				
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
					
											System.out.printf("Foglalás ID:  %s%n" , fogid);
											System.out.printf("Ebbe az ID-jû hotelbe foglaltak szobát:  %s%n" , hid);
											System.out.println("SzobaSzám:"+eElement.getElementsByTagName("SzobaSzám").item(0).getTextContent()
													+"\n"+eElement.getElementsByTagName("SzobaSzám1").item(0).getTextContent()
																+"\n"+eElement.getElementsByTagName("SzobaSzám2").item(0).getTextContent()+" \n"
					//Ha kivesszük a kommenteket,akkor hibát dob, mert az az elem már nem létezik amit ki akarunk íratni vagyis a hotel neve
				//+"Hotel: "+eElement.getElementsByTagName("Hotel").item(0).getTextContent()
											+" \n"+"Foglaló neve: "+eElement.getElementsByTagName("Foglaló").item(0).getTextContent()
											+" \n"+"Foglalás dátuma: "+eElement.getElementsByTagName("Dátum").item(0).getTextContent());
											
										}

							} 
									
									System.out.println("------------------------------------------------------------------------------");
					 				System.out.println("6. Módosítás:  Elsõ foglaló nevének átírása");	
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
												
												System.out.printf("Foglaló személyigazolvány száma:  %s%n" , fogszig);
												System.out.printf("Ebbe az ID-jû hotelbe foglaltak szobát:  %s%n" , hid);
												System.out.println("Megváltozott Egyed: " + "\n"+"Telefonszám:"+
														eElement.getElementsByTagName("Telefonszám").item(0).getTextContent()
														+"\n"+"Irányítószám:"+eElement.getElementsByTagName("Irányítószám").item(0).getTextContent()
														+"\n"+"Város: "+eElement.getElementsByTagName("Város").item(0).getTextContent()
														+" \n"+"Utca: "+eElement.getElementsByTagName("Utca").item(0).getTextContent()
														+" \n"+"Házszám: "+eElement.getElementsByTagName("Házszám").item(0).getTextContent()
														+" \n"+"Név: "+eElement.getElementsByTagName("Név").item(0).getTextContent());
												
											}
											
								} 
		 			}
}

