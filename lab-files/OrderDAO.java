package com.acme.services;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.acme.services.entities.LineItem;
import com.acme.services.entities.Order;
import com.acme.services.entities.OrderList;

public class OrderDAO {
	static Hashtable<Long, Order> database = new Hashtable<Long, Order>();
	static long nextOrderId = 1000;
	Logger logger = Logger.getLogger("OrderService");
	
	/**
	 * Creates a new order and returns the order ID.
	 * 
	 * @param o - The Order object.
	 * @return The unique ID of the order.
	 * @throws Exception
	 */
	public long createOrder(Order o) throws Exception {
		//Validate the order
		if (o.getLineItemList().size() == 0) {
			throw new Exception("Empty order");
		}
		//Assign a unique ID.
		o.setOrderId(nextOrderId++);
		//Save the order
		database.put(o.getOrderId(), o);
		//Log the order info
		logger.info("New order ID: " + o.getOrderId());
		for (LineItem li : o.getLineItemList()) {
			logger.info("Item: " + li.getPartNumber() + 
				" Qty: " + li.getQuantity());
		}
		
		return o.getOrderId();
	}
	
	public Order findOrder(long orderId) throws Exception {
		Order o = database.get(orderId);
		if (o == null) {
			logger.severe("Invalid order ID: " + orderId);
		}
		return o;
	}
	
	public void cancelOrder(long orderId) throws Exception {
		database.remove(orderId);
	}
	
	public OrderList findOrderForOrganization(String clientId) throws Exception {
		ArrayList<Order> list = new ArrayList<Order>();
		OrderList ol = new OrderList();
		Iterator<Entry<Long, Order>> iter = database.entrySet().iterator();
		
		while (iter.hasNext()) {
			Order o = iter.next().getValue();
			if (o.getClientId().equalsIgnoreCase(clientId)) {
				list.add(o);
			}
		}
		
		ol.setOrderList(list);
		return ol;
	}

	public Order updateOrder(long orderId, Order newOrder) {

		Order o = database.get(orderId);
		if (o == null) {
			return null;
		}
		newOrder.setOrderId(orderId);
		database.put(orderId, newOrder);
		return newOrder;
	}
}
