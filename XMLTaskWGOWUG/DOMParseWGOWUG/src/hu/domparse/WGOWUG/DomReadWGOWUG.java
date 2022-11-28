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
			
			//p�ld�nyos�tjuk a  DocumentBuilderFactory oszt�lyt a statikus newInstance() met�dussal
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			dbFact.setValidating(true); //Valid�ljuk az xml-t
			
			
			// a DocumentBuilderFactory-b�l megkapjuk a DocumentBuilder-t.
			
			Document doc = dBuild.parse(xmlDoc);
			
			doc.getDocumentElement().normalize();

			//Gy�k�r elem ki�rat�sa
			System.out.println("Gy�k�r elem neve: "+ doc.getDocumentElement().getNodeName());
			
			// a fa megadott n�vvel rendelkez� csom�pontjainak �sszegy�jt�se
			// csom�pontok halmaza, adattagja �s egy met�dusa van
			
			//read hotel element
			NodeList nList = doc.getElementsByTagName("Hotel");
			// A getElementsByTagName() met�dus seg�ts�g�vel megkapjuk a hotel elem NodeList-j�t
			// a list�n for ciklussal megy�nk v�gig
			for(int i=0; i<nList.getLength()-3;i++)
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
					
					//Lek�rj�k az aktu�lis elem gyerekelemeinek tartalm�t
					Node node1 = eElement.getElementsByTagName("El�rhet�s�g").item(0);
					String elerhetoseg = node1.getTextContent();
					
					Node node2 = eElement.getElementsByTagName("Nyitvatart�s").item(0);
					String nyitvatartas = node2.getTextContent();
					
					Node node3 = eElement.getElementsByTagName("N�v").item(0);
					String hotelneve = node3.getTextContent();
					
					System.out.printf("Hotel ID:  %s%n" , hid);
					System.out.printf("Hotel El�rhet�s�ge:  %s%n" , elerhetoseg);
					System.out.printf("Hotel Nyitvatart�sa:  %s%n" , nyitvatartas);
					System.out.printf("Hotel Neve:  %s%n" , hotelneve);
					
					
		
				} 
		
			}
			
		
			 nList = doc.getElementsByTagName("Besz�ll�t�");
			// A getElementsByTagName() met�dus seg�ts�g�vel megkapjuk a Besz�ll�t� elem NodeList-j�t
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
					String adszam = eElement.getAttribute("Ad�sz�m");
					
					
					//Lek�rj�k az aktu�lis elem gyerekelemeinek tartalm�t
					Node node1 = eElement.getElementsByTagName("N�v").item(0);
					String besznev = node1.getTextContent();
					
					Node node2 = eElement.getElementsByTagName("Ir�ny�t�sz�m").item(0);
					String birsz = node2.getTextContent();
					
					Node node3 = eElement.getElementsByTagName("V�ros").item(0);
					String bvaros = node3.getTextContent();
					
					Node node4 = eElement.getElementsByTagName("Utca").item(0);
					String butca = node4.getTextContent();
					
					Node node5 = eElement.getElementsByTagName("H�zsz�m").item(0);
					String bhsz = node5.getTextContent();
					
					Node node6 = eElement.getElementsByTagName("El�rhet�s�g").item(0);
					String beler = node6.getTextContent();
					
					//a c�m gyerekelemeinek �sszef�z�se egy stringbe
					String bcim = birsz + bvaros  + butca + bhsz ;
					
					System.out.printf("Besz�ll�t� ad�sz�ma:  %s%n" , adszam);
					System.out.printf("Besz�ll�t� neve:  %s%n" , besznev);
					System.out.printf("Besz�ll�t� c�me:  %s%n" , bcim);
					System.out.printf("Besz�ll�t� el�rhet�s�ge:  %s%n" , beler);
				}

	}
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
						
						
						//Lek�rj�k az aktu�lis elem gyerekelemeinek tartalm�t
						Node node1 = eElement.getElementsByTagName("Besz�ll�tott_dolgok").item(0);
						String beszdol = node1.getTextContent();
						
						Node node2 = eElement.getElementsByTagName("D�tum").item(0);
						String bdatum = node2.getTextContent();
						
						System.out.printf("Besz�ll�t� ad�sz�ma:  %s%n" , beszadszam);
						System.out.printf("Ebbe az ID-j� hotelbe sz�ll�tottak be:  %s%n" , hid);
						System.out.printf("Besz�ll�tott dolog:  %s%n" , beszdol);
						System.out.printf("Besz�ll�t�s ideje:  %s%n" , bdatum);
					}

		}
				
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
							
							
							//Lek�rj�k az aktu�lis elem gyerekelemeinek tartalm�t
							Node node1 = eElement.getElementsByTagName("Keresztn�v").item(0);
							String knev = node1.getTextContent();
							
							Node node2 = eElement.getElementsByTagName("Vezet�kn�v").item(0);
							String vnev = node2.getTextContent();
							
							//n�v �sszef�z�se
							String nev = vnev + knev ;
							
							Node node3 = eElement.getElementsByTagName("Sz�let�siId�").item(0);
							String szido = node3.getTextContent();
							
							Node node4 = eElement.getElementsByTagName("Sz�let�siHely").item(0);
							String szhely = node4.getTextContent();
							
							Node node5 = eElement.getElementsByTagName("Ir�ny�t�sz�m").item(0);
							String airsz = node5.getTextContent();
							
							Node node6 = eElement.getElementsByTagName("V�ros").item(0);
							String avaros = node6.getTextContent();
							
							Node node7 = eElement.getElementsByTagName("Utca").item(0);
							String autca = node7.getTextContent();
							
							Node node8 = eElement.getElementsByTagName("H�zsz�m").item(0);
							String ahsz = node8.getTextContent();
							
							//a c�m gyerekelemeinek �sszef�z�se egy stringbe
							String alcim = airsz + avaros  + autca + ahsz ;
							
							Node node9 = eElement.getElementsByTagName("Beoszt�s").item(0);
							String beosz = node9.getTextContent();
							
							
							System.out.printf("Alkalmazott szem�lyigazolv�ny sz�ma:  %s%n" , alkszem);
							System.out.printf("Ebbe az ID-j� hotelbe dolgozik az alkalmazott:  %s%n" , hid);
							System.out.printf("Alkalmazott teljes neve:  %s%n" , nev);
							System.out.printf("Alkalmazott sz�let�si ideje:  %s%n" , szido);
							System.out.printf("Alkalmazott sz�let�si helye:  %s%n" , szhely);
							System.out.printf("Alkalmazott lakc�me:  %s%n" , alcim);
							System.out.printf("Alkalmazott beoszt�sa:  %s%n" , beosz);
						}

			}

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
								
								
								//Lek�rj�k az aktu�lis elem gyerekelemeinek tartalm�t
								Node node1 = eElement.getElementsByTagName("Szolg�ltat�").item(0);
								String szolg = node1.getTextContent();
								
								Node node2 = eElement.getElementsByTagName("�ra�ll�s").item(0);
								String oraall = node2.getTextContent();
								
								Node node3 = eElement.getElementsByTagName("Hiteles�t�D�tum").item(0);
								String hitdat = node3.getTextContent();
								
								
								System.out.printf("Fogyazt�s m�r� gy�ri sz�ma:  %s%n" , gyszam);
								System.out.printf("Ebbe az ID-j� hotelbe van a fogyaszt�sm�r�:  %s%n" , hid);
								System.out.printf("A fogyaszt�sm�r�t biztos�t� szolg�ltat�:  %s%n" , szolg);
								System.out.printf("�ra�ll�s:  %s%n" , oraall);
								System.out.printf("Leolvas�s id�pontja:  %s%n" , hitdat);
								
							}

				}

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
									
									
									//Lek�rj�k az aktu�lis elem gyerekelemeinek tartalm�t
									Node node1 = eElement.getElementsByTagName("SzobaSz�m").item(0);
									String szszam = node1.getTextContent();
									
									Node node11 = eElement.getElementsByTagName("SzobaSz�m1").item(0);
									String szszam1 = node11.getTextContent();
									
									Node node111 = eElement.getElementsByTagName("SzobaSz�m2").item(0);
									String szszam111 = node111.getTextContent();
									
									Node node2 = eElement.getElementsByTagName("Hotel").item(0);
									String hot = node2.getTextContent();
									
									Node node3 = eElement.getElementsByTagName("Foglal�").item(0);
									String fogl = node3.getTextContent();
									
									Node node4 = eElement.getElementsByTagName("D�tum").item(0);
									String fdat = node4.getTextContent();
									
									
									
									System.out.printf("Foglal�s ID:  %s%n" , fogid);
									System.out.printf("Ebbe az ID-j� hotelbe foglaltak szob�t:  %s%n" , hid);
									System.out.printf("Foglalt szob�k sz�ma:  %s%n %s%n %s%n" , szszam, szszam1, szszam111 );
									System.out.printf("Ebbe a nev� hotelbe foglaltak szob�kat:  %s%n" , hot);
									System.out.printf("Foglal� teljes neve:  %s%n" , fogl);
									System.out.printf("Foglal�s ideje:  %s%n" , fdat);
									
								}

					}

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
										
										
										//Lek�rj�k az aktu�lis elem gyerekelemeinek tartalm�t
										Node node1 = eElement.getElementsByTagName("Telefonsz�m").item(0);
										String ftelsz = node1.getTextContent();
										
										Node node2 = eElement.getElementsByTagName("Ir�ny�t�sz�m").item(0);
										String firsz = node2.getTextContent();
										
										Node node3 = eElement.getElementsByTagName("V�ros").item(0);
										String fvaros = node3.getTextContent();
										
										Node node4 = eElement.getElementsByTagName("Utca").item(0);
										String futca = node4.getTextContent();
										
										Node node5 = eElement.getElementsByTagName("H�zsz�m").item(0);
										String fhsz = node5.getTextContent();
										
										
										
										//a c�m gyerekelemeinek �sszef�z�se egy stringbe
										String flcim = firsz + fvaros  + futca + fhsz ;
										
										
										Node node6 = eElement.getElementsByTagName("N�v").item(0);
										String fnev = node6.getTextContent();
									
										System.out.printf("Foglal� szem�lyigazolv�ny sz�ma:  %s%n" , fogszig);
										System.out.printf("Ebbe az ID-j� hotelbe foglaltak szob�t:  %s%n" , hid);
										System.out.printf("Fogl� telefonsz�ma %s%n" , ftelsz );
										System.out.printf("Foglal� lakc�me:  %s%n" , flcim);
										System.out.printf("Foglal� teljes neve:  %s%n" , fnev);
										
										
									}
									
						} 
								 //ki�rja egy txt fileba az xml doksit
								Ki�r�sTXTF�jlba(doc);
								
								
	}//K�l�n met�dus az XML doksi ki�rat�s�hoz TXT f�jlba
	 private static void Ki�r�sTXTF�jlba(Document doc)
			    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
			        doc.getDocumentElement().normalize();
			        TransformerFactory transformerFactory = TransformerFactory.newInstance(); //Transzformer l�trehoz�sa
			        Transformer transformer = transformerFactory.newTransformer();
			        DOMSource source = new DOMSource(doc); //A Forr�s �s az Eredm�ny megad�sa
			        StreamResult result = new StreamResult(new File("XMLwgowug.txt")); //megadjuk, hogy a transzform�tor hova �rja ki az �talak�t�s eredm�ny�t
			        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //megfelel�en beh�zza a kimenetet, mivel alap�rtelmez�s szerint a beh�z�s nulla sz�k�z.
			        transformer.transform(source, result); //megmondjuk a transzform�tornak, hogy m�k�dj�n a forr�sobjektumon, �s adja ki a kimenetet az eredm�ny objektumnak
			       
			    }
	
	 
}

