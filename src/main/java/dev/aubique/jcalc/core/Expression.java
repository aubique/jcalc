package dev.aubique.jcalc.core;

public class Expression {

    public String rawExpression;
    private String parsedExpression;
    private Integer numberOne, numberTwo, result;
    private Character operator;

    Expression() {
        this.numberOne = this.numberTwo = 0;
        this.operator = null;
    }

    Expression(Integer numberOne, Integer numberTwo, Character operator) {
        setNumbers(numberOne, numberTwo, operator);
    }

    void setNumbers(Integer numberOne, Integer numberTwo, Character operator) {
        setNumbers(numberOne, numberTwo, operator, 0);
    }

    void setNumbers(Integer numberOne, Integer numberTwo, Character operator, Integer result) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
        this.operator = operator;
        this.result = result;
    }

    public String getParsedExpression() {
        return parsedExpression;
    }

    public void setParsedExpression(String parsedExpression) {
        this.parsedExpression = parsedExpression;
    }

    public Integer getNumberOne() {
        return numberOne;
    }

    public Integer getNumberTwo() {
        return numberTwo;
    }

    public Character getOperator() {
        return operator;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(int calculationResult) {
        this.result = calculationResult;
    }

    @Override
    public String toString() {
        return String.format(
                "Result: %d %c %d = %d",
                this.numberOne,
                this.operator,
                this.numberTwo,
                this.result
        );
    }
}
