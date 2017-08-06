package base64;

public class Base64Coder {

 // Mapping table from 6-bit nibbles to Base64 characters.
 private char[] map1 = new char[64];
     {
       int i=0;
       for (char c='A'; c<='Z'; c++)
        map1[i++] = c;
       for (char c='a'; c<='z'; c++)
        map1[i++] = c;
       for (char c='0'; c<='9'; c++)
        map1[i++] = c;
       map1[i++] = '+'; map1[i++] = '/';
     }
 
 // Mapping table from Base64 characters to 6-bit nibbles.
 private byte[] map2 = new byte[128];
     {
       for (int i=0; i<map2.length; i++)
        map2[i] = -1;
       for (int i=0; i<64; i++) map2[map1[i]] =
        (byte)i;
     }
 
 /**
 * Codificando un string a base 64
 * @param s   String a codificar
 * @return   String en Base64
 */
 public String encodeString (String s) {
    return new String(encode(s.getBytes())); }
 
 /**
 * array de byte en fomate base64
 * @param in  array que contine los byte a codificas
 * @return    array de caracters  codificados en base 64
 */
 public char[] encode (byte[] in) {
    return encode(in,in.length); }
 
 /**
 * Codifica un array de byte en base 64
 * @param in   array de byte a codificar.
 * @param iLen numero de byte a codificar en in
 * @return     Array de caracteres codificados en base 64
 */
 public char[] encode (byte[] in, int iLen) {
    int oDataLen = (iLen*4+2)/3;     
    int oLen = ((iLen+2)/3)*4;       
    char[] out = new char[oLen];
    int ip = 0;
    int op = 0;
    while (ip < iLen) {
       int i0 = in[ip++] & 0xff;
       int i1 = ip < iLen ? in[ip++] & 0xff : 0;
       int i2 = ip < iLen ? in[ip++] & 0xff : 0;
       int o0 = i0 >>> 2;
       int o1 = ((i0 &   3) << 4) | (i1 >>> 4);
       int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
       int o3 = i2 & 0x3F;
       out[op++] = map1[o0];
       out[op++] = map1[o1];
       out[op] = op < oDataLen ? map1[o2] : '='; op++;
       out[op] = op < oDataLen ? map1[o3] : '='; op++;
    }
    return out;
 }
 
 /**
 * Decodifica un string en base 64
 * @param s  String en base 64
 * @return   A String que contiene los datos decodificados
 * @throws   IllegalArgumentException if the input is not valid Base64 encoded data.
 */
 public String decodeString (String s) {
    return new String(decode(s));
 }
 
 /**
 * Decodifica un array de byte en formato Base64.
 * @param s  String en base 64 a codificar.
 * @return   Array de byte decodificado
 * @throws   IllegalArgumentException if the input is not valid Base64 encoded data.
 */
 public byte[] decode (String s) {
    return decode(s.toCharArray());
 }
 
 /**
 * Decodifica un array de byte en formato base 64
 * @param in  Array de caracteres en base 64
 * @return    Array de byte con los datos decodificados
 * @throws    IllegalArgumentException if the input is not valid Base64 encoded data.
 */
 public byte[] decode (char[] in) {
    int iLen = in.length;
    if (iLen%4 != 0)
     throw new IllegalArgumentException ("Longitud de cadena codificada en base 64 no es multiple de 4");
    while (iLen > 0 && in[iLen-1] == '=')
     iLen--;
    int oLen = (iLen*3) / 4;
    byte[] out = new byte[oLen];
    int ip = 0;
    int op = 0;
    while (ip < iLen) {
       int i0 = in[ip++];
       int i1 = in[ip++];
       int i2 = ip < iLen ? in[ip++] : 'A';
       int i3 = ip < iLen ? in[ip++] : 'A';
       if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
          throw new IllegalArgumentException ("Caracter ilegal codificado en base 64.");
       int b0 = map2[i0];
       int b1 = map2[i1];
       int b2 = map2[i2];
       int b3 = map2[i3];
       if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
          throw new IllegalArgumentException ("Caractee ilegal codificado en base 64.");
       int o0 = ( b0       <<2) | (b1>>>4);
       int o1 = ((b1 & 0xf)<<4) | (b2>>>2);
       int o2 = ((b2 &   3)<<6) |  b3;
       out[op++] = (byte)o0;
       if (op<oLen)
        out[op++] = (byte)o1;
       if (op<oLen)
        out[op++] = (byte)o2;
    }
    return out;
 }
 
 
 public Base64Coder() {}
 
} // end class Base64Coder