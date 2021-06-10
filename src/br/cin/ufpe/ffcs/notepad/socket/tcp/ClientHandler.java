package br.cin.ufpe.ffcs.notepad.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import br.cin.ufpe.ffcs.notepad.impl.NotepadImpl;
import br.cin.ufpe.ffcs.notepad.model.FileStatus;

public class ClientHandler implements Runnable {
    final Socket clientSocket;
    
    public ClientHandler(Socket socket) {
    	this.clientSocket = socket;
	}
    
    @Override
    public void run() {
    	PrintWriter out = null;
    	BufferedReader in = null;
    	try {
	    	while(true) {
				//lendo dados enviados pelo cliente e chamando a classe que salva a mensagem em arquivo
				String mensagem = "";
				try {
					//obtem o objeto OutputStream do Client
					out = new PrintWriter(this.clientSocket.getOutputStream(), true);
					
					in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
					
					while( (mensagem = in.readLine()) != null) {
						NotepadImpl notepadImpl = new NotepadImpl();
						FileStatus status = notepadImpl.writeToFile(mensagem, "teste.txt");
						out.print(String.format("Status da mensagem enviada foi %s", status));
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
