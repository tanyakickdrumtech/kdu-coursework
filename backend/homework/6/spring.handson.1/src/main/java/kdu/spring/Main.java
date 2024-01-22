package kdu.spring;

import kdu.spring.config.Config;
import kdu.spring.service.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);

        logger.info("Most expensive vehicle price: " + vehicleService.findMostExpensiveVehicle().getPrice());
    }
}
