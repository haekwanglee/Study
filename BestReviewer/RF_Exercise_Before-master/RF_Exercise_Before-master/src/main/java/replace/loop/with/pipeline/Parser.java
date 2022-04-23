package replace.loop.with.pipeline;

import java.util.*;

public class Parser {

    /*
    office, country, telephone
    Chicago, USA, +1 312 373 1000
    Beijing, China, +86 4008 900 505
    Bangalore, India, +91 80 4064 9570
    Porto Alegre, Brazil, +55 51 3079 3550
    Chennai, India, +91 44 6604 4766
     */
    public List<OfficeInfo> parse(String input){
        String[] lines = input.split("\n");

        boolean firstLine = true;
        List<OfficeInfo> result = new ArrayList();
        for(String line : lines){
            if(firstLine){
                firstLine = false;
                continue;
            }

            if(line.trim().isEmpty()) continue;
            String[] record = line.split(",");

            if("India".equals(record[1].trim())){
                result.add(new OfficeInfo(record[0].trim(), record[2].trim()));
            }
        }
        return result;
    }
}
