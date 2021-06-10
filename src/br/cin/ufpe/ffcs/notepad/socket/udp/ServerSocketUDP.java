package br.cin.ufpe.ffcs.notepad.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.cin.ufpe.ffcs.notepad.impl.NotepadImpl;
import br.cin.ufpe.ffcs.notepad.model.FileStatus;

public class ServerSocketUDP {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		if (args.length < 1) return;
		int porta = Integer.parseInt(args[0]);
		
		try(DatagramSocket serverSocket = new DatagramSocket(porta)){
			System.out.println(String.format("Iniciando o server socket UDP na porta %s", porta));			
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];			
			
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);

				String sentence = new String(receivePacket.getData());
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();

				NotepadImpl notepadImpl = new NotepadImpl();
				FileStatus status = notepadImpl.writeToFileWithFileWriter(sentence.strip(), "teste.txt");
				sendData = String.format("Status da mensagem enviada foi %s", status).getBytes();
				
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
			}
		}
	}
}
