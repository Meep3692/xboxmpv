package ca.awoo.xboxmpv;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

public class CombinatorTest {
    @Test
    public void compinatorTest(){
        Combinator combinator = new Combinator().optional("lib").fixed("mpv-").or("1", "2").or(".dll", ".so", "");
        List<String> options = combinator.explode();
        String[] expected = new String[]{
            "libmpv-1.dll",
            "libmpv-1.so",
            "libmpv-1",
            "libmpv-2.dll",
            "libmpv-2.so",
            "libmpv-2",
            "mpv-1.dll",
            "mpv-1.so",
            "mpv-1",
            "mpv-2.dll",
            "mpv-2.so",
            "mpv-2",
        };
        assertArrayEquals(expected, options.toArray());
    }
}
