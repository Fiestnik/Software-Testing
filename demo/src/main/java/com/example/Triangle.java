package com.example;

public class Triangle {

    public static String classify(int a, int b, int c) {
        // c1–c3: range checks
        if (a < 1 || a > 200) {
            return "Value of a is not in the range of permitted values.";
        }
        if (b < 1 || b > 200) {
            return "Value of b is not in the range of permitted values.";
        }
        if (c < 1 || c > 200) {
            return "Value of c is not in the range of permitted values.";
        }

        // c4–c6: triangle inequality (strict)
        if (a >= b + c || b >= a + c || c >= a + b) {
            return "NotATriangle";
        }

        // classification
        if (a == b && b == c) {
            return "Equilateral";
        }
        if (a == b || a == c || b == c) {
            return "Isosceles";
        }
        return "Scalene";
    }
}