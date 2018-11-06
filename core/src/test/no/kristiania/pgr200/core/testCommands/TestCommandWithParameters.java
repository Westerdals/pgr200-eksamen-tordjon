package no.kristiania.pgr200.core.testCommands;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;

public class TestCommandWithParameters extends TestCommand {

    public String field1;
    public String field2;


    public TestCommandWithParameters withField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public TestCommandWithParameters withField2(String field2) {
        this.field2 = field2;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return new TestCommandWithParameters()
                .withField1(parameters.get("field1"))
                .withField2(parameters.get("field2"));
    }
}
