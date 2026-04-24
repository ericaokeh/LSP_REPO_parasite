package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    // Normal case
    @Test
    void testAverageNormal() {
        assertEquals(80.0, calculator.average(70, 80, 90));
    }

    @Test
    void testLetterGradeNormal() {
        assertEquals("B", calculator.letterGrade(85.0));
    }

    @Test
    void testIsPassingNormal() {
        assertTrue(calculator.isPassing(75.0));
    }

    // Boundary cases
    @Test
    void testBoundaryA() {
        assertEquals("A", calculator.letterGrade(90.0));
    }

    @Test
    void testBoundaryB() {
        assertEquals("B", calculator.letterGrade(80.0));
    }

    @Test
    void testBoundaryC() {
        assertEquals("C", calculator.letterGrade(70.0));
    }

    @Test
    void testBoundaryD() {
        assertEquals("D", calculator.letterGrade(60.0));
    }

    @Test
    void testBoundaryF() {
        assertEquals("F", calculator.letterGrade(59.9));
    }

    @Test
    void testPassingBoundary() {
        assertTrue(calculator.isPassing(60.0));
        assertFalse(calculator.isPassing(59.9));
    }

    // Edge cases
    @Test
    void testAllZeroScores() {
        assertEquals(0.0, calculator.average(0, 0, 0));
    }

    @Test
    void testAllPerfectScores() {
        assertEquals(100.0, calculator.average(100, 100, 100));
    }

    // Exception cases
    @Test
    void testNegativeScoreThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.average(-1, 50, 60);
        });
    }

    @Test
    void testScoreAbove100ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.average(50, 101, 60);
        });
    }

    // Integration test
    @Test
    void testFullFlow() {
        double avg = calculator.average(80, 85, 90);
        assertEquals("B", calculator.letterGrade(avg));
        assertTrue(calculator.isPassing(avg));
    }
}