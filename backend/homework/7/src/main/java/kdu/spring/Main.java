package kdu.spring;

import kdu.spring.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.logging.Logger;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Runner rn=context.getBean(Runner.class);
        double maxi=max(rn.getFactoryOne().findMostExpensiveVehicle().getPrice(), rn.getFactoryTwo().findMostExpensiveVehicle().getPrice());
        double mini=min(rn.getFactoryOne().findLeastExpensiveVehicle().getPrice(), rn.getFactoryTwo().findLeastExpensiveVehicle().getPrice());

        String messageMost="Most expensive vehicle price:" + maxi;
        String messageLeast="Least expensive vehicle price:" + mini;

        logger.info(messageMost);
        logger.info(messageLeast);
    }
}
