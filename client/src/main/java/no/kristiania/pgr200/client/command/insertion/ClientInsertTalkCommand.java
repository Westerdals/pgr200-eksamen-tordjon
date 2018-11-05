package no.kristiania.pgr200.client.command.insertion;

import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.insertion.InsertTalkCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ClientInsertTalkCommand extends InsertTalkCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        System.out.println("insert talk");

        Map<String, String> parameters = new HashMap<>();


        /*Field[] fields = this.getClass().getSuperclass().getDeclaredFields();
        for(Field f : fields){
            try {
                System.out.println(f.getName() + "..." + f.get(this));
                parameters.put(f.getName(), (String) f.get(this));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/


        System.out.println(parameters);
       // String uri = new Uri("/api/insert/talk", parameters) //Map<String,String>
       /* "/api/insert/talk" +
                "?title=" + title
                + "&description=" + description
                + "&topic=" + topic);*/



        HttpRequest req = new HttpRequest("localhost", 8080, null);
        try {
            HttpResponse response = req.execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //throw new NotImplementedException();
        return null;
    }
}