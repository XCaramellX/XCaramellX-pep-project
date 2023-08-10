package DAO;
import Model.Account;
import java.util.List;

public interface AccountDAO {
    List<Account> getAccounts();
    Account getAccountById(int account_id);
    
    Account addAccount(Account account);
    void updateAccount(int id, Account account);
    Account deleteAccount(int account_id);
    
}
