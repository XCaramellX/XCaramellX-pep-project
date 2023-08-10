import Controller.SocialMediaController;
import DAO.AccountDAO;
import DAO.AccountDAOImpli;
import DAO.MessageDAO;
import DAO.MessageDAOImpli;
import Service.MessageService;
import Model.Account;
import Model.Message;
import io.javalin.Javalin;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);

        Account myAccount = new Account( "m", "kere");
        Account fixedAccount = new Account( "p", "n");

        AccountDAO account = new AccountDAOImpli();

       account.addAccount(myAccount);
       account.getAccounts();
       account.updateAccount(2, fixedAccount);
       account.getAccounts();
      
      account.deleteAccount(2); 
      account.getAccounts(); 
     
       Message myMessage = new Message(2, 2, "lp", 890);

       MessageDAO messages = new MessageDAOImpli();

      /* messages.getMessageById(1); 
       messages.addMessage(myMessage);
       messages.getMessages();
       messages.getMessageById(2); 
       messages.deleteMessageById(2);
       messages.getMessages(); */

      /* MessageService myMessageService = new MessageService();
        myMessageService.addMessage(2, myMessage);
       myMessageService.getAllMessages();

       */

       
       
    }
}
