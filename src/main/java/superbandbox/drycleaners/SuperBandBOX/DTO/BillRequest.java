package superbandbox.drycleaners.SuperBandBOX.DTO;

import java.util.List;

public class BillRequest {

	private String customerName;
	private List<BillItemRequest> billItems;
	private double gstPercentage;
	private double discountPercentage;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<BillItemRequest> getBillItems() {
		return billItems;
	}

	public void setBillItems(List<BillItemRequest> billItems) {
		this.billItems = billItems;
	}

	public double getGstPercentage() {
		return gstPercentage;
	}

	public void setGstPercentage(double gstPercentage) {
		this.gstPercentage = gstPercentage;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public static class BillItemRequest {

		private String itemName;
		private int quantity;
		private double pricePerItem;

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getPricePerItem() {
			return pricePerItem;
		}

		public void setPricePerItem(double pricePerItem) {
			this.pricePerItem = pricePerItem;
		}
	}
}
