package io.github.karanbhatt100.tools.jdbc_mapper;

import io.github.karanbhatt100.tools.jdbc_mapper.model.Result;
import io.github.karanbhatt100.tools.jdbc_mapper.util.MeasureTimeToMapList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class TimeMeasurementTest {

    @Test
    void shouldMeasureExecutionTime() {
        try {
            MeasureTimeToMapList.measureExecutionTime(Result.class, 100, "D:\\Projects\\jdbc_mapper\\log");
        } catch (Exception e) {
            System.out.println("Test failed because " + e.getMessage());
            fail();
        }
    }

    @Test
    void shouldMeasureExecutionTimeByCycle() {
        try {
            MeasureTimeToMapList.measureExecutionTime(Result.class, 100, "D:\\Projects\\jdbc_mapper\\cycleLog", 30);
        } catch (Exception e) {
            System.out.println("Test failed because " + e.getMessage());
            fail();
        }
    }

    @Test
    void shouldMeasureExecutionTimeAndGetStringOutput() {
        try {
            String output = MeasureTimeToMapList.measureExecutionTime(Result.class, 100);
            System.out.println(output);
        } catch (Exception e) {
            System.out.println("Test failed because " + e.getMessage());
            fail();
        }
    }
}
