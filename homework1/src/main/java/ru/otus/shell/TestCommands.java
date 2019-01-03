package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.TestService;

@ShellComponent
public class TestCommands {

    private TestService testService;

    public TestCommands(TestService testService) {
        this.testService = testService;
    }

    @ShellMethod("Test student.")
    public void test() {
        testService.doTest(System.in, System.out);
    }
}
