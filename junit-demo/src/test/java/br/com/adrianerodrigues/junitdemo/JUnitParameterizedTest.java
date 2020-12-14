package br.com.adrianerodrigues.junitdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.temporal.ChronoUnit;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class JUnitParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = {"quadrado", "triangulo", "losango"})
    void polygonTest(String polygonName) {
        log.info("M=polygonTest, name={}", polygonName);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullSourceTest(String value) {
        log.info("M=nullSourceTest, value={}", value);
        assertTrue(value == null || value.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(names = {"DAYS", "YEARS"})
    void enumSourceTest(ChronoUnit unit) {
        log.info("M=enumSourceTest, unit={}", unit.name());
        assertTrue(EnumSet.of(ChronoUnit.YEARS, ChronoUnit.DAYS).contains(unit));
    }

    @ParameterizedTest
    @CsvSource({"banana", "maçã", "'laranja, limão"})
    void csvSourceTest(String fruit) {
        log.info("M=csvSourceTest, fruit={}", fruit);
        assertNotNull(fruit);
    }
}
