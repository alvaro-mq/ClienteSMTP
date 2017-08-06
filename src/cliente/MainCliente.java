package cliente;


public class MainCliente {
	public static void main(String arg[]){
		Cliente c=new Cliente("192.168.16.10",25);
		c.iniciar("telematica@info.net",
				"alvaro@info.net" ,
				"Prueba de correo mediante sockets java",
				"Hola esta es una prueba de correo");
	}
	
}
