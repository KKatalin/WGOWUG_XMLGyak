package saxwgowug;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;



public class SaxWGOWUG {

	public static void main(String[] args){
	      try {	
	    	  //File elérési út
			  String filePath = "D:\\JAVA\\SaxWGOWUG\\src\\saxwgowug\\KocsisKatalin_orarend_H.xml";
			  
			  //file object készítés
	          File inputFile = new File(filePath);
	          
	          //Get SAXParserFactory instance.
	          SAXParserFactory factory = SAXParserFactory.newInstance();
	          
	          //Get SAXParser object from SAXParserFactory instance.
	          SAXParser saxParser = factory.newSAXParser();
	          
	          //EsemenyKezelo object.
	          EsemenyKezelo esemenyKezelo = new EsemenyKezelo();
	          
	          //Parse the XML file.
	          saxParser.parse(inputFile, esemenyKezelo);     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   

}
