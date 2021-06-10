package br.cin.ufpe.ffcs.notepad.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketTCP {
	
	private static final int SAMPLE_SIZE = 10000;
	
	private int porta;

	public void iniciarCliente(String mensagem) throws IOException, ClassNotFoundException {
		//abrindo socket
		try(Socket socket = new Socket("localhost", porta)) {
			 // writing to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
  
            // reading from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			for (int i = 0; i < SAMPLE_SIZE; i++) {
				//enviando mensagem e obtendo resposta do servidor
				out.println(mensagem + "Execucao " + (i+1));
				out.flush();
			}
			System.out.println(String.format("Resposta do servidor: %s", in.readLine()));
			//fechando streams
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public ClientSocketTCP() {
		super();
	}

	public ClientSocketTCP(int porta) {
		super();
		this.porta = porta;
	}	
	
}
