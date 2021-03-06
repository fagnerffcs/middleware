package br.cin.ufpe.ffcs.notepad;

import java.io.IOException;

import br.cin.ufpe.ffcs.notepad.socket.udp.ClientSocketUDP;

public class MainUDP {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Runnable runnable = () -> { 
			long tempoInicial = System.currentTimeMillis();
			ClientSocketUDP clientUDP = new ClientSocketUDP(1222);
			try {
				clientUDP.iniciarComunicacao("Mensagem do cliente UDP.");
				System.out.println(String.format("O m?todo foi executado em %s milessegundos ", (System.currentTimeMillis() - tempoInicial)));				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		Thread t3 = new Thread(runnable);
		Thread t4 = new Thread(runnable);
		Thread t5 = new Thread(runnable);
		Thread t6 = new Thread(runnable);
		Thread t7 = new Thread(runnable);
		Thread t8 = new Thread(runnable);
		Thread t9 = new Thread(runnable);
		Thread t10 = new Thread(runnable);
		
		t1.start();		
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
	}

}
