package DAO;
import Model.Account;
import java.util.List;

public interface AccountDAO {
    List<Account> getAccounts();
    Account getAccountByUser(Account account);
    
    Account addAccount(Account account);

    
}
