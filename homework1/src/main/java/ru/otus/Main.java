package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.TestService;

/**
 * Created by alina on 09.12.2018.
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        TestService testService = context.getBean(TestService.class);
        testService.doTest(System.in, System.out);
    }
}

