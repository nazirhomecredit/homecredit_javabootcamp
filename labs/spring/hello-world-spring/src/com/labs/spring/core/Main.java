package com.labs.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
	// write your cod;
        ApplicationContext ctx= new ClassPathXmlApplicationContext("beans-config.xml");
        System.out.println(ctx);
        ctx.getBean("greetings",Greetings.class);


//        Greetings greetings=  ctx.getBean("greetings",Greetings.class);
//        System.out.println(greetings.getMessage());
//        Greetings greetings1=  ctx.getBean("greetings1",Greetings.class);
//        System.out.println(greetings1.getMessage());

        for(String beanName:ctx.getBeanDefinitionNames())
        {
            System.out.println(beanName);
        }

    }
}
