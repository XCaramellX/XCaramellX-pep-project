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

    public Account getAccountById(int account_id){
        return accountDAO.getAccountById(account_id);
    }

    public Account addAccount(Account account){
        return accountDAO.addAccount(account);
    }

    public Account updateAccount(int account_id, Account account){
        if(account == null){
            return null;
        }
        
        accountDAO.updateAccount(account_id, account);
        return accountDAO.getAccountById(account_id);
    }

    public Account deleteAccount(int account_id){

        if(accountDAO.getAccountById(account_id) == null){
            return null;
        }
        
        return accountDAO.deleteAccount(account_id);
    }

}
