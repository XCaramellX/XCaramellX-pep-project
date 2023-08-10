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

    public Account getAccountById(int account_id){
        return accountDAO.getAccountById(account_id);
    }

    public Account addAccount(Account account){
        if(account.getUsername() != null && account.getUsername().length() <= 4 && account != accountDAO.getAccountById(account.getAccount_id())){
           return accountDAO.addAccount(account);
        }

        return null;
    }

    public Account updateAccount(int account_id, Account account){
        if(account.getUsername() != null && account.getUsername().length() <= 4){
            accountDAO.updateAccount(account_id, account);
            return accountDAO.getAccountById(account_id);
        }

        return null;
    }

    public Account deleteAccount(int account_id){

        if(accountDAO.getAccountById(account_id) == null){
            return null;
        }
        
        return accountDAO.deleteAccount(account_id);
    }

}
