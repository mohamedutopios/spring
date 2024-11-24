package org.example.demoscope.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingletonScopeExample {

    @Autowired
    private SingletonBean singletonBean1;

    @Autowired
    private SingletonBean singletonBean2;

    public void demonstrateScope() {
        System.out.println("singletonBean1 hashCode: " + singletonBean1.hashCode());
        System.out.println("singletonBean2 hashCode: " + singletonBean2.hashCode());
    }

}

