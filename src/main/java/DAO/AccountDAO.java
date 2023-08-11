package DAO;
import Model.Account;
import java.util.List;

public interface AccountDAO {
    List<Account> getAccounts();
    Account addAccountByUserPass(Account account);
    
    Account addAccount(Account account);

    
}
