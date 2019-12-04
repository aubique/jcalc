import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bot extends TelegramLongPollingBot {

    private static final String PATTERN = "(\\d+)\\s*([\\+\\-\\*\\/]{1})\\s*(\\d+)";
    private static Bot instance = null;

    private Expression expressionObj;
    private String expressionString, e;
    private Integer a, b;
    private Character o;
    private Pattern patternObj;
    private Matcher matcherObj;

    private Bot() {
        this.expressionObj = new Expression();
    }

    public static Bot getInstance() {
        if (instance == null) {
            instance = new Bot();
        }
        return instance;
    }

    void testFindGroups() {
        for (int i = 0; i < matcherObj.groupCount(); i++) {
            System.out.println(matcherObj.group(i + 1));
        }
    }

    private void parseExpression() {
        this.patternObj = Pattern.compile(PATTERN);
        this.matcherObj = patternObj.matcher(expressionString);

        if (matcherObj.find()) {
            this.e = matcherObj.group(0);
            this.a = Integer.parseInt(matcherObj.group(1));
            this.o = matcherObj.group(2).charAt(0);
            this.b = Integer.parseInt(matcherObj.group(3));
        }
    }

    public String computeExpression(String expression) {
        int res;

        this.expressionString = expression;
        parseExpression();
        res = CalculationCommand.commandCalculate(o, a, b);
        expressionObj.setNumbers(a, b, o, res);
        return expressionObj.toString();
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = computeExpression(update.getMessage().getText());
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    @Override
    public String getBotUsername() {
        return "Java Calculator";
    }

    @Override
    public String getBotToken() {
        return "693119206:AAFFhWzgR5IOoj-ssEsYN2iDIG6YJqifBrc";
    }

    static class CalculationCommand {

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
                return (Integer) a / b;
            };

            actions.put('+', add);
            actions.put('-', substract);
            actions.put('*', multiply);
            actions.put('/', divide);

            ACTIONS = Collections.unmodifiableMap(actions);
        }

        private static Integer commandCalculate(
                Character operator, Integer numOne, Integer numTwo) {
            Commandable command = ACTIONS.get(operator);
            return command.evaluate(numOne, numTwo);
        }
    }
}
