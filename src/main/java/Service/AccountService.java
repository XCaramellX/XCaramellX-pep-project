package Service;
import Model.Account;
import java.util.List;
import DAO.AccountDAO;
import DAO.AccountDAOImpli;

public class AccountService {
    private AccountDAO accountDAO;


    public AccountService(){
        accountDAO = new AccountDAOImpli();
    }

    public List<Account> getAllAccounts(){
        return accountDAO.getAccounts();
    }

    public void addAccount(Account account){
        if(account.getUsername() != null && account.getUsername().length() <= 4){

        }
    }

}
