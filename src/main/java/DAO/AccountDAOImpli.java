package DAO;
import Model.Account;

import java.sql.SQLException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


import java.util.ArrayList;
import Util.ConnectionUtil;

public class AccountDAOImpli implements AccountDAO{

    public List<Account> getAccount(){
        List<Account> allAccounts = new ArrayList<>();

        Connection myConnection = null;
        try{   
           
             myConnection = ConnectionUtil.getConnection();
             String stmt = "SELECT * FROM account";
             PreparedStatement  select = myConnection.prepareStatement(stmt);
             ResultSet sResultSet = select.executeQuery();

             while(sResultSet.next()){
                    Account newAccount = new Account();
                    int id = sResultSet.getInt(1);
                    String user = sResultSet.getString(2);
                    String pass = sResultSet.getString(3);

                    newAccount.setAccount_id(id);
                    newAccount.setUsername(user);
                    newAccount.setPassword(pass);
                    
                    allAccounts.add(newAccount);
             }

            
        }catch(SQLException e){
            e.printStackTrace();
            }
            
        System.out.println(allAccounts);
        return allAccounts;
    };
    
    public void addAccount(Account account){
            Connection myConnection = null;
        try{
            myConnection = ConnectionUtil.getConnection();
            String stmt = "INSERT INTO account(username, password) VALUES(?, ?)";
            PreparedStatement insertStmt = myConnection.prepareStatement(stmt);
            String insertSet = insertStmt.getResultSet().getString(2);

            if(account.getUsername() != "" && account.getPassword().length() >= 4 && 
            !account.getUsername().equals(insertSet)){
                
            insertStmt.setString(1, account.getUsername());
            insertStmt.setString(2, account.getPassword());
            }

            insertStmt.executeUpdate();

            System.out.println("Successfully added " + 1 + " account!");
        }catch(SQLException e){
            e.printStackTrace();
        }

    };
    public void updateAccount(Account account){

        Connection myConnection = null;
        try{
            myConnection = ConnectionUtil.getConnection();
            String stmt = "UPDATE account SET username = ?, password = ? WHERE account_id = ?";
            PreparedStatement updateStmt = myConnection.prepareStatement(stmt);

            updateStmt.setString(1, account.getUsername());
            updateStmt.setString(2, account.getPassword());
            updateStmt.setInt(3, account.getAccount_id());

            updateStmt.executeUpdate();

            System.out.println("Successfully updated " + 1 + " account!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    };
    public void deleteAccount(Account account){

    };
}
