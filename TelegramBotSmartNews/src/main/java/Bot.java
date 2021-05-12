import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Bot extends TelegramLongPollingBot {
    private final Set<String> chats = new HashSet<>();
    private boolean fetched = false;

    public String getBotUsername() {
        return  "SmartNewsBot";
    }

    public String getBotToken() {
        return "1712124889:AAEswbVUfjWhItQDmt74qHQ9nevaiYO8YYE";
    }

    public void onUpdateReceived(Update update){
        if(fetched) {
            processMessage(update);
        }else{
            try {
                this.getChats();
                this.fetched = true;
                processMessage(update);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getChats() throws IOException {
        Path path = Paths.get("C:\\Users\\ArtemCodeMachine\\Desktop\\users.txt");
        this.chats.addAll(Arrays.asList(Files.readAllLines(path, Charset.defaultCharset()).get(0).split("/")));
    }

    public void processMessage(Update update){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        Long chatId = update.getMessage().getChatId();
        if (update.hasMessage()) {
            //create and
            if (!this.chats.contains(String.valueOf(update.getMessage().getChatId()))) {
                try {
                    Path path = Paths.get("C:\\Users\\ArtemCodeMachine\\Desktop\\users.txt");
                    String s = String.valueOf(update.getMessage().getChatId());
                    Files.write(path, (s + "/").getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    message.setText("You are subscribed for updates");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    message.setText("IO error");
                }
            } else {
                message.setText("You are already subscribed for news");
            }
        } else {
            message.setText("Error");
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
