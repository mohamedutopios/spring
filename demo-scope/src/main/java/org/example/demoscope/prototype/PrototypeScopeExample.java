package org.example.demoscope.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrototypeScopeExample {

    @Autowired
    private PrototypeBean prototypeBean1;

    @Autowired
    private PrototypeBean prototypeBean2;

    public void demonstrateScope() {
        System.out.println("prototypeBean1 hashCode: " + prototypeBean1.hashCode());
        System.out.println("prototypeBean2 hashCode: " + prototypeBean2.hashCode());
    }
}

