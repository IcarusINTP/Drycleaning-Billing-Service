package superbandbox.drycleaners.SuperBandBOX.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import superbandbox.drycleaners.SuperBandBOX.Models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
	@Query("SELECT SUM(b.totalAmount) FROM Bill b WHERE b.date = :today")
	Double getTodaySales(@Param("today") LocalDate today);

	@Query("SELECT SUM(b.totalAmount) FROM Bill b WHERE b.date BETWEEN :from AND :to")
	Double getSalesBetween(@Param("from") LocalDate from, @Param("to") LocalDate to);
}