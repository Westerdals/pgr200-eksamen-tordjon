package no.kristiania.pgr200.server.command.listing;

import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.database.Util;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ListCommandResponseTest { //TODO: Heter ListCommandResponseTest fordi det er responsen som testes. Kan også hete ListCommandTest

    private ListCommand command;
    private DataSource dataSource;

    @Before
    public void pickRandomListCommand() {

        ListCommand[] commands = {
                new ListConferencesCommand(),
                new ListDaysCommand(),
                new ListSpecificTalkCommand(),
                new ListTalksCommand(),
                new ListTimeslotsCommand()
        };

        int index = new Random().nextInt(commands.length);

        command = commands[index];
    }

    @Before
    public void setDataSource() throws IOException {
        dataSource = dataSource = Util.createDataSource("test.properties");
    }

    @Test
    public void testStatus() throws SQLException {
        ServerResponse response = command.execute(dataSource);

        assertThat(response.getStatus().getCode()).isEqualTo(200);
    }

    @Test
    public void testContentLength() throws SQLException {
        // Do not have the content here, but we can check that it is set
        ServerResponse response = command.execute(dataSource);

        assertThat(response.getHeaders().get("Content-Length"))
                .isNotNull();
    }

    @Test
    public void testContentType() throws SQLException {
        // Do not have the content here, but we can check that it is set
        ServerResponse response = command.execute(dataSource);

        assertThat(response.getHeaders().get("Content-Type"))
                .isEqualTo("application/json");
    }
}