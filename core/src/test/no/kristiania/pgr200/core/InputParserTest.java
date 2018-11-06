package no.kristiania.pgr200.core;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.testCommands.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class InputParserTest {

    /*
        To avoid circular dependecies between core-server and core-client, we are
        using some testCommands.
        They are not inner classes. That makes .newInstance() in InputParser throw an exception
    */

    private static Map<String, Class<? extends Command>> map = new HashMap<>();

    @BeforeClass
    public static void populateSamples() {

        map.put("first", FirstTestCommand.class);
        map.put("second", SecondTestCommand.class);
        map.put("third", ThirdTestCommand.class);
        map.put("withParameters", TestCommandWithParameters.class);
    }

    @Test
    public void shoudReturnCorrectCommands() {

        for(Map.Entry<String, Class<? extends Command>> entry : map.entrySet()) {
            Command command = InputParser.decodeInput(map, entry.getKey(), new HashMap<>());

            assertThat(command)
                    .isInstanceOf(entry.getValue());assertThat(command)
                    .isInstanceOf(entry.getValue());
        }
    }

    @Test
    public void shouldBuildWithCorrectParameters() throws NoSuchFieldException, IllegalAccessException {
        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("field1", "field1 value");
        parameters.put("field2", "field2 value");


        TestCommandWithParameters command = (TestCommandWithParameters) InputParser.decodeInput(map, "withParameters", parameters);

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            assertThat(command.getClass().getDeclaredField(entry.getKey()).get(command))
                    .isEqualTo(entry.getValue());
        }
    }

    @Test
    public void invalidCommandReturnsNull() {
        Command command = InputParser.decodeInput(map, "invalid", new HashMap<>());
        assertThat(command).isNull();
    }

    // Inner classes gives InstantiationException
    private class WillThrowExceptionCommand extends TestCommand{}
    @Test
    public void shouldNotCrashOnInstantiationException() {
        HashMap<String, Class<? extends Command>> map = new HashMap<>();
        map.put("exception", WillThrowExceptionCommand.class);

        InputParser.decodeInput(map, "exception", new HashMap<>());
    }
}