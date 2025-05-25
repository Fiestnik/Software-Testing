package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TriangleTest {

    @Nested
    @DisplayName("Range‐check violations")
    class RangeChecks {
        @Test void aTooSmall() {
            assertEquals(
                "Value of a is not in the range of permitted values.",
                Triangle.classify(0, 50, 50)
            );
        }
        @Test void aTooLarge() {
            assertEquals(
                "Value of a is not in the range of permitted values.",
                Triangle.classify(201, 50, 50)
            );
        }
        @Test void bTooSmall() {
            assertEquals(
                "Value of b is not in the range of permitted values.",
                Triangle.classify(50, 0, 50)
            );
        }
        @Test void bTooLarge() {
            assertEquals(
                "Value of b is not in the range of permitted values.",
                Triangle.classify(50, 201, 50)
            );
        }
        @Test void cTooSmall() {
            assertEquals(
                "Value of c is not in the range of permitted values.",
                Triangle.classify(50, 50, 0)
            );
        }
        @Test void cTooLarge() {
            assertEquals(
                "Value of c is not in the range of permitted values.",
                Triangle.classify(50, 50, 201)
            );
        }
    }

    @Nested
    @DisplayName("Triangle‐inequality failures")
    class NotTriangles {
        @Test void degenerateCase_equalSum() {
            assertEquals("NotATriangle", Triangle.classify(1, 2, 3));
        }
        @Test void oneSideTooLong() {
            assertEquals("NotATriangle", Triangle.classify(1, 1, 200));
        }
    }

    @Nested
    @DisplayName("Valid triangles")
    class ValidTriangles {
        @Test void equilateralMin() {
            assertEquals("Equilateral", Triangle.classify(1, 1, 1));
        }
        @Test void equilateralMax() {
            assertEquals("Equilateral", Triangle.classify(200, 200, 200));
        }
        @Test void isoscelesExample() {
            assertEquals("Isosceles", Triangle.classify(5, 5, 8));
        }
        @Test void scaleneExample() {
            assertEquals("Scalene", Triangle.classify(2, 3, 4));
        }
    }
}