package one.dastec.soapdemo;

import one.dastec.demo_web_service.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@SpringBootApplication
public class SoapDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapDemoApplication.class, args);
    }

}

@RequestMapping("/rest/account")
@RestController
class AccountController {

    private final AccountService accountService;

    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/{accountNbr}")
    public Account getAccount(@PathVariable("accountNbr") String accountNbr) {
        Account account = accountService.getAccount(accountNbr);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found: " + accountNbr);
        }
        return account;
    }
}
