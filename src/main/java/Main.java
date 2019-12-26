import dev.aubique.jcalc.bot.BotHandler;
import dev.aubique.jcalc.util.ConfigLoader;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotApi = null;
        String externalUrl = ConfigLoader.getExternalUrl();
        String internalUrl = ConfigLoader.getInternalUrl();
        try {
            telegramBotApi = new TelegramBotsApi(externalUrl, internalUrl);
            telegramBotApi.registerBot(BotHandler.getInstance());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

}
