package DAO;
import Model.Account;
import java.util.List;

public interface AccountDAO {
    List<Account> getAccount();
    
    void addAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);
    
}
