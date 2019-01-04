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

    @ShellMethod("Login.")
    public void login() { testService.login(); }

    @ShellMethod("Test student.")
    public void test() {
        testService.doTest();
    }

    @ShellMethod("Write score.")
    public void writeScore() {
        testService.writeScore();
    }
}
