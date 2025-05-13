package superbandbox.drycleaners.SuperBandBOX.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import superbandbox.drycleaners.SuperBandBOX.Models.Bill;
import superbandbox.drycleaners.SuperBandBOX.Services.BillService;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "http://localhost:3000") // Ensure this matches your React frontend URL
public class BillController {

	@Autowired
	private BillService billService;

	@PostMapping("/create")
	public Bill createBill(@RequestBody Bill bill) {
		return billService.saveBill(bill);
	}

	@GetMapping
	public List<Bill> getAllBills() {
		return billService.getAllBills();
	}

	@GetMapping("/sales/today")
	public Double getTodaySales() {
		return billService.getTodaySales(LocalDate.now());
	}

	@GetMapping("/sales/range")
	public ResponseEntity<?> getSalesBetween(@RequestParam String from, @RequestParam String to) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fromDate = LocalDate.parse(from, formatter);
			LocalDate toDate = LocalDate.parse(to, formatter);

			Double sales = billService.getSalesBetween(fromDate, toDate);
			return ResponseEntity.ok(sales != null ? sales : 0.0);

		} catch (DateTimeParseException e) {
			return ResponseEntity.badRequest().body("Invalid date format. Please use 'yyyy-MM-dd' (e.g., 2025-05-01).");
		}
	}
}
