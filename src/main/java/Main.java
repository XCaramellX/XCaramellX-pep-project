import Controller.SocialMediaController;
import DAO.AccountDAOImpli;
import DAO.MessageDAO;
import DAO.MessageDAOImpli;
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

      /*  Account myAccount = new Account( "m", "kere");
      Account fixedAccount = new Account(2, "p", "n");

        AccountDAOImpli account = new AccountDAOImpli();

        account.addAccount(myAccount);
        account.getAccounts();
        account.updateAccount(fixedAccount);
        account.getAccounts();

       account.deleteAccount(fixedAccount); */

       Message myMessage = new Message(2, 2, "lp", 890);

       MessageDAO messages = new MessageDAOImpli();

       messages.getMessageById(1);

       
       
    }
}
