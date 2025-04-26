package superbandbox.drycleaners.SuperBandBOX.Models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String customerName;
	private String phone;
	private String address;
	private double totalAmount;
	private double gst;
	private double discount;

	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BillItem> items;

	private LocalDate date; // Add the date field

	public Bill() {
	}

	public Bill(String customerName, String phone, String address, double totalAmount, double gst, double discount,
			LocalDate date) {
		this.customerName = customerName;
		this.phone = phone;
		this.address = address;
		this.totalAmount = totalAmount;
		this.gst = gst;
		this.discount = discount;
		this.date = date;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public List<BillItem> getItems() {
		return items;
	}

	public void setItems(List<BillItem> items) {
		this.items = items;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void addItem(String itemName, int quantity, double rate, double total) {
		BillItem billItem = new BillItem(itemName, quantity, rate, total, this);
		items.add(billItem);
	}

	public void removeItem(BillItem billItem) {
		items.remove(billItem);
	}
}
