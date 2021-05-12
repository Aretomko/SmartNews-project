import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        ApiContextInitializer.init();

        TelegramBotsApi api = new TelegramBotsApi();


        Bot bot = new Bot();
        try{
            api.registerBot(bot);
            System.out.println("registered");
        }catch (TelegramApiException ex){
            ex.printStackTrace();
        }

        Timer timer = new Timer();
        timer.schedule(new UpdateTask(bot), 0, 5000);

    }
    private static Set<String> getChats() throws IOException {
        Set<String> chats = new HashSet<>();
        Path path = Paths.get("C:\\Users\\ArtemCodeMachine\\Desktop\\users.txt");
        chats.addAll(Arrays.asList(Files.readAllLines(path, Charset.defaultCharset()).get(0).split("/")));
        return chats;
    }
    static class UpdateTask extends TimerTask {

        private Bot bot;

        public  UpdateTask(Bot bot){
            this.bot = bot;
        }

        @Override
        public void run() {
            Path path = Paths.get("C:\\Users\\ArtemCodeMachine\\Desktop\\updates.txt");
            List<String> updates = new ArrayList<>();
            try {
                List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
                if(lines.size()==1) {
                    updates = Arrays.asList(lines.get(0).split("/"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<String> chats = null;
            try {
                chats = getChats();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(updates.size()>0) {
                for (String message : updates) {
                    for (String chatId : chats) {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(chatId);
                        sendMessage.setText(message);
                        try {
                            bot.execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            try {
                Files.delete(path);
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
