package org.example.demoscope.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionScopeExample {

    @Autowired
    private SessionBean sessionBean;

    @GetMapping("/test-session-scope")
    public String testSessionScope() {
        System.out.println("sessionBean hashCode: " + sessionBean.hashCode());
        return "Check logs for SessionBean scope!";
    }
}
