package base64;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class Base64FileDecoder {
 /**
  * decodifica un archivos de base 64
  * @param inputFileName
  * @param outputFileName
  * @throws IOException
  */
 public void decodeFile (String inputFileName, String outputFileName) throws IOException {
    BufferedReader in = null;
    BufferedOutputStream out = null;
    try {
       in = new BufferedReader(new FileReader(inputFileName));
       out = new BufferedOutputStream(new FileOutputStream(outputFileName));
       decodeStream (in,out);
       out.flush(); }
     finally {
       if (in != null) in.close();
       if (out != null) out.close();
     }
 }

 public void decodeStream (BufferedReader in, OutputStream out) throws IOException {
    Base64Coder cod = new Base64Coder();
  while (true) {
       String s = in.readLine();
       if (s == null) break;
       byte[] buf = cod.decode(s);
       out.write (buf);
    }
 }

}