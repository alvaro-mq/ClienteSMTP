package base64;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
public class Base64FileEncoder {

	 /**
	  * Codifica un fichero a base 64
	  * @param inputFileName
	  * @param outputFileName
	  * @throws IOException
	  */
	 public  void encodeFile (String inputFileName, String outputFileName) throws IOException {
	    BufferedInputStream in = null;
	    BufferedWriter out = null;
	    try {
	       in = new BufferedInputStream(new FileInputStream(inputFileName));
	       out = new BufferedWriter(new FileWriter(outputFileName));
	       encodeStream (in,out);
	       out.flush(); }
	     finally {
	       if (in != null) in.close();
	       if (out != null) out.close();
	     }
	 }
	 
	 /**
	  * Codifica un Stream
	  * @param in
	  * @param out
	  * @throws IOException
	  */
	 public void encodeStream (InputStream in, BufferedWriter out) throws IOException {
	    int lineLength = 72;
	    Base64Coder cod = new Base64Coder();
	    byte[] buf = new byte[lineLength/4*3];
	    while (true) {
	       int len = in.read(buf);
	       if (len <= 0) break;
	       out.write (cod.encode(buf, len));
	       out.newLine();
	     }
	 }
	 
	 /**
	  * PAsa un fichero a base 64 y devuelve el resultado en un StringBuffer
	  * @param inputFileName
	  * @param out
	  * @throws IOException
	  */
	 
	 public void encodeStream(String inputFileName, StringBuffer out) throws IOException{
	  int lineLength = 72;
	  
	  BufferedInputStream in = new BufferedInputStream(new FileInputStream(inputFileName));
	  Base64Coder cod = new Base64Coder();
	  byte[] buf = new byte[lineLength/4*3];
	   while (true) {
	    int len = in.read(buf);
	      if (len <= 0) break;
	      String nuevo = new String(cod.encode(buf, len));
	      out.append(nuevo);
	  }
	 }

	}