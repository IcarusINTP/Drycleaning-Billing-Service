package superbandbox.drycleaners.SuperBandBOX.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import superbandbox.drycleaners.SuperBandBOX.Models.Bill;
import superbandbox.drycleaners.SuperBandBOX.Models.BillItem;
import superbandbox.drycleaners.SuperBandBOX.Models.Customer;
import superbandbox.drycleaners.SuperBandBOX.Models.ItemRequest;
import superbandbox.drycleaners.SuperBandBOX.Repository.BillRepository;
import superbandbox.drycleaners.SuperBandBOX.Repository.CustomerRepository;

@Service
public class BillingService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private BillRepository billRepo;

	// Price list for different items
	private final Map<String, Double> priceList = new HashMap<>();

	public BillingService() {
		// Initialize price list for various items
		priceList.put("shirt", 200.0);
		priceList.put("pants", 300.0);
		priceList.put("coat", 500.0);
		priceList.put("kurta", 250.0);
		priceList.put("blazer", 550.0);
		priceList.put("stain_removal", 300.0);
		priceList.put("tough_stain_removal", 500.0);
	}

	public Bill getBillById(Long billId) {
		return billRepo.findById(billId).orElse(null); // Returns null if bill is not found
	}

	public Bill generateBill(ItemRequest request) {
		// Create customer and save it to the repository
		Customer customer = new Customer();
		customer.setName(request.getName());
		customer.setPhone(request.getPhone());
		customer.setAddress(request.getAddress());
		customer = customerRepo.save(customer);

		// Create list of BillItems for the current bill
		List<BillItem> billItems = new ArrayList<>();
		double totalAmount = 0.0;

		for (String itemName : request.getItems()) {
			double rate = priceList.getOrDefault(itemName.toLowerCase(), 0.0);
			double itemTotal = rate * 1; // Assuming quantity is always 1 for simplicity
			totalAmount += itemTotal;

			// Create BillItem and add it to the list
			BillItem billItem = new BillItem(itemName, 1, rate, itemTotal, null); // We'll set the Bill later
			billItems.add(billItem);
		}

		// Calculate GST and discount (example: 18% GST, 10% discount)
		double gst = totalAmount * 0.18;
		double discount = totalAmount * 0.10;
		double finalAmount = totalAmount + gst - discount;

		// Create Bill object
		Bill bill = new Bill();
		bill.setCustomer(customer);
		bill.setItems(billItems);
		bill.setTotalAmount(finalAmount);
		bill.setGst(gst);
		bill.setDiscount(discount);
		bill.setDate(LocalDate.now());

		// Save Bill and BillItems
		bill = billRepo.save(bill);

		// Associate the Bill with each BillItem
		for (BillItem billItem : billItems) {
			billItem.setBill(bill);
			// Save BillItems with the associated Bill
			// You might need a BillItemRepository to save them separately
		}

		return bill;
	}

}