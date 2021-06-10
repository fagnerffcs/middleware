package br.cin.ufpe.ffcs.notepad.socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTCP {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		if (args.length < 1) return;
		
		int porta = Integer.parseInt(args[0]);
		
		try(ServerSocket serverSocket = new ServerSocket(porta)){
			System.out.println(String.format("Iniciando o server socket TCP na porta %s", porta));
			serverSocket.setReuseAddress(true);
			
			while(true) {
				Socket conn = serverSocket.accept();	
				//criando handler do cliente
				ClientHandler clientSock = new ClientHandler(conn);
				//iniciando thread
				new Thread(clientSock);
			}
		}
	}
	
}
