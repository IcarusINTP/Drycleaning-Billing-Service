package superbandbox.drycleaners.SuperBandBOX.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import superbandbox.drycleaners.SuperBandBOX.Models.Bill;
import superbandbox.drycleaners.SuperBandBOX.Models.ItemRequest;

@Service
public class BillingService {

	private Map<Long, Bill> billStorage = new HashMap<>();
	private long billIdCounter = 1;

	public BillingService() {
		// No special constructor needed
	}

	public Bill generateBill(ItemRequest request) {
		Bill bill = new Bill();
		bill.setId(billIdCounter++);
		bill.setCustomerName(request.getCustomerName());
		bill.setPhone(request.getPhone());
		bill.setAddress(request.getAddress());

		if (request.getItems() != null) {
			for (Map.Entry<String, Integer> entry : request.getItems().entrySet()) {
				String itemName = entry.getKey();
				int quantity = entry.getValue();
				int rate = 100; // You can set different rates later
				int total = rate * quantity;
				bill.addItem(itemName, quantity, rate, total);
			}
		}

		billStorage.put(bill.getId(), bill);
		return bill;
	}

	public Bill getBillById(Long billId) {
		return billStorage.get(billId);
	}
}
