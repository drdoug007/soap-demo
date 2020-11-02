package one.dastec.soapdemo;

import one.dastec.demo_web_service.GetAccountRequest;
import one.dastec.demo_web_service.GetAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AccountEndPoint {

    public static final String NAMESPACE_URI = "http://dastec.one/demo-web-service";

    private final AccountService accountService;

    @Autowired
    public AccountEndPoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccountRequest")
    @ResponsePayload
    public GetAccountResponse getAccount(@RequestPayload GetAccountRequest request) {
        GetAccountResponse response = new GetAccountResponse();
        response.setAccount(accountService.getAccount(request.getAccountNbr()));
        return response;
    }

}
