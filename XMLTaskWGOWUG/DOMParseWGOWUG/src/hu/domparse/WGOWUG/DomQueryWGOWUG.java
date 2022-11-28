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
			
			// a DocumentBuilderFactory-b�l megkapjuk a DocumentBuilder-t.
			
						Document doc = dBuilder.parse(inputFile);
						
						doc.getDocumentElement().normalize();

						//Gy�k�r elem ki�rat�sa
						System.out.println("Gy�k�r elem neve: "+ doc.getDocumentElement().getNodeName() +"\n");
						
						System.out.println("------------------------------------------------------------------------------");
						
						System.out.println("1.Lek�rdzek�s:" +"\n"+"Azokat a fogyaszt�sm�r�knek az adatait k�ri le amiknek a szolg�ltat�ja az MVM:  \n");
						//K�rdezze le azokat a fogyaszt�sm�r�ket amiket az MVM szolg�ltat
						NodeList nList = doc.getElementsByTagName("Fogyaszt�sM�r�");
						
						
						for (int temp =0; temp < nList.getLength(); temp++) {
							Node nNode = nList.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("Szolg�ltat�").item(0);
									String szolg  = node0.getTextContent();
									//Ha igen, akkor ki�ratom az adataikat
									if("MVM".equals(szolg)) 
									{String gyszam = eElement.getAttribute("Gy�riSz�m");
									String hid = eElement.getAttribute("K-GYSZref");
									
									
									//Lek�rj�k az aktu�lis elem gyerekelemeinek tartalm�t
									Node node1 = eElement.getElementsByTagName("Szolg�ltat�").item(0);
									String szolg1 = node1.getTextContent();
									
									Node node2 = eElement.getElementsByTagName("�ra�ll�s").item(0);
									String oraall = node2.getTextContent();
									
									Node node3 = eElement.getElementsByTagName("Hiteles�t�D�tum").item(0);
									String hitdat = node3.getTextContent();
									
									
									System.out.printf("Fogyazt�s m�r� gy�ri sz�ma:  %s%n" , gyszam);
									System.out.printf("Ebbe az ID-j� hotelbe van a fogyaszt�sm�r�:  %s%n" , hid);
									System.out.printf("A fogyaszt�sm�r�t biztos�t� szolg�ltat�:  %s%n" , szolg1);
									System.out.printf("�ra�ll�s:  %s%n" , oraall);
									System.out.printf("Leolvas�s id�pontja:  %s%n" , hitdat);
										
									}
								
							
								
							}
						}	System.out.println("------------------------------------------------------------------------------");
						
						System.out.println("2.Lek�rdzek�s:" +"\n"+"Azokat a besz�ll�t�k adatait k�ri le akik Budapestr�l sz�ll�tj�k az �rukat a hotelbokba:  \n");
						//K�rdezze le azokat a Besz�ll�t�kat akik Budapestr�l sz�ll�tanak be
						NodeList nList1 = doc.getElementsByTagName("Besz�ll�t�");
						
						
						for (int temp =0; temp < nList1.getLength(); temp++) {
							Node nNode = nList1.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("V�ros").item(0);
									String bp  = node0.getTextContent();
									//Ha igen, akkor ki�ratom az adataikat
									if("Budapest".equals(bp)) {
										
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
						}	System.out.println("------------------------------------------------------------------------------");
						System.out.println("3.Lek�rdzek�s:" +"\n"+"Azokat a foglal�sok adatait list�zza ki amik a Kir�ly Hotel nev� hotelba t�rt�ntek:  \n");
						//K�rdezze le azokat a foglal�sokat amik a kri�ly hotelbe t�rt�ntek
						NodeList nList11 = doc.getElementsByTagName("Foglal�s");
						
						
						for (int temp =0; temp < nList11.getLength(); temp++) {
							Node nNode = nList11.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("Hotel").item(0);
									String khot  = node0.getTextContent();
									//Ha igen, akkor ki�ratom az adataikat
									if("Kir�ly hotel".equals(khot)) {
										
									
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
						}System.out.println("------------------------------------------------------------------------------");
						System.out.println("4.Lek�rdzek�s:" +"\n"+"Azokat a Hotelek adatait list�zza ki amik �jjel nappal nyitva vannak:  \n");
						//K�rdezze le azokat a hoteleket amik �lland�an nyitva vannak
						NodeList nList111 = doc.getElementsByTagName("Hotel");
						
						
						for (int temp =0; temp < nList111.getLength()-3; temp++) {
							Node nNode = nList111.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("Nyitvatart�s").item(0);
									String nyt  = node0.getTextContent();
									//Ha igen, akkor ki�ratom az adataikat
									if("00-24".equals(nyt)) {
										
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
						}System.out.println("------------------------------------------------------------------------------");
						System.out.println("5.Lek�rdzek�s:" +"\n"+"Azokat az alkalmazottak adatait list�zza akik takar�t�i munkabeoszt�sba vannak:  \n");
						//K�rdezze le azokat az alkalmazottakat akik takar�t�k
						NodeList nList1111 = doc.getElementsByTagName("Alkalmazott");
						
						
						for (int temp =0; temp < nList1111.getLength(); temp++) {
							Node nNode = nList1111.item(temp);
							
							
							if(nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								
								Node node0;
							
									node0 = eElement.getElementsByTagName("Beoszt�s").item(0);
									String beo  = node0.getTextContent();
									//Ha igen, akkor ki�ratom az adataikat
									if("Takar�t�".equals(beo)) {
										
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
						}
						
		}
	

	}


