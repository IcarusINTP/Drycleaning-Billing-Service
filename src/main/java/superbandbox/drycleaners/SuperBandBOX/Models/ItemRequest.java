package superbandbox.drycleaners.SuperBandBOX.Models;

import java.util.List;

public class ItemRequest {

	private String name;
	private String phone;
	private String address;
	private List<String> items;

	// Default constructor
	public ItemRequest() {
	}

	// Parameterized constructor
	public ItemRequest(String name, String phone, String address, List<String> items) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.items = items;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
}
