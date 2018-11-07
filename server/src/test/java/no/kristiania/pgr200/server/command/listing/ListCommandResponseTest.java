package no.kristiania.pgr200.server.command.listing;

import javax.sql.DataSource;

public class ListCommandResponseTest { //TODO: Heter ListCommandResponseTest fordi det er responsen som testes. Kan også hete ListCommandTest

    //private ListCommand command;
    private DataSource dataSource;

    /*@Before
    public void pickRandomListCommand() {

        ListCommand[] commands = {
                new ServerListConferencesCommand(),
                new ServerListDaysCommand(),
                new ServerListSpecificTalkCommand(),
                new ServerListTalksCommand(),
                new ServerListTimeslotsCommand()
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
    }*/
}