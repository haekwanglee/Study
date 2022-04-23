package replace.loop.with.pipeline;

import java.util.*;
import java.util.stream.Collectors;

public class Parser {
    /*
    office, country, telephone
    Chicago, USA, +1 312 373 1000
    Beijing, China, +86 4008 900 505
    Bangalore, India, +91 80 4064 9570
    Porto Alegre, Brazil, +55 51 3079 3550
    Chennai, India, +91 44 6604 4766
     */

    public List<OfficeInfo> parse(String input) {

        return Arrays.asList(input.split("\n"))
                .stream()
                .skip(1)
                .filter(line -> !line.trim().isEmpty())
                .map(line -> line.split(","))
                .filter(record -> "India".equals(record[1].trim()))
                .map(record -> new OfficeInfo(record[0].trim(), record[2].trim()))
                .collect(Collectors.toList());

    }

//
//    public List<OfficeInfo> parse(String input){
//
//        String[] lines = input.split("\n");  // 초기 코드
//        List<String> streamlines = Arrays.asList(input.split("\n"));  // 첫 단계에서 수정된 코드
//        boolean firstLine = true;
//
//        stream 의 filter, map,.. 을 이용하여 리팩토링한 결과
//        List<String[]> streamlines = Arrays.asList(input.split("\n"))
//                .stream()
//                .skip(1)
//                .filter(line->!line.trim().isEmpty())
//                .map(line->line.split(","))
//                .filter(record->"India".equals(record[1].trim()) )
//                .collect(Collectors.toList());
//
//        stream 으로 대체하면서 최종적으로 불필요함.
//        List<OfficeInfo> result = new ArrayList();
//        for(String line : lines){
//        for(String line : streamlines){
//
//        map(record -> new OfficeInfo 로 대체
//        for(String[] record : streamlines){
//            stream.skip(1) 으로 대체됨.
//            if(firstLine){
//                firstLine = false;
//                continue;
//            }
//
//            filter(line->!line.trim().isEmpty() 로 대체
//            if(line.trim().isEmpty()) continue;
//
//            stream.map() 으로 대체
//            String[] record = line.split(",");
//
//            stream 의 filter( record->"India"...) 로 대체
//            if("India".equals(record[1].trim())){
//                result.add(new OfficeInfo(record[0].trim(), record[2].trim()));
//            }
//
//            map(record-> new OfficeInfo ...) 로 대체
//            result.add(new OfficeInfo(record[0].trim(), record[2].trim()));
//
//        }
//        stream 으로 변경한 결과 리턴으로 수정됨.
//        return result;
//    }
//

}
