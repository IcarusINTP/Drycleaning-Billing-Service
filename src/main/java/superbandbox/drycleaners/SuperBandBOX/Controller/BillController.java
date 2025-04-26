package superbandbox.drycleaners.SuperBandBOX.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import superbandbox.drycleaners.SuperBandBOX.Models.Bill;
import superbandbox.drycleaners.SuperBandBOX.Models.ItemRequest;
import superbandbox.drycleaners.SuperBandBOX.Services.BillingService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bills")
public class BillController {

	@Autowired
	private BillingService billingService;

	// Endpoint to generate a new bill
	@PostMapping
	public ResponseEntity<Bill> generateBill(@RequestBody ItemRequest request) {
		Bill bill = billingService.generateBill(request);
		return new ResponseEntity<>(bill, HttpStatus.CREATED);
	}

	// Endpoint to get a bill by ID
	@GetMapping("/{billId}")
	public ResponseEntity<Bill> getBill(@PathVariable Long billId) {
		Bill bill = billingService.getBillById(billId);
		if (bill != null) {
			return new ResponseEntity<>(bill, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
