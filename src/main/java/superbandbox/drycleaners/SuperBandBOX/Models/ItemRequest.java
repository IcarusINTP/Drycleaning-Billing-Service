package superbandbox.drycleaners.SuperBandBOX.Models;

import java.util.HashMap;
import java.util.Map;

public class ItemRequest {

	private String customerName;
	private String phone;
	private String address;
	private Map<String, Integer> items = new HashMap<>(); // initialized to empty map

	public ItemRequest() {
		// Default constructor
	}

	// Getters and Setters

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, Integer> getItems() {
		if (items == null) {
			items = new HashMap<>();
		}
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		if (items == null) {
			this.items = new HashMap<>();
		} else {
			this.items = items;
		}
	}
}
