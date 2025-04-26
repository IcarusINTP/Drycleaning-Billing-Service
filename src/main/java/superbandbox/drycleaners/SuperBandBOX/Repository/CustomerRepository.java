package superbandbox.drycleaners.SuperBandBOX.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import superbandbox.drycleaners.SuperBandBOX.Models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
