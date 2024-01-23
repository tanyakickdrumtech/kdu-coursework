package kdu.spring.service;

import kdu.spring.entities.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SpeakerService {
    /**
     * bean for 1st speaker
     * @return
     */
    @Bean("sony")
    public Speaker speaker1() {
        return new Speaker("Sony", 15000);
    }

    /**
     * bean for 2nd speaker
     * @return
     */
    @Bean("bose")
    public Speaker speaker2(){
        return new Speaker("Bose",8000);
    }
}
