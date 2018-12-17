package ru.otus.commandLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.service.TestService;

@Component
public class TestRunner implements CommandLineRunner {

    private TestService testService;

    public TestRunner(TestService testService) {
        this.testService = testService;
    }

    @Override
    public void run(String... args) throws Exception {
        testService.doTest(System.in, System.out);
    }
}
