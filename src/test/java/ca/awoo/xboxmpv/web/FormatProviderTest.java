package ca.awoo.xboxmpv.web;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import ca.awoo.jabert.FastJsonFormat;
import ca.awoo.jabert.Format;

public class FormatProviderTest {
    @Test
    public void jsonFormatProviderTest() throws Exception{
        JsonFormatProvider provider = new JsonFormatProvider();
        Optional<Format> format = provider.getFormat(new ContentType("application/json"));
        assertTrue("Got a format", format.isPresent());
        assertTrue("Got a FastJsonFormat", format.get() instanceof FastJsonFormat);
    }

    @Test
    public void multiFormatProviderTest() throws Exception{
        JsonFormatProvider jsonProvider = new JsonFormatProvider();
        MultipleFormatProvider multiProvider = new MultipleFormatProvider();
        multiProvider.addFormat(jsonProvider);
        Optional<Format> format = multiProvider.getFormat(new ContentType("application/json"));
        assertTrue("Got a format", format.isPresent());
        assertTrue("Got a FastJsonFormat", format.get() instanceof FastJsonFormat);
    }

    @Test
    public void webServerFormatProviderTest() throws Exception{
        WebServer server = new WebServer();
        Optional<Format> format = server.getFormat(new ContentType("application/json"));
        assertTrue("Got a format", format.isPresent());
        assertTrue("Got a FastJsonFormat", format.get() instanceof FastJsonFormat);
    }
}
