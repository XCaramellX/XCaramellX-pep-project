package Service;
import Model.Account;
import java.util.List;
import DAO.AccountDAO;
import DAO.AccountDAOImpli;

public class AccountService {

    AccountDAO accountDAO;


    public AccountService(){
        accountDAO = new AccountDAOImpli();
    }

    public List<Account> getAllAccounts(){
        return accountDAO.getAccounts();
    }

    public Account getAccountByUser(Account account){
        return accountDAO.getAccountByUser(account);
    }

    public Account addAccount(Account account){
        return accountDAO.addAccount(account);
    }

}
