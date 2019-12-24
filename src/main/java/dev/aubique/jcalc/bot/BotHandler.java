package dev.aubique.jcalc.bot;

import dev.aubique.jcalc.core.Parser;
import dev.aubique.jcalc.util.ConfigLoader;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BotHandler extends TelegramWebhookBot {

    private static BotHandler instance = null;
    private Parser model;

    private BotHandler() {
        model = new Parser();
    }

    public static BotHandler getInstance() {
        if (instance == null) {
            instance = new BotHandler();
        }
        return instance;
    }

    public synchronized BotApiMethod sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        return sendMessage;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        System.out.println("Update method triggered");
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = model.computeExpression(update.getMessage().getText());
            return sendMsg(update.getMessage().getChatId().toString(), message);
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return ConfigLoader.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return ConfigLoader.getBotToken();
    }

    @Override
    public String getBotPath() {
        return ConfigLoader.getBotUsername();
    }
}
