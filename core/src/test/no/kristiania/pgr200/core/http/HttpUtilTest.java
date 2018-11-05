package no.kristiania.pgr200.core.http;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class HttpUtilTest {

    private InputStream inputStream;

    public void queryServer(String path) throws IOException {
        Socket socket = new Socket("urlecho.appspot.com", 80);
        OutputStream output = socket.getOutputStream();
        // InputStream input = socket.getInputStream(); // FIXME : remove if not useful

        output.write(("GET " + path + " HTTP/1.1\r\n").getBytes());
        output.write(("Host: urlecho.appspot.com\r\n").getBytes());
        output.write("Connection: close\r\n".getBytes());
        output.write("\r\n".getBytes());

        inputStream = socket.getInputStream();
    }

    @Test
    public void shouldGetHeader() throws IOException {
        queryServer("http://urlecho.appspot.com/echo?X-Custom-Header=something");
        HttpUtil httpUtil = new HttpUtil(inputStream);

        String value = httpUtil.getHeader("X-Custom-Header");

        assertThat(value)
                .isEqualTo("something");


    }

    @Test
    public void shouldSeveralHeader() throws IOException {
        queryServer("http://urlecho.appspot.com/echo?Accept-Language=en&User-Agent=chrome");
        HttpUtil httpUtil = new HttpUtil(inputStream);

        String language = httpUtil.getHeader("Accept-Language");
        String userAgent = httpUtil.getHeader("User-Agent");

        assertThat(language)
                .isEqualTo("en");

        assertThat(userAgent)
                .isEqualTo("chrome");


    }


    @Test
    public void shouldGetBody() throws IOException {
        queryServer("http://urlecho.appspot.com/echo?&body=What%20a%20body!");
        HttpUtil httpUtil = new HttpUtil(inputStream);

        String body = "What a body!";
        assertThat(httpUtil.getBody())
                .isEqualTo(body);
    }

    @Test
    public void shouldGetAnotherBody() throws IOException {
        queryServer("http://urlecho.appspot.com/echo?body=This%20one%20is%20just%20as%20awesome.");
        HttpUtil httpUtil = new HttpUtil(inputStream);

        String body = "This one is just as awesome.";
        assertThat(httpUtil.getBody())
                .isEqualTo(body);
    }

    @Test
    public void shouldGiveStatusCode() throws IOException {
        queryServer("http://urlecho.appspot.com/echo?status=200");
        HttpUtil httpUtil = new HttpUtil(inputStream);

        assertThat(httpUtil.getStatusCode())
                .isEqualTo(200);
    }

    @Test
    public void shouldGiveOtherStatusCodes() throws IOException {
        Random random = new Random();

        int[] codes = {
                200, 201, 204, 301, 303, 401, 500
        };

        int code = codes[random.nextInt(codes.length)];


        queryServer("http://urlecho.appspot.com/echo?status=" + code);
        HttpUtil httpUtil = new HttpUtil(inputStream);

        assertThat(httpUtil.getStatusCode())
                .isEqualTo(code);
    }
}