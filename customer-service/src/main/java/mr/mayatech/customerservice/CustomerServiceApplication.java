package mr.mayatech.customerservice;

import mr.mayatech.customerservice.config.GlobalConfig;
import mr.mayatech.customerservice.entities.Customer;
import mr.mayatech.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {

			List<Customer> customerList = List.of(
					Customer.builder()
							.firstname("Hassan")
							.lastname("Elhoumi")
							.email("hassan@gmail.com")
							.build(),
					Customer.builder()
							.firstname("Mohamed")
							.lastname("Elhannaoui")
							.email("mohamed@gmail.com")
							.build(),
					Customer.builder()
							.firstname("Oussoufi")
							.lastname("Camara")
							.email("oussfi@gmail.com")
							.build()
			);

			customerRepository.saveAll(customerList);
		};
	}
}
