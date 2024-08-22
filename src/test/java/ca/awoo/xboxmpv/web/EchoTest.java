package ca.awoo.xboxmpv.web;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import ca.awoo.jabert.FastJsonFormat;
import ca.awoo.jabert.SValue;

public class EchoTest {
    FastJsonFormat format = new FastJsonFormat("UTF-8");
    WebServer server;

    public EchoTest() throws IOException{
        server = new WebServer();
        server.setHandler(new ViewMiddleware(new EchoHandler()));
        server.start();
    }
    
    private void testRequest(SValue value) throws Exception{
        URL url = new URL("http://localhost:8080");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        format.emit(value, connection.getOutputStream());
        connection.getOutputStream().close();
        SValue echo = format.parse(connection.getInputStream());
        assertEquals("Get echo back intact", value, echo);
    }

    @Test
    public void stringEcho() throws Exception{
        testRequest(new SValue.SString("Hello"));
    }
}
