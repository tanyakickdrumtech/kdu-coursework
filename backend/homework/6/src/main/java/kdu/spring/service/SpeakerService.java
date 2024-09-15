package kdu.spring.service;

import kdu.spring.entities.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpeakerService {
    @Bean("sony")
    public Speaker speaker1() {
        return new Speaker("Sony", 15000);
    }
    @Bean("bose")
    public Speaker speaker2(){
        return new Speaker("Bose",8000);

    }
}
