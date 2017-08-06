package cliente;
import java.net.*;
import java.io.*;
import java.util.*;

import base64.Base64FileEncoder;
public class Cliente{
	private Socket sCliente;
	private PrintStream salida;
	private Scanner entrada;
	private String host;
	private int puerto;
	
	public Cliente(String h,int p){
		host =h;
		puerto=p;
	}
	public void iniciar(String correoOrigen, String correoDestino, String asunto, String mensaje){
		try{
			sCliente=new Socket(host,puerto);
			salida=new PrintStream(sCliente.getOutputStream());
			entrada=new Scanner(sCliente.getInputStream());
			/////////////////////////////////////////////
			System.out.println(entrada.nextLine());//Mensaje del Servidor

			//////Indicamos el correo de origen
			salida.println("mail from: "+correoOrigen);
			System.out.println(entrada.nextLine());//Respuesta del Servidor
			
			//////Indicamos el correo de destino
			salida.println("rcpt to: "+correoDestino);
			System.out.println(entrada.nextLine());//Respuesta del Servidor

			//////Indicamos el mensaje
			salida.println("data");
			System.out.println(entrada.nextLine());//Respuesta del Servidor		
			salida.println("From:"+correoOrigen);
			salida.println("To:"+correoDestino);
			salida.println("Subject:"+asunto);
			//1. Este es un mensaje simple pero usando MIME(Extension de Correo de Internet Multiproposito) 
			//El tipo por defecto que usa MIME es texto plano
			//Ojo: usando mime en un mensaje simple se pueden incluir acentos
				/*salida.println("MIME-Version: 1.0"); //version de MIME que usaremos
				salida.println("Content-Type: text/plain"); //tipo del archivo que enviaremos
				salida.println("Content-Transfer-Encoding: 7bit");//formato de codificacion 
				salida.println(mensaje);
			*/
			//fin de mensaje simple
			/*
			//2. Este es um mensaje que enviara contenido HTML
				salida.println("MIME-Version: 1.0"); 
				salida.println("Content-Type: text/html ; charset=\"ISO-8859-1\""); 
				salida.println("<html><head><meta http-equiv='content-type' content='text/html; charset=ISO-8859-1'></head><body bgcolor=\"blue\" text=\"white\" ><center><p id=\"WN\">HOLA MUNDO</p></center></body></html>");
				*/
			//fin de mensaje HTML
			
			//3. Este es un mensaje que enviara una imagen
			/*	salida.println("MIME-Version: 1.0"); 
				salida.println("Content-Type: image/jpeg;name=\"talento.jpg\""); 
				salida.println("Content-Transfer-Encoding: base64");
				Base64FileEncoder enviar = new Base64FileEncoder();
					StringBuffer salida1 = new StringBuffer();
					enviar.encodeStream("talento.jpg",salida1);
					System.out.println(salida1.toString());
				salida.println(salida1.toString());
				*/		
			//fin de mensaje de imagen
			
			//4. Este es um mensaje que enviara un documento word
			/*	salida.println("MIME-Version: 1.0"); 
				salida.println("Content-Type: application/octet-stream ; name=\"word.doc\""); 
				salida.println("Content-Transfer-Encoding: base64");
				salida.println("Content-Description: word.doc (Microsoft Word Document)");
				salida.println("0MR4xGuEAAAAAAAAAAAAAAPgAFFFFaaaEEEE3ADFA$E%EAFSD");
			*/
			//fin de mensaje de documento word
			
			//4. Este es um mensaje que enviara un documento word	
				salida.println("MIME-Version: 1.0"); 
				salida.println("Content-Type: application/pdf;name=\"tres.pdf\""); 
				salida.println("Content-Transfer-Encoding: base64");
				Base64FileEncoder enviar = new Base64FileEncoder();
					StringBuffer salida1 = new StringBuffer();
					enviar.encodeStream("/home/telematica/dos.pdf",salida1);
					//System.out.println(salida1.toString());
				salida.println(salida1.toString());
				
			salida.println(".");
			System.out.println(entrada.nextLine());//Respuesta del Servidor
			//////Salimos
			salida.println("quit");
			/////////////////////////////////////////////
			finalizar();
			System.out.println("Mensaje Enviado!!");
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Error Mensaje NOs Enviado!!");
			finalizar();
		}
		
	}
	public void finalizar(){
		try{
			salida.close();
			entrada.close();
			sCliente.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
