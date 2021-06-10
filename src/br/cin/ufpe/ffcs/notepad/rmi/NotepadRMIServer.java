package br.cin.ufpe.ffcs.notepad.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.cin.ufpe.ffcs.notepad.impl.NotepadImpl;

public class NotepadRMIServer {
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public static void main(String[] args) {
		try {
			NotepadImpl server = new NotepadImpl();
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("INotepad", server);
			LOGGER.info("Servidor RMI pronto");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro ao fazer o bind");
			e.printStackTrace();
		}
	}
	
}