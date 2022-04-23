import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.HashMap;
import java.util.Map;

class InjectTestReporterTest{

    @Test
    void reportSingleValue(TestReporter testReporter){
        testReporter.publishEntry("a status message");
    }

    @Test
    void reportKeyValuePair(TestReporter testReporter){
        testReporter.publishEntry("a key","a value");
    }


    @Test
    void reportMultipleKeyValuePair(TestReporter testReporter){
        Map<String, String> values = new HashMap<>();
        values.put("user name", "dk38");
        values.put("award year", "1974");
        testReporter.publishEntry(values);
    }

}
