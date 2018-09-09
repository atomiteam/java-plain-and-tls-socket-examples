# java-plain-and-tls-socket-examples
Java Plain and Server and/or Client Certificate Socket examples 

This project has three client and server applications in Java. 
The Applications are doing the same i.e. the server echoes the message it receives from the client;
However the communication is varying from unencrypted to TLS two side certificate authentication.

As of date, there is not educative, simple Java Socket examples demonstrating different encyrption options.
The project has been created to be educative for TLS beginners who code in Java.




Lets explore packages:

* com.atomiteam.socket.plain: Client and Server applications that talk in unencrypted manner.

* com.atomiteam.socket.tls: Client and Server applications that talk in TLS and the only client authenticates the server certificate. This is the usual way Internet Browsers talk to Web Servers in SSL manner as it is not practical for clients to have clients certificates. 

In this case, the server has a key store that contains the server's private key and certificate. The client, in return, stores the server certificate into its trust store.

* com.atomiteam.socket.mutual: Client and Server applications that talk in TLS and both the client and server authenticates the certificate of the other side, which is called Mutual SSL Authentication. Mutual SSL authentication or certificate based mutual authentication refers to two parties authenticating each other through verifying the provided digital certificate so that both parties are assured of the others' identity.

In this case, both the server and the client have key stores for their private keys and certificates. They also keep the other side's certificate in their trust stores. 

For simplicity, self signed certificates are used. The scripts (cert.txt) and generated files are in the same package of the applications.


Plain socket is opened through ServerSocket.

```java
serverSocket = new ServerSocket(port);
```

TLS socket is opened through SSLServerSocketFactory.

```java

SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
serverSocket = ssf.createServerSocket(port);

```

The client certificate authentication is forced through below line.

```java
serverSocket.setNeedClientAuth(true);

```

Key store and trust stores are set into system variables programmatically. 

```java

String keyStore = ApplicationServer.class.getClassLoader()
				.getResource("com/atomiteam/socket/mutual/client.pkcs12").getFile();
String trustStore = ApplicationServer.class.getClassLoader()
				.getResource("com/atomiteam/socket/mutual/client.trust").getFile();

System.setProperty("javax.net.ssl.trustStore", trustStore);
System.setProperty("javax.net.ssl.trustStorePassword", "password");
System.setProperty("javax.net.ssl.trustStoreType", "JKS");

System.setProperty("javax.net.ssl.keyStore", keyStore);
System.setProperty("javax.net.ssl.keyStorePassword", "password");

System.setProperty("javax.net.debug", "all");

````
