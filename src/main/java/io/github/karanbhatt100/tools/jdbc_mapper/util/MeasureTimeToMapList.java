package io.github.karanbhatt100.tools.jdbc_mapper.util;

import io.github.karanbhatt100.tools.jdbc_mapper.ClassMapper;
import io.github.karanbhatt100.tools.jdbc_mapper.exception.MeasureTimeFails;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class MeasureTimeToMapList {

    private MeasureTimeToMapList() {
    }

    public static <T> void measureExecutionTime(Class<T> classType, int size, String filePath, int totalCycle) {
        IntStream.range(0, totalCycle).forEach(i -> {
            try {
                log.info("Running cycle {}", i);
                Files.createDirectories(Paths.get(filePath));
                File file = new File(filePath + File.separator + "RunCycle_" + i + ".csv");
                String output = measureExecutionTime(classType, size);
                PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
                writer.println(output);
                writer.close();
                log.info("Cycle {} completed", i);
            } catch (Exception e) {
                throw new MeasureTimeFails(e);
            }
        });
    }


    public static <T> void measureExecutionTime(Class<T> classType, int size, String filePath) {
        try {
            Files.createDirectories(Paths.get(filePath));
            File file = new File(filePath + File.separator + "RunCycle_0.csv");
            String output = measureExecutionTime(classType, size);
            PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
            writer.println(output);
            writer.close();
        } catch (Exception e) {
            throw new MeasureTimeFails(e);
        }
    }

    public static <T> String measureExecutionTime(Class<T> classType, int size) {

        StringBuilder text = new StringBuilder();
        text.append("listSize,timeInNano,timeInMilli,timeInSecond");
        generateRange(size).forEach(i -> {
            List<T> result = Instancio.ofList(classType).size(size).create();
            long time = executeToMap(result);

            text.append("\n").append(i).append(", ")
                    .append(time).append(",")
                    .append(time / 1000000).append(",")
                    .append(time / 1000000000);
        });

        return text.toString();
    }

    private static <T> long executeToMap(List<T> elementList) {
        long start = System.nanoTime();
        List<Map<String, Object>> valueList = ClassMapper.toMap(elementList);
        long end = System.nanoTime();
        assert (elementList.size() == valueList.size());

        return (end - start);
    }

    private static List<Integer> generateRange(int endTo) {
        return Stream.iterate(1, x -> x <= endTo, x -> x * 2).toList();
    }
}
