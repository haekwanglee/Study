package com.sec.bestreviewer.test;

import com.sec.bestreviewer.store.Employee;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CommandGenerator {

    private final Random random = new Random();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    private final List<String> empNumberYearPrefix = Stream.concat(
            IntStream.rangeClosed(90, 99).mapToObj(Integer::toString),
            IntStream.rangeClosed(0, 19).mapToObj(it -> String.format("%02d", it)))
            .collect(Collectors.toList());

    private final List<String> careerLevels = Arrays.asList("CL1", "CL2", "CL3", "CL4");

    private final List<String> certi = Arrays.asList("ADV", "PRO", "EX");

    public List<Employee> generateEmployees(int count) {
        return IntStream.range(0, count).boxed()
                .map(it -> new Employee.Builder())
                .peek(it -> it.setEmployeeNumber(getEmployeeNumber()))
                .peek(it -> it.setName(getName()))
                .peek(it -> it.setCareerLevel(chooseOne(careerLevels)))
                .peek(it -> it.setPhoneNumber(getPhoneNumber()))
                .peek(it -> it.setBirthday(getBirthday()))
                .peek(it -> it.setCerti(chooseOne(certi)))
                .map(Employee.Builder::build)
                .distinct()
                .sorted(Comparator.comparing(Employee::getEmployeeNumberDateFormatYyyy))
                .collect(Collectors.toList());
    }

    private String getEmployeeNumber() {
        return chooseOne(empNumberYearPrefix) + String.format("%06d", random.nextInt(999999));
    }

    private String getName() {
        StringBuilder name = new StringBuilder();
        IntStream.range(0, 5).forEach(it -> name.append((char) (random.nextInt(26) + 'A')));
        name.append(" ");
        IntStream.range(0, 3).forEach(it -> name.append((char) (random.nextInt(26) + 'A')));
        return name.toString();
    }

    private String getPhoneNumber() {
        return "010-" + String.format("%04d", random.nextInt(9999)) + "-" + String.format("%04d", random.nextInt(9999));
    }

    public String getBirthday() {
        long now = new Date().getTime();
        Date years50Ago = new Date(now - (TimeUnit.DAYS.toMillis(1) * 365 * 50));
        Date years20Ago = new Date(now - (TimeUnit.DAYS.toMillis(1) * 365 * 20));

        long startMillis = years50Ago.getTime();
        long endMillis = years20Ago.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
        return dateFormat.format(new Date(randomMillisSinceEpoch));
    }

    private String chooseOne(List<String> items) {
        return items.get(random.nextInt(items.size()));
    }
}
