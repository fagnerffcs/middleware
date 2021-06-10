package br.cin.ufpe.ffcs.notepad.rmi;

import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.cin.ufpe.ffcs.notepad.model.INotepad;

public class NotepadRMIClient {
	
	private static final String FILENAME = "teste.txt";
	
	private static final int SAMPLE_SIZE = 10000;

	public void escreverNoArquivo(String msg) {
		try {
			Registry registry = LocateRegistry.getRegistry(1099);
			INotepad notepad = (INotepad) registry.lookup("INotepad");
			for (int i = 0; i < SAMPLE_SIZE; i++) {
				notepad.writeToFile(msg, FILENAME);	
			}
		} catch (RemoteException | NotBoundException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}