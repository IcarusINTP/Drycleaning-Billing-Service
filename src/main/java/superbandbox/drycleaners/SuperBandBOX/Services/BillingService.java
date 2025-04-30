package superbandbox.drycleaners.SuperBandBOX.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import superbandbox.drycleaners.SuperBandBOX.Models.Bill;
import superbandbox.drycleaners.SuperBandBOX.Models.BillItem;
import superbandbox.drycleaners.SuperBandBOX.Repository.BillRepository;

@Service
public class BillingService {

	@Autowired
	private BillRepository billRepository;

	public Bill createBill(Bill bill) {
		if (bill.getBillItems() != null) {
			for (BillItem item : bill.getBillItems()) {
				item.setBill(bill);
			}
		}
		return billRepository.save(bill);
	}

	public List<Bill> getAllBills() {
		return billRepository.findAll();
	}
}
