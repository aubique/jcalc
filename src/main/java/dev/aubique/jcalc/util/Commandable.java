package dev.aubique.jcalc.util;

@FunctionalInterface
public interface Commandable {
    Integer evaluate(Integer a, Integer b);
}
