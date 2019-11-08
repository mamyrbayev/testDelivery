package com.delivery.testDelivery.models.audits.bilab;

import java.util.*;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
	private Socket socket;

	public ServerThread(Socket socket){
		this.socket = socket;
	}

	public Socket getSocket(){
		return socket;
	}

	public void run(){
		try{

			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			Message message;
			while((message = (Message)inStream.readObject()) != null){
				System.out.println(message);
				String res = "++";
				outStream.writeObject(res);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
