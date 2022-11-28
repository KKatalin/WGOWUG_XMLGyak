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

            // M�dos�tja a Besz�ll�t�sn�l a besz�ll�tott dolgokat, mindegyiket aj�nd�kt�rggy� m�dos�tja 
            M�dos�t(doc);
            
            //Els� foglal� nev�nek �t�r�sa
            M�dos�t2(doc);

            // A foglal�sb�l t�rli a hotel nev�t
            T�r�l(doc);
            
            // az alkalmazottak c�m�t t�rli
            T�r�l2(doc);

            // hozz�ad egy "c�m" elemet a hotelhoz
            Hozz�ad(doc);
            
            // hozz�ad egy "gy�rt�" elemet a fogyaszt�sm�r�h�z
            Hozz�ad2(doc);
          
            Ki�r�sXMLF�jlba(doc); 
           
            Ki�r(doc);
            
            Ki�r2(doc);

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();

	}

}
				private static void Ki�r2(Document doc) throws TransformerException {
					System.out.printf("\n" + "-----------------------------------------------------------"
							+ "-------------------------------------------------------" + "\n"+ "Teljes m�dosult XML Doksi ki�rat�sa:" + "\n"  + "\n");
					
					Transformer transformer = TransformerFactory.newInstance().newTransformer(); 
					//Transzformer l�trehoz�sa
					transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 
					//UTF 8 k�dol�s� legyen , hogy olvashat� legyen
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");  
					//megfelel�en beh�zza a kimenetet, mivel alap�rtelmez�s szerint a beh�z�s nulla sz�k�z.
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); 
					DOMSource source = new DOMSource(doc); //A Forr�s �s az Eredm�ny megad�sa
					Writer stringWriter = new StringWriter();
					StreamResult streamResult = new StreamResult(stringWriter); 
					//megadjuk, hogy a transzform�tor hova �rja ki az �talak�t�s eredm�ny�t
					transformer.transform(source, streamResult);     
					//megmondjuk a transzform�tornak, hogy m�k�dj�n a forr�sobjektumon, �s adja ki a kimenetet az eredm�ny objektumnak    
					String result = stringWriter.toString();
					System.out.printf(result);
					
				}
	
			     //c�m Elem hozz�ad�sa a hotelhez
			    private static void Hozz�ad(Document doc) {
			    	NodeList Turizmus = doc.getElementsByTagName("Hotel");
			        Element Hotel = null;
			        
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	Hotel = (Element) Turizmus.item(i); 
			            Element id = doc.createElement("C�m");
			            id.appendChild(doc.createTextNode("3571 Arn�t J�zsef Attilla �t 76."));
			            Hotel.appendChild(id);
			        }
			    }
			    
			    private static void Hozz�ad2(Document doc) {
			    	NodeList Turizmus = doc.getElementsByTagName("Fogyaszt�sM�r�");
			        Element Fogyaszt�sM�r� = null;
			        
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	Fogyaszt�sM�r� = (Element) Turizmus.item(i); 
			            Element id = doc.createElement("Gy�rt�");
			            id.appendChild(doc.createTextNode("Electryc Fogyaszt�sm�r� KFT"));
			            Fogyaszt�sM�r�.appendChild(id);
			        }
			       
			    }

			    //Egy elem t�rl�se foglal�sb�l a hotel-t ( hotel neve)
			    private static void T�r�l(Document doc) {
			    	NodeList Turizmus = doc.getElementsByTagName("Foglal�s");
			        Element Foglal�s = null;
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	Foglal�s = (Element) Turizmus.item(i);
			            Node cim = Foglal�s.getElementsByTagName("Hotel").item(0);
			            Foglal�s.removeChild(cim);
			        }
			    }
			    
			    //Egy elem t�rl�se az alkalmazott egyedb�l, alkalmazottnak a lakc�m�t t�rli
			    private static void T�r�l2(Document doc) {
			    	NodeList Turizmus = doc.getElementsByTagName("Alkalmazott");
			        Element Alkalmazott = null;
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	Alkalmazott = (Element) Turizmus.item(i);
			            Node Lakc�m = Alkalmazott.getElementsByTagName("Lakc�m").item(0);
			            Alkalmazott.removeChild(Lakc�m);
			        }
			    }

			    //Besz�ll�t�s els� elem�nek friss�t�se, m�dos�t�sa
			    private static void M�dos�t(Document doc) {
			        NodeList Turizmus = doc.getElementsByTagName("Besz�ll�t�s");
			        Element Besz�ll�t�s = null;
			        // loop for each user
			        for (int i = 0; i < Turizmus.getLength(); i++) {
			        	Besz�ll�t�s = (Element) Turizmus.item(i);
			            Node Besz�ll�tott_dolgok = Besz�ll�t�s.getElementsByTagName("Besz�ll�tott_dolgok").item(0).getFirstChild();
			            Besz�ll�tott_dolgok.setNodeValue("Aj�nd�k t�rgy");
			        }
			        }
			    
			    //M�sz�ros Kitti nev� foglal� �t�r�sa Feh�rn� Nagy kl�r�v�
			        private static void M�dos�t2(Document doc) {
				        NodeList Turizmus = doc.getElementsByTagName("Foglal�");
				        Element Foglal� = null;
				        // loop for each user
				        for (int i = 3; i < Turizmus.getLength()-2; i++) {
				        	Foglal� = (Element) Turizmus.item(i);
				            Node N�v = Foglal�.getElementsByTagName("N�v").item(0).getFirstChild();
				            N�v.setNodeValue("Feh�rn� Nagy Kl�ra");
				        }
				        
				        
			    }

		 			private static void Ki�r�sXMLF�jlba(Document doc)
		 				    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		 				        doc.getDocumentElement().normalize();
		 				        TransformerFactory transformerFactory = TransformerFactory.newInstance(); 
		 				        //Transzformer l�trehoz�sa
		 				        Transformer transformer = transformerFactory.newTransformer();
		 				        DOMSource source = new DOMSource(doc);
		 				        //A Forr�s �s az Eredm�ny megad�sa
		 				        StreamResult result = new StreamResult(new File("Updated_XMLwgowug.xml"));  
		 				        //megadjuk, hogy a transzform�tor hova �rja ki az �talak�t�s eredm�ny�t
		 				        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
		 				        //megfelel�en beh�zza a kimenetet, mivel alap�rtelmez�s szerint a beh�z�s nulla sz�k�z.
		 				        transformer.transform(source, result); 
		 				        //megmondjuk a transzform�tornak, hogy m�k�dj�n a forr�sobjektumon, �s adja ki a kimenetet az eredm�ny objektumnak
		 				       
		 				    }
			        
			    	private static void Ki�r(Document doc) throws ParserConfigurationException, SAXException, IOException {
		 				
		 				
		 				File xmlDoc = new File("Updated_XMLwgowug.xml");
		 				
		 				//p�ld�nyos�tjuk a  DocumentBuilderFactory oszt�lyt a statikus newInstance() met�dussal
		 				DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
		 				DocumentBuilder dBuild = dbFact.newDocumentBuilder();
		 				// a DocumentBuilderFactory-b�l megkapjuk a DocumentBuilder-t.
		 				
		 				Document doc1 = dBuild.parse(xmlDoc);
		 				
		 				doc1.getDocumentElement().normalize();
		 				
		 				System.out.println("1.M�dos�t�s: Hozz�ad egy c�m elemet a hotelhoz");
		 				
		 				NodeList nList = doc.getElementsByTagName("Hotel");
		 				// A getElementsByTagName() met�dus seg�ts�g�vel megkapjuk a hotel elem NodeList-j�t
		 				// a list�n for ciklussal megy�nk v�gig
		 				for(int i=0; i<nList.getLength();i++)
		 				{
		 					
		 					//Lek�rj�k a lista aktu�lis elem�t
		 					Node nNode = nList.item(i);
		 					
		 					System.out.println("\nEgyed neve: " + nNode.getNodeName());
		 					
		 					if(nNode.getNodeType() == Node.ELEMENT_NODE)
		 					{
		 						//elementt� konvert�ljuk a az aktu�lis elemet
		 						Element eElement = (Element) nNode; 
		 						 //Lek�rj�k az aktu�lis elem attrib�tumj�nak tartalm�t
		 						String hid = eElement.getAttribute("HotelId");
		 						
		 						System.out.printf("Hotel ID:  %s%n" , hid);
		 						System.out.println("El�rhet�s�g:"+eElement.getElementsByTagName("El�rhet�s�g").item(0).getTextContent()
		 								+"\n"+ "Nyitvatart�s:"+eElement.getElementsByTagName("Nyitvatart�s").item(0).getTextContent()
		 								+"\n"+ "N�v:"+eElement.getElementsByTagName("N�v").item(0).getTextContent()+"\n"+ "C�m:"
		 								+eElement.getElementsByTagName("C�m").item(0).getTextContent());
		 						
		 			
		 					} 
		 			
		 				}
		 				System.out.println("------------------------------------------------------------------------------");
		 				System.out.println("2.M�dos�t�s: Friss�ti,�t�rja a Besz�ll�t�sn�l a besz�ll�tott dolgokat");
		 				
		 				 nList = doc.getElementsByTagName("Besz�ll�t�s");
		 				// A getElementsByTagName() met�dus seg�ts�g�vel megkapjuk a Besz�ll�t�s elem NodeList-j�t
		 				// a list�n for ciklussal megy�nk v�gig
		 				for(int i=0; i<nList.getLength();i++)
		 				{
		 					
		 					//Lek�rj�k a lista aktu�lis elem�t
		 					Node nNode = nList.item(i);
		 					
		 					System.out.println("\nEgyed neve: " + nNode.getNodeName());
		 					
		 					if(nNode.getNodeType() == Node.ELEMENT_NODE)
		 					{
		 						//elementt� konvert�ljuk a az aktu�lis elemet
		 						Element eElement = (Element) nNode; 
		 						 //Lek�rj�k az aktu�lis elem attrib�tumj�nak tartalm�t
		 						String beszadszam = eElement.getAttribute("K-Bref");
		 						String hid = eElement.getAttribute("K-Href");
		 						
		 						System.out.printf("Besz�ll�t� ad�sz�ma:  %s%n" , beszadszam);
		 						System.out.printf("Ebbe az ID-j� hotelbe sz�ll�tottak be:  %s%n" , hid);
		 						System.out.println("Besz�ll�tott dolgok:"+eElement.getElementsByTagName("Besz�ll�tott_dolgok").item(0).getTextContent()
		 								+"\n"+ "D�tum:"+eElement.getElementsByTagName("D�tum").item(0).getTextContent());
		 					}

		 		}
		 				System.out.println("------------------------------------------------------------------------------");
		 				System.out.println("3. M�dos�t�s: Az alkalmazottak c�m�t t�rli");	
		 				 nList = doc.getElementsByTagName("Alkalmazott");
							// A getElementsByTagName() met�dus seg�ts�g�vel megkapjuk az Alkalmazott elem NodeList-j�t
							// a list�n for ciklussal megy�nk v�gig
							for(int i=0; i<nList.getLength();i++)
							{
								
								//Lek�rj�k a lista aktu�lis elem�t
								Node nNode = nList.item(i);
								
								System.out.println("\nEgyed neve: " + nNode.getNodeName());
								
								if(nNode.getNodeType() == Node.ELEMENT_NODE)
								{
									//elementt� konvert�ljuk a az aktu�lis elemet
									Element eElement = (Element) nNode; 
									 //Lek�rj�k az aktu�lis elem attrib�tumj�nak tartalm�t
									String alkszem = eElement.getAttribute("Szem�lyigazolv�nySz�m");
									String hid = eElement.getAttribute("k-szigref");
									
									System.out.printf("Alkalmazott szem�lyigazolv�ny sz�ma:  %s%n" , alkszem);
									System.out.printf("Ebbe az ID-j� hotelbe dolgozik az alkalmazott:  %s%n" , hid);
									System.out.println("Keresztn�v:"+eElement.getElementsByTagName("Keresztn�v").item(0).getTextContent()+"\n"
									+ "Vezet�kn�v:"+eElement.getElementsByTagName("Vezet�kn�v").item(0).getTextContent()
											+"\n"+"Sz�let�siId�:"+eElement.getElementsByTagName("Sz�let�siId�").item(0).getTextContent()+" \n"
											+" \n"+"Sz�let�siHely: "+eElement.getElementsByTagName("Sz�let�siHely").item(0).getTextContent()
											/* Ha kivessz�k a kommenteket,akkor hib�t dob, mert az az elem m�r nem l�tezik amit ki akarunk �ratni vagyis a c�m
											+" \n"+"Ir�ny�t�sz�m: "+eElement.getElementsByTagName("Ir�ny�t�sz�m").item(0).getTextContent()
											+" \n"+"V�ros: "+eElement.getElementsByTagName("V�ros").item(0).getTextContent()
											+" \n"+"Utca: "+eElement.getElementsByTagName("Utca").item(0).getTextContent()
											+" \n"+"H�zsz�m: "+eElement.getElementsByTagName("H�zsz�m").item(0).getTextContent()*/
											+" \n"+"Beoszt�s: "+eElement.getElementsByTagName("Beoszt�s").item(0).getTextContent());
									
								}

					}
							System.out.println("------------------------------------------------------------------------------");
			 				System.out.println("4. M�dos�t�s: hozz�ad egy gy�rt� elemet a fogyaszt�sm�r�h�z");	
			 				

							 nList = doc.getElementsByTagName("Fogyaszt�sM�r�");
								// A getElementsByTagName() met�dus seg�ts�g�vel megkapjuk az Fogyaszt�sm�r� elem NodeList-j�t
								// a list�n for ciklussal megy�nk v�gig
								for(int i=0; i<nList.getLength();i++)
								{
									
									//Lek�rj�k a lista aktu�lis elem�t
									Node nNode = nList.item(i);
									
									System.out.println("\nEgyed neve: " + nNode.getNodeName());
									
									if(nNode.getNodeType() == Node.ELEMENT_NODE)
									{
										//elementt� konvert�ljuk a az aktu�lis elemet
										Element eElement = (Element) nNode; 
										 //Lek�rj�k az aktu�lis elem attrib�tumj�nak tartalm�t
										String gyszam = eElement.getAttribute("Gy�riSz�m");
										String hid = eElement.getAttribute("K-GYSZref");
										
										
										System.out.printf("Fogyazt�s m�r� gy�ri sz�ma:  %s%n" , gyszam);
										System.out.printf("Ebbe az ID-j� hotelbe van a fogyaszt�sm�r�:  %s%n" , hid);
										System.out.println("Szolg�ltat�:"+eElement.getElementsByTagName("Szolg�ltat�").item(0).getTextContent()+
												"\n"+ "�ra�ll�s:"+eElement.getElementsByTagName("�ra�ll�s").item(0).getTextContent()
												+"\n"+"Hiteles�t�D�tum:"+eElement.getElementsByTagName("Hiteles�t�D�tum").item(0).getTextContent()+" \n"
												+" \n"+"Gy�rt�: "+eElement.getElementsByTagName("Gy�rt�").item(0).getTextContent());
										
									}

						}
								System.out.println("------------------------------------------------------------------------------");
				 				System.out.println("5. M�dos�t�s:  A foglal�sb�l t�rli a hotel nev�t");	
				 				
				 				 nList = doc.getElementsByTagName("Foglal�s");
									// A getElementsByTagName() met�dus seg�ts�g�vel megkapjuk az Foglal�s elem NodeList-j�t
									// a list�n for ciklussal megy�nk v�gig
									for(int i=0; i<nList.getLength();i++)
									{
										
										//Lek�rj�k a lista aktu�lis elem�t
										Node nNode = nList.item(i);
										
										System.out.println("\nEgyed neve: " + nNode.getNodeName());
										
										if(nNode.getNodeType() == Node.ELEMENT_NODE)
										{
											//elementt� konvert�ljuk a az aktu�lis elemet
											Element eElement = (Element) nNode; 
											 //Lek�rj�k az aktu�lis elem attrib�tumj�nak tartalm�t
											String fogid = eElement.getAttribute("Foglal�sID");
											String hid = eElement.getAttribute("K-Fref");
					
											System.out.printf("Foglal�s ID:  %s%n" , fogid);
											System.out.printf("Ebbe az ID-j� hotelbe foglaltak szob�t:  %s%n" , hid);
											System.out.println("SzobaSz�m:"+eElement.getElementsByTagName("SzobaSz�m").item(0).getTextContent()
													+"\n"+eElement.getElementsByTagName("SzobaSz�m1").item(0).getTextContent()
																+"\n"+eElement.getElementsByTagName("SzobaSz�m2").item(0).getTextContent()+" \n"
					//Ha kivessz�k a kommenteket,akkor hib�t dob, mert az az elem m�r nem l�tezik amit ki akarunk �ratni vagyis a hotel neve
				//+"Hotel: "+eElement.getElementsByTagName("Hotel").item(0).getTextContent()
											+" \n"+"Foglal� neve: "+eElement.getElementsByTagName("Foglal�").item(0).getTextContent()
											+" \n"+"Foglal�s d�tuma: "+eElement.getElementsByTagName("D�tum").item(0).getTextContent());
											
										}

							} 
									
									System.out.println("------------------------------------------------------------------------------");
					 				System.out.println("6. M�dos�t�s:  Els� foglal� nev�nek �t�r�sa");	
					 				 nList = doc.getElementsByTagName("Foglal�");
										// A getElementsByTagName() met�dus seg�ts�g�vel megkapjuk az Foglal� elem NodeList-j�t
										// a list�n for ciklussal megy�nk v�gig
										for(int i=3; i<nList.getLength();i++)
										{
											
											//Lek�rj�k a lista aktu�lis elem�t
											Node nNode = nList.item(i);
											
											System.out.println("\nEgyed neve: " + nNode.getNodeName());
											
											if(nNode.getNodeType() == Node.ELEMENT_NODE)
											{
												//elementt� konvert�ljuk a az aktu�lis elemet
												Element eElement = (Element) nNode; 
												 //Lek�rj�k az aktu�lis elem attrib�tumj�nak tartalm�t
												String fogszig = eElement.getAttribute("Szem�lyigazolv�nySz�m");
												String hid = eElement.getAttribute("K-szigref");
												
												System.out.printf("Foglal� szem�lyigazolv�ny sz�ma:  %s%n" , fogszig);
												System.out.printf("Ebbe az ID-j� hotelbe foglaltak szob�t:  %s%n" , hid);
												System.out.println("Megv�ltozott Egyed: " + "\n"+"Telefonsz�m:"+
														eElement.getElementsByTagName("Telefonsz�m").item(0).getTextContent()
														+"\n"+"Ir�ny�t�sz�m:"+eElement.getElementsByTagName("Ir�ny�t�sz�m").item(0).getTextContent()
														+"\n"+"V�ros: "+eElement.getElementsByTagName("V�ros").item(0).getTextContent()
														+" \n"+"Utca: "+eElement.getElementsByTagName("Utca").item(0).getTextContent()
														+" \n"+"H�zsz�m: "+eElement.getElementsByTagName("H�zsz�m").item(0).getTextContent()
														+" \n"+"N�v: "+eElement.getElementsByTagName("N�v").item(0).getTextContent());
												
											}
											
								} 
		 			}
}

