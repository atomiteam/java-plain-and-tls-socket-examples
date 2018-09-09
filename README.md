# java-plain-and-tls-socket-examples
Java Plain and Server and/or Client Certificate Socket examples 

This project has three client and server applications in Java. 
The Applications are doing the same i.e. the server echoes the message it receives from the client;
However the communication is varying from unencrypted to TLS two side certificate authentication.

As of date, there is not educative, simple Java Socket examples demonstrating different encyrption options.
The project has been created to be educative for TLS beginners who code in Java.

Lets explore packages:

* com.atomiteam.socket.plain: Client and Server applications that talk in unencrypted manner.

* com.atomiteam.socket.tls: Client and Server applications that talk in TLS and the client authenticates the server certificate.

* com.atomiteam.socket.mutual: Client and Server applications that talk in TLS and both the client and server authenticates the certificate of the other side, which is called Mutual SSL Authentication.




