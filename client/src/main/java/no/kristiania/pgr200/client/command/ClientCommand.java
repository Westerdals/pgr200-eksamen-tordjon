package no.kristiania.pgr200.client.command;

import com.google.gson.Gson;
import no.kristiania.pgr200.core.http.HttpResponse;

import java.util.HashMap;

public interface ClientCommand {
    HashMap<String, String> parameters = new HashMap<>();
    Gson gson = new Gson();


    default boolean checkForError(HttpResponse response) {

        if (response.getStatusCode() == 500) {
            System.out.println("Internal server error");
            return true;
        } else if (response.getStatusCode() == 400) {
            System.out.println("Your input was not as expected. Use \"help\"-command to get more help.");
            System.out.println(response.getBody());
            return true;
        } else if (response.getStatusCode() == 404) {
            System.out.println("The resource you were looking for could not be found. Use \"help\"-command to get more help.");
        }

        return false;

    }
}
