import dev.aubique.jcalc.bot.BotHandler;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotApi = null;
        try {
            telegramBotApi = new TelegramBotsApi("https://6537d7ed.eu.ngrok.io", "https://localhost:8443");
            telegramBotApi.registerBot(BotHandler.getInstance());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
