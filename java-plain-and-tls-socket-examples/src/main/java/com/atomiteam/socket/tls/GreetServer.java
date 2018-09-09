package com.atomiteam.socket.tls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;

public class GreetServer {
	private ServerSocket serverSocket;

	public void start(int port) {
		try {

			SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

			serverSocket = ssf.createServerSocket(port);
			while (true)
				new EchoClientHandler(serverSocket.accept()).start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class EchoClientHandler extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;

		public EchoClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		public void run() {
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String inputLine;
				while (true) {
					System.out.println("Server is waiting for a line");
					inputLine = in.readLine();
					System.out.println("Line is " + inputLine);
					if (".".equals(inputLine)) {
						out.println("bye");
						break;
					}
					out.println("Echoing  " + inputLine);
					System.out.println(inputLine);
				}

				in.close();
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}