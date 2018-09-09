package com.atomiteam.socket.mutual;

/**
 * 
 * TLS Server Application that forces client authentication
 * 
 * 
 * 
 * @author sami altundag
 *
 */
public class ApplicationServer {

	public static void main(String[] args) throws Exception {
		
		String keyStore = ApplicationServer.class.getClassLoader().getResource("com/atomiteam/socket/mutual/server.pkcs12").getFile();
		String trustStore = ApplicationServer.class.getClassLoader().getResource("com/atomiteam/socket/mutual/server.trust").getFile();
		
		System.setProperty("javax.net.ssl.keyStore", keyStore);
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.debug", "all");
		
		
		System.setProperty("javax.net.ssl.trustStore", trustStore);
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		
		
		GreetServer server = new GreetServer();
		server.start(6666);

	}

}
