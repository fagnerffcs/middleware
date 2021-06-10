package br.cin.ufpe.ffcs.notepad.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class ClientSocketUDP {
	
	private static final int SAMPLE_SIZE = 10000;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private int porta;
	private String host = "localhost";
	
	public void iniciarComunicacao(String mensagem) throws IOException, ClassNotFoundException {
		for (int i = 0; i < SAMPLE_SIZE; i++) {
			
			byte[] dadosAEnviar = new byte[1024];
			DatagramPacket pacotesAEnviar = null;
			
			byte[] dadosDoServidor = new byte[1024];
			DatagramPacket pacotesDoServidor = null;
			String msgDoServidor = null;
			
			InetAddress IPAddress = InetAddress.getByName(host);
			
			//criando datagram socket e enviando para servidor
			try(DatagramSocket clienteSocket = new DatagramSocket()){
				dadosAEnviar = mensagem.getBytes();
				pacotesAEnviar = new DatagramPacket(dadosAEnviar, dadosAEnviar.length, IPAddress, porta);
				clienteSocket.send(pacotesAEnviar);
				
				//recebendo resposta do servidor
				pacotesDoServidor = new DatagramPacket(dadosDoServidor, dadosDoServidor.length);
				clienteSocket.receive(pacotesDoServidor);
				msgDoServidor = new String(pacotesDoServidor.getData());
				
//				LOGGER.info(String.format("Resposta do servidor: %s", msgDoServidor));
			}				
		}
	}		

	public ClientSocketUDP() {
		super();
	}

	public ClientSocketUDP(int porta) {
		super();
		this.porta = porta;
	}
	
}
