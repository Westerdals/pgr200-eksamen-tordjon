package no.kristiania.pgr200.core.http;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatusTest {
    @Test
    public void testThatCodesGiveCorrectStatusString() {

        HashMap<Integer, String> pairs = new HashMap<>();
        pairs.put(200, "OK");
        pairs.put(201, "UPDATED");
        pairs.put(204, "NO CONTENT");
        pairs.put(404, "NOT FOUND");
        pairs.put(500, "Internal Server Error");

        for(Map.Entry entry : pairs.entrySet()) {
            Status status = new Status((int) entry.getKey());
            assertThat(status.toString())
                    .isEqualTo(entry.getKey() + " " + entry.getValue());
        }
    }


}