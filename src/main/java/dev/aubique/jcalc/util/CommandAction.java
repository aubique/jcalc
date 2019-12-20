package dev.aubique.jcalc.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandAction {

    private static final Map<Character, Commandable> ACTIONS;
    private static final Map<Character, Commandable> actions;
    private static Commandable add, substract, multiply, divide;

    static {
        actions = new HashMap<>();
        add = Integer::sum;
        substract = (a, b) -> a - b;
        multiply = (a, b) -> a * b;
        divide = (a, b) -> {
            if (b == 0)
                throw new IllegalArgumentException("Divided by zero");
            return a / b;
        };

        actions.put('+', add);
        actions.put('-', substract);
        actions.put('*', multiply);
        actions.put('/', divide);

        ACTIONS = Collections.unmodifiableMap(actions);
    }

    public static Integer commandCalculate(
            Character operator, Integer numOne, Integer numTwo) {
        Commandable command = CommandAction.ACTIONS.get(operator);
        return command.evaluate(numOne, numTwo);
    }
}
