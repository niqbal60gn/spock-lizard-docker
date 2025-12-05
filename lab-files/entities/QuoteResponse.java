package com.acme.services.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="quote_response")
public class QuoteResponse {
String partNumber;
int quantity;
String clientId;
String dateRequiredBy;
double unitPrice;
double totalPrice;
boolean productAvailable;

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
public String getClientId() {
	return clientId;
}
public void setClientId(String clientId) {
	this.clientId = clientId;
}
public String getDateRequiredBy() {
	return dateRequiredBy;
}
public void setDateRequiredBy(String dateRequiredBy) {
	this.dateRequiredBy = dateRequiredBy;
}
public double getUnitPrice() {
	return unitPrice;
}
public void setUnitPrice(double unitPrice) {
	this.unitPrice = unitPrice;
}
public double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}
public boolean isProductAvailable() {
	return productAvailable;
}
public void setProductAvailable(boolean productAvailable) {
	this.productAvailable = productAvailable;
}

}
