package com.taufik.Model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataStored {
	public DataStored() {
		// TODO Auto-generated constructor stub
	}
	//Make Entity 
	private String itemName;
	private String itemContent;
	private int itemType;
	
	//Generate getter and setter 
	public String getItemName() {
		return itemName;
	}
	@XmlElement
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemContent() {
		return itemContent;
	}
	@XmlElement
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}
	
	public int getItemType() {
		return itemType;
	}
	@XmlElement
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	
	//make data stored in  
	public DataStored(String itemName, String itemContent, int itemType) {
		super();
		this.itemName = itemName;
		this.itemContent = itemContent;
		this.itemType = itemType;
	}
}
