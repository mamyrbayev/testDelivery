package com.delivery.testDelivery.models.audits.bilab;

import java.util.*;
import java.io.*;
import java.net.*;

public class Client{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try{
			Socket socket = new Socket("127.0.0.1", 2020);
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Insert your name:");
			String name = sc.next();
			System.out.println("Message:");
			while(true){
				
				String m = sc.next();
				Message message = new Message(name, m);
				outStream.writeObject(message);
				String response = (String)inStream.readObject();
				System.out.println(response);
				
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
