package br.cin.ufpe.ffcs.notepad;

import java.io.IOException;

import br.cin.ufpe.ffcs.notepad.socket.tcp.ClientSocketTCP;

public class MainSocketTCP {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		Runnable runnable = () -> { 
			ClientSocketTCP client = new ClientSocketTCP(1223);
			try {
				long tempoInicial = System.currentTimeMillis();
				client.iniciarCliente("Mensagem do cliente TCP.");
				System.out.println(String.format("O método foi executado em %s milessegundos ", (System.currentTimeMillis() - tempoInicial)));				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		Thread t1 = new Thread(runnable);
//		Thread t2 = new Thread(runnable);
//		Thread t3 = new Thread(runnable);
//		Thread t4 = new Thread(runnable);
//		Thread t5 = new Thread(runnable);
//		Thread t6 = new Thread(runnable);
//		Thread t7 = new Thread(runnable);
//		Thread t8 = new Thread(runnable);
//		Thread t9 = new Thread(runnable);
//		Thread t10 = new Thread(runnable);
		
		t1.start();		
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();
//		t6.start();
//		t7.start();
//		t8.start();
//		t9.start();
//		t10.start();
	}

}
