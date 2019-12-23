package dev.aubique.jcalc.core;

interface Parsable {
    void parseExpression(String expression);

    String computeExpression(String expression);
}
