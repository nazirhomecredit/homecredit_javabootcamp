package com.labs.spring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CarApp {
    public static void main(String[] args) {
        //STEP 1: Create IoC Container
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("AppConfig");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(ctx);

        Car swift = ctx.getBean("swift", Car.class);
//        System.out.println(swift.getMaker() + " " + swift.getModel() + " " + swift.getColor() + " " + swift.getEngine().getType());
        System.out.println(swift.getMaker() + " " + swift.getModel() + " " + swift.getColor() + " " + swift.getEngine().getType());
    }
}
