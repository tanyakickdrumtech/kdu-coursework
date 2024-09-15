package kdu.spring.service;

import kdu.spring.entities.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class TyreService {
    @Bean("mrf")
    public Tyre tyre1(){
        return new Tyre("MRF",10000);

    }
    @Bean("bridestone")
    public Tyre tyre2(){
        return new Tyre("Bridestone",5000);
    }
}
