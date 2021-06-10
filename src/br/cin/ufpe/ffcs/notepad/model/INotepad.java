package br.cin.ufpe.ffcs.notepad.model;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INotepad extends Remote {
	public FileStatus writeToFile(String message, String fileName) throws FileNotFoundException, RemoteException;
	public FileStatus writeToFileWithFileWriter(String message, String fileName) throws FileNotFoundException, RemoteException;
	public String readFromFile(String fileName) throws FileNotFoundException, RemoteException;
	public FileStatus eraseFile(String fileName) throws FileNotFoundException, RemoteException;
}
