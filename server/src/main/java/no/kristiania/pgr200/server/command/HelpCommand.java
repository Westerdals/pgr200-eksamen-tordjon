package no.kristiania.pgr200.server.command;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

public class HelpCommand extends Command {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return this;
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {

        System.out.println(
                "-----------------------------------\n" +
                "|These are the possible commands: |\n" +
                "-----------------------------------\n" +
                "reset db\n" +
                "insert talk\n" +
                "    \t-title TALKNAME\n" +
                "    \t-description DESCRIPTION\n" +
                "    \t-topic TOPIC\n" +
                "insert conference\n" +
                "    \t-name NAME\n" +
                "insert timeslot\n" +
                "    \t-start HH:MM\n" +
                "    \t-end HH:MM\"\n" +
                "insert day\n" +
                "    \t-date DD.MM.YYYY\n" +
                "list talks\n" +
                "list conferences\n" +
                "list timeslots\n" +
                "list days\n" +
                "update talk\n" +
                "    \t-talk TALKNAME\n" +
                "    \t-description DESCRIPTION\n" +
                "    \t-topic TOPIC\n" +
                "    \t-id ID\n" +
                "update conference\n" +
                "   \t-name NAME\n" +
                "    \t-id ID" +
                "udpate timeslot\n" +
                "   \t-start HH:MM\n" +
                "   \t-end HH:MM\"\n" +
                "    \t-id ID\n" +
                "update day\n" +
                "   \t-date DD.MM.YYYY\n" +
                "    \t-id ID\n" +
                "delete talk\n" + 
                "   \t-id ID\n" +
                "delete conference\n" + 
                "   \t-id ID\n" +
                "delete day\n" + 
                "   \t-id ID\n" +
                "delete timeslot\n" + 
                "   \t-id ID\n" +
                "connect day-with-conference\n" +
                "   \t-day  DAY_ID\n" +
                "   \t-conference CONFERENCE_ID\n" +
                "connect talk-with-timeslot\n" +
                "   \t-talk  TALK_ID\n" +
                "   \t-timeslot TIMESLOT_ID\n" +
                "connect timeslot-with-day\n" +
                "   \t-timeslot  TIMESLOT_ID\n" +
                "   \t-day DAY_ID\n" +
                "show schedule\n" +
                "   \t-id CONFERENCE_ID\n" +
                "\n\n" + 
                "You may also refer to the documentation for further information." +
                "\n\n" 
        );
    }
}
