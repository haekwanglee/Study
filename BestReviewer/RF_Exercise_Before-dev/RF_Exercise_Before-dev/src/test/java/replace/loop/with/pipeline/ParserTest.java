package replace.loop.with.pipeline;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    public void testParse(){
        Parser parser = new Parser();
        String input =
                "office, country, telephone\n" +
                        "Chicago, USA, +1 312 373 1000\n" +
                        "Beijing, China, +86 4008 900 505\n" +
                        "Bangalore, India, +91 80 4064 9570\n" +
                        "Porto Alegre, Brazil, +55 51 3079 3550\n" +
                        "Chennai, India, +91 44 6604 4766";

        List<OfficeInfo> expected = Arrays.asList(new OfficeInfo("Bangalore","+91 80 4064 9570"),
                new OfficeInfo("Chennai","+91 44 6604 4766"));

//        assertArrayEquals(expected.toArray(), parser.parse(input).toArray());
        List<OfficeInfo> actual = parser.parse(input);
        assertAll(
                ()-> assertEquals( expected.size(), actual.size()),
                ()->assertEquals(expected.get(0).city, actual.get(0).city ),
                ()->assertEquals(expected.get(0).phoneNumber, actual.get(0).phoneNumber ),
                ()->assertEquals(expected.get(1).city, actual.get(1).city ),
                ()->assertEquals(expected.get(1).phoneNumber, actual.get(1).phoneNumber )
                );

    }

    @Test
    public void testParse_firstRowContainsIndia(){
        Parser parser = new Parser();
        String input =
                "office, India, telephone\n" +
                        "Chicago, USA, +1 312 373 1000\n" +
                        "Beijing, China, +86 4008 900 505\n" +
                        "Bangalore, India, +91 80 4064 9570\n" +
                        "Porto Alegre, Brazil, +55 51 3079 3550\n" +
                        "Chennai, India, +91 44 6604 4766";

        List<OfficeInfo> expected = Arrays.asList(new OfficeInfo("Bangalore","+91 80 4064 9570"),
                new OfficeInfo("Chennai","+91 44 6604 4766"));
//        assertArrayEquals(expected.toArray(), parser.parse(input).toArray());
        List<OfficeInfo> actual = parser.parse(input);
        assertAll(
                ()-> assertEquals( expected.size(), actual.size()),
                ()->assertEquals(expected.get(0).city, actual.get(0).city ),
                ()->assertEquals(expected.get(0).phoneNumber, actual.get(0).phoneNumber ),
                ()->assertEquals(expected.get(1).city, actual.get(1).city ),
                ()->assertEquals(expected.get(1).phoneNumber, actual.get(1).phoneNumber )
        );

    }


}