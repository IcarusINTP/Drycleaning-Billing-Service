package superbandbox.drycleaners.SuperBandBOX.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import superbandbox.drycleaners.SuperBandBOX.Models.Bill;
import superbandbox.drycleaners.SuperBandBOX.Repository.BillRepository;
import superbandbox.drycleaners.SuperBandBOX.Repository.CustomerRepository;

@Service
public class BillService {

	private final BillRepository billRepository;
	private final CustomerRepository customerRepository;

	@Autowired
	public BillService(BillRepository billRepository, CustomerRepository customerRepository) {
		this.billRepository = billRepository;
		this.customerRepository = customerRepository;
	}

	public Bill saveBill(Bill bill) {
		bill.setDate(LocalDate.now());

		double total = bill.getBillItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

		if (bill.isGst()) {
			total += total * 0.18; // Add 18% GST
		}

		total -= total * (bill.getDiscount() / 100.0); // Apply discount

		bill.setTotalAmount(total);

		// This will ensure each BillItem is linked to this bill
		bill.setBillItems(bill.getBillItems());

		return billRepository.save(bill);
	}

	public Double getTodaySales(LocalDate today) {
		return billRepository.getTodaySales(today);
	}

	public Double getSalesBetween(LocalDate from, LocalDate to) {
		return billRepository.getSalesBetween(from, to);
	}

	public List<Bill> getAllBills() {
		return billRepository.findAll();
	}
}
