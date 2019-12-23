package dev.aubique.jcalc.core;

import dev.aubique.jcalc.util.CommandAction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements Parsable {

    private static final String PATTERN = "(\\d+)\\s*([\\+\\-\\*\\/]{1})\\s*(\\d+)";

    private Expression expressionObj;
    private String expressionString;
    private Integer numberA, numberB;
    private Character operator;
    private Pattern patternObj;
    private Matcher matcherObj;

    public Parser() {
        this.expressionObj = new Expression();
    }

    void testFindGroups() {
        for (int i = 0; i < matcherObj.groupCount(); i++) {
            System.out.println(matcherObj.group(i + 1));
        }
    }

    @Override
    public void parseExpression(String expression) {
        this.patternObj = Pattern.compile(PATTERN);
        this.matcherObj = patternObj.matcher(expression);

        if (matcherObj.find()) {
            this.expressionString = matcherObj.group(0);
            this.numberA = Integer.parseInt(matcherObj.group(1));
            this.operator = matcherObj.group(2).charAt(0);
            this.numberB = Integer.parseInt(matcherObj.group(3));
        }
    }

    @Override
    public String computeExpression(String expression) {
        int result;

        parseExpression(expression);
        result = CommandAction.commandCalculate(operator, numberA, numberB);
        expressionObj.setNumbers(numberA, numberB, operator, result);
        return expressionObj.toString();
    }

}
