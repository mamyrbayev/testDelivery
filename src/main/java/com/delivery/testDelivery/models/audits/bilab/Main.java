package com.delivery.testDelivery.models.audits.bilab;

import java.util.*;
import java.io.*;
import java.net.*;

public class Main{
	public static void main(String[] args) {

		try{
			ServerSocket serverSocket = new ServerSocket(2020);
			System.out.println("Wainting for a client...");


			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Client connected!!!");
				ServerThread st = new ServerThread(socket);
				st.start();
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
