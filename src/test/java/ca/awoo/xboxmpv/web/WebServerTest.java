package ca.awoo.xboxmpv.web;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import ca.awoo.xboxmpv.web.exceptions.InternalServerException;

public class WebServerTest {
    @Test
    public void basicWebServerTest() throws Exception{
        WebServer server = new WebServer();
        server.setHandler((context) -> {
            String response = "Hello world";
            context.addResponseHeader("Content-Type", "text/plain; charset=utf-8");
            //context.setResponseHeader("Content-Length", "" + response.getBytes().length);
            try {
                context.sendHeaders(200);
                context.getResponseBody().write(response.getBytes());
            } catch (IOException e) {
                throw new InternalServerException(e);
            }
        });
        server.start();
        URL url = new URL("http://localhost:8080");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setUseCaches(false);
        InputStream response = connection.getInputStream();
        //int length = Integer.parseInt(connection.getHeaderField("Content-Length"));
        int length = "Hello world".length();
        byte[] bytes = new byte[length];
        response.read(bytes);
        String responseString = new String(bytes);
        assertEquals("Got what we expected", "Hello world", responseString);
    }
}
