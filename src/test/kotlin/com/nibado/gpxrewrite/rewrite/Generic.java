package com.nibado.gpxrewrite.rewrite;

    public class Generic<T extends Number> {
        public String toString(T number) {
            return "the number is " + number;
        }

    public static void main(String... argv) {
        Generic<Integer> genInt = new Generic<>();
        Generic<Double> genDouble = new Generic<>();

        System.out.println(genInt.toString(100));
        System.out.println(genDouble.toString(0.0));
    }
    }
