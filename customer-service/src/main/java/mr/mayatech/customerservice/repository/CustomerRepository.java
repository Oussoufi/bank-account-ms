package mr.mayatech.customerservice.repository;

import mr.mayatech.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
