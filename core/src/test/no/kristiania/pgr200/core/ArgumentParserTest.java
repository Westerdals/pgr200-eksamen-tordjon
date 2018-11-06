package no.kristiania.pgr200.core;

import org.junit.Test;

import java.util.HashMap;
import static no.kristiania.pgr200.core.ArgumentParser.getArguments;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;

public class ArgumentParserTest {
    @Test
    public void shouldGiveCorrectArguments() {

        HashMap<String, String> arguments = getArguments(new String[]{
                "-argument1", "value1", "-argument2", "value2"
        });

        assertThat(arguments)
                .hasEntrySatisfying("argument1", argument -> argument.equals("value1"))
                .hasEntrySatisfying("argument2", argument -> argument.equals("value2"));
    }

    @Test
    public void shouldNotGiveNonExistingArguments() {
        HashMap<String, String> arguments = getArguments(new String[]{
                "-argument1", "value1", "-argument2", "value2"
        });

        assertThat(arguments)
                .size()
                .isEqualTo(2);
    }

    @Test
    public void shouldThrowExceptionOnTwoOptions() {


        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            HashMap<String , String> arguments = getArguments(new String[]{
                    "-option", "-invalid", "value"
            });
        });
    }
}