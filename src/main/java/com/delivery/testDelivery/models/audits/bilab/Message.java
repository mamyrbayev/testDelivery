package com.delivery.testDelivery.models.audits.bilab;

import java.util.*;
import java.io.*;

public class Message implements Serializable{
	private String name;
	private String text;
	private Date date;

	public Message(String name, String text){
		this.name = name;
		this.text = text;
		this.date = new Date();
	}

	public String toString(){
		return name+" said "+text+" at "+date.toString();
	}

}
