package com.acme.services.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="lineitem")
public class LineItem {
	String partNumber;
	int quantity;

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
