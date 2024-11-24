package org.example.demoscope.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestScopeExample {

    @Autowired
    private RequestBean requestBean;

    @GetMapping("/test-request-scope")
    public String testRequestScope() {
        System.out.println("requestBean hashCode: " + requestBean.hashCode());
        return "Check logs for RequestBean scope!";
    }
}

