package mr.mayatech.accountservice.web;

import mr.mayatech.accountservice.clients.CustomerRestClient;
import mr.mayatech.accountservice.entities.BankAccount;
import mr.mayatech.accountservice.models.Customer;
import mr.mayatech.accountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }
    @GetMapping
    public List<BankAccount> accountList(){
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        bankAccountList.forEach(bankAccount -> {
            bankAccount.setCustomer(customerRestClient.findCustomerById(bankAccount.getCustomerId()));
        });

        return bankAccountList;
    }

    @GetMapping("/{id}")
    public BankAccount bankAccountById(@PathVariable String id){
        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
