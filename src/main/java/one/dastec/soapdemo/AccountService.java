package one.dastec.soapdemo;

import com.google.gson.Gson;
import one.dastec.demo_web_service.Account;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {

    private Map<String, Account> accountMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() throws IOException {
        Resource accountResource = new ClassPathResource("accounts.json");
        try (Reader jsonReader = new InputStreamReader(accountResource.getInputStream())) {
            for (Account account : new Gson().fromJson(jsonReader, Account[].class)) {
                accountMap.put(account.getAccountNbr(), account);
            }
        }
    }

    public Account getAccount(String accountNbr) {
        return accountMap.get(accountNbr);
    }
}
