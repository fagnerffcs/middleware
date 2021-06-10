package br.cin.ufpe.ffcs.notepad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import br.cin.ufpe.ffcs.notepad.rmi.NotepadRMIClient;

public class MainRMIClientExample {
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final int THREAD_SIZE = 10;

	public static void main(String[] args) {
		// provides a pool of threads and an API for assigning tasks to it.
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_SIZE);
		for (int i = 0; i < THREAD_SIZE; i++) {
			Runnable worker = new ClientRunnable(""+(i+1));
			executor.execute(worker);
		}
		//nao permite que novas threads sejam adicionadas
		executor.shutdown();
		
		//aguarda a execucao de todas as threads
		while(!executor.isTerminated()) {
			
		}
		LOGGER.info("Todas as threads foram concluidas.");
	}
	
	public static class ClientRunnable implements Runnable {
		private final String id;
		
		public ClientRunnable(String id) {
			this.id = id;
		}

		@Override
		public void run() {
			NotepadRMIClient clientRMI = new NotepadRMIClient();
			long tempoInicial = System.currentTimeMillis();
			clientRMI.escreverNoArquivo("Mensagem do cliente " + id + " RMI TCP no arquivo.");
			LOGGER.info(String.format("O método foi executado em %s milessegundos ", (System.currentTimeMillis() - tempoInicial)));
		}
	}

}