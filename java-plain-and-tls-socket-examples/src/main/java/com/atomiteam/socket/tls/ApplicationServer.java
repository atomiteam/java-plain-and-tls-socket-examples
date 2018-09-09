package com.atomiteam.socket.tls;

/**
 * 
 * TLS Server Application that only does  server certificate authentication
 * 
 * 
 * 
 * @author sami altundag
 *
 */
public class ApplicationServer {

	public static void main(String[] args) throws Exception {

		String keyStore = ApplicationServer.class.getClassLoader().getResource("com/atomiteam/socket/tls/server.pkcs12")
				.getFile();

		System.setProperty("javax.net.ssl.keyStore", keyStore);
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.debug", "all");
		GreetServer server = new GreetServer();
		server.start(6666);

	}

}
