package kdu.spring;

import kdu.spring.service.FactoryOne;
import kdu.spring.service.FactoryTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Runner {

    private FactoryOne factoryOne;
    private FactoryTwo factoryTwo;

    //this is the constructor injection earlier i used field injection but it was showing code smells so i switched to constructor injection.
    @Autowired
    public Runner(@Qualifier("factoryOne") FactoryOne factoryOne,
                  @Qualifier("factoryTwo") FactoryTwo factoryTwo) {
        this.factoryOne = factoryOne;
        this.factoryTwo = factoryTwo;
    }

    public FactoryOne getFactoryOne() {
        return factoryOne;
    }

    public void setFactoryOne(FactoryOne factoryOne) {
        this.factoryOne = factoryOne;
    }

    public FactoryTwo getFactoryTwo() {
        return factoryTwo;
    }

    public void setFactoryTwo(FactoryTwo factoryTwo) {
        this.factoryTwo = factoryTwo;
    }
}
