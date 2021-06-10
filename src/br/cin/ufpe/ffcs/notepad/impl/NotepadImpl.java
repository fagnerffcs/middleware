package br.cin.ufpe.ffcs.notepad.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.cin.ufpe.ffcs.notepad.model.FileStatus;
import br.cin.ufpe.ffcs.notepad.model.INotepad;

public class NotepadImpl extends UnicastRemoteObject implements INotepad {
	
	private static final long serialVersionUID = 1L;

	@Override
	public FileStatus writeToFile(String message, String fileName) {
		Path filePath = Paths.get("C:/", "Temp", fileName);

		try {
			// escrevendo mensagem no arquivo, caso nao exista, cria
			if(!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
				Files.createFile(filePath);
			}
			Files.writeString(filePath, message+"\n", StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
			return FileStatus.ERRO_AO_ESCREVER;
		}
		return FileStatus.SUCESSO;
	}

	@Override
	public String readFromFile(String fileName) throws FileNotFoundException {
		String dirName = System.getenv("DIR_NAME") != null ? System.getenv("DIR_NAME") : "C:/Temp";
		Path filePath = Paths.get(dirName, fileName);

		try {
			String content = Files.readString(filePath);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public FileStatus writeToFileWithFileWriter(String message, String fileName) throws FileNotFoundException {
		try(FileWriter fw = new FileWriter(new File(fileName))) {
			fw.write(message.trim());
			fw.flush();
			return FileStatus.SUCESSO;
		} catch (IOException e) {
			e.printStackTrace();
			return FileStatus.ERRO_AO_ESCREVER;
		}
	}

	@Override
	public FileStatus eraseFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		if(file.delete()) {
			return FileStatus.ARQUIVO_REMOVIDO;
		}
		
		return FileStatus.ARQUIVO_NAO_REMOVIDO;
	}

	public NotepadImpl() throws RemoteException {
		super();
	}
	
}
