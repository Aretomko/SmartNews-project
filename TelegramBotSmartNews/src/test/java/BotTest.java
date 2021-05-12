import jdk.jfr.Threshold;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BotTest {
    @Test
    public void processMessage_NewUsers_UserAddedToDatabase(){
        Bot bot = new Bot();
        bot.processMessage(new Update());

    }
    @Test
    public void getChats_ListOfCaratsReceivedFromDB(){

    }
}
