package saxwgowug;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EsemenyKezelo extends DefaultHandler{

	boolean orarend = false;
	boolean ora = false;
	   boolean targy = false;
	   boolean idopont = false;
	   boolean nap = false;
	   boolean mettol = false;
	   boolean meddig = false;
	   boolean helyszin = false;
	   boolean oktato = false;
	   boolean szak = false;
	   
	   public void startElement(String uri, 
	      String localName, String qName, Attributes attributes)
	         throws SAXException {  	
	         if(qName.equalsIgnoreCase("orarend")) { 
	        	 System.out.println( qName + " start");
	        
	        	 
	         }
	         else if (qName.equalsIgnoreCase("ora")) { 
	        	 String id = attributes.getValue("id");
	        	 String tipus = attributes.getValue("tipus");
	        	 System.out.println("\t ora, {id=" + id + ", tipus=" + tipus + "} start");
	       
	      } else if (qName.equalsIgnoreCase("targy")) {
	    	  System.out.println( "\t \t" + qName + " start");
	    	  targy = true;
	    	  
	      } else if (qName.equalsIgnoreCase("idopont")) {
	    	  System.out.println( "\t \t" + qName + " start");
	    	  
	    	  
	      } 
	      else if (qName.equalsIgnoreCase("nap")) {
	    	  System.out.println( "\t \t \t" + qName + " start");
	    	  nap = true;
	    	  
	      } 
	      else if (qName.equalsIgnoreCase("mettol")) {
	    	  System.out.println( "\t \t \t" + qName + " start");
	    	  mettol = true;
	    	  
	      } 
	      else if (qName.equalsIgnoreCase("meddig")) {
	    	  System.out.println( "\t \t \t" + qName + " start");
	    	  meddig = true;
	    	  
	      } 
	      else if (qName.equalsIgnoreCase("helyszin")) {
	    	  System.out.println( "\t \t" + qName + " start");
	    	  helyszin = true;
	    	  
	      }  else if (qName.equalsIgnoreCase("oktato")) {
	    	  System.out.println( "\t \t" + qName + " start");
	    	  oktato = true;
	      }
	      else if (qName.equalsIgnoreCase("szak")) {
	    	  System.out.println( "\t \t" + qName + " start");
	    	  szak = true;
	      }
	   }

	   public void endElement(String uri, 
	      String localName, String qName) throws SAXException {
		   if(qName.equalsIgnoreCase("orarend")) {
			   System.out.println( qName + " end");
		   }
	   else if (qName.equalsIgnoreCase("ora")) {
	         System.out.println("\t" + qName + " end");
	      } else if(qName.equalsIgnoreCase("targy")){
	    	  System.out.println( "\t \t" +qName + " end");
	      }
	      else if(qName.equalsIgnoreCase("idopont")){
	    	  System.out.println( "\t \t" +qName + " end");
	      }
	      else if(qName.equalsIgnoreCase("nap")){
	    	  System.out.println( "\t \t \t" +qName + " end");
	      }
	      else if(qName.equalsIgnoreCase("mettol")){
	    	  System.out.println( "\t \t \t" +qName + " end");
	      }
	      else if(qName.equalsIgnoreCase("meddig")){
	    	  System.out.println( "\t \t \t" +qName + " end");
	      }
	      else if(qName.equalsIgnoreCase("helyszin")){
	    	  System.out.println( "\t \t" +qName + " end");
	      }
	      else if(qName.equalsIgnoreCase("oktato")){
	    	  System.out.println( "\t \t" +qName + " end");
	      }
	      else if(qName.equalsIgnoreCase("szak")){
	    	  System.out.println( "\t \t" +qName + " end");
	      }
	   }

	   public void characters(char ch[], 
	      int start, int length) throws SAXException {
		   if (orarend) {
		         System.out.println("orarend " 
		         + new String(ch, start, length));
		         orarend = false;
		      } 
		   else if (ora) {
		         System.out.println("ora " 
		         + new String(ch, start, length));
		         ora = false;
		      }
		   else if (targy) {
	         System.out.println( "\t \t \t" +
	          new String(ch, start, length));
	         targy = false;
	      } else if (idopont) {
		         System.out.println( "\t \t \t" +
		   	          new String(ch, start, length));
		   	         idopont = false;
		   	      } 
	      else if (nap) {
		         System.out.println("\t \t \t \t" 
		         + new String(ch, start, length));
		         nap = false;
		      }
	      else if (mettol) {
		         System.out.println("\t \t \t \t" 
		         + new String(ch, start, length));
		         mettol = false;
		      }
	      else if (meddig) {
		         System.out.println("\t \t \t \t" 
		         + new String(ch, start, length));
		         meddig = false;
		      }
		   else if (helyszin) {
	         System.out.println("\t \t \t" 
	         + new String(ch, start, length));
	         helyszin = false;
	      } else if (oktato) {
	         System.out.println("\t \t \t"  
	         + new String(ch, start, length));
	         oktato = false;
	      }
	      else if (szak) {
		         System.out.println("\t \t \t" 
		         + new String(ch, start, length));
		         szak = false;
		      }
	   }

}
