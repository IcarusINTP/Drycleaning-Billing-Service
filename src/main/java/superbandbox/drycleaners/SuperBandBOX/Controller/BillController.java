package superbandbox.drycleaners.SuperBandBOX.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import superbandbox.drycleaners.SuperBandBOX.Models.Bill;
import superbandbox.drycleaners.SuperBandBOX.Services.BillingService;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class BillController {

	@Autowired
	private BillingService billService;

	@PostMapping
	public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
		Bill createdBill = billService.createBill(bill);
		return ResponseEntity.ok(createdBill);
	}

	@GetMapping
	public ResponseEntity<List<Bill>> getAllBills() {
		List<Bill> bills = billService.getAllBills();
		return ResponseEntity.ok(bills);
	}
}
