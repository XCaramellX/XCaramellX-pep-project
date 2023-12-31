package DAO;
import Model.Account;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


import java.util.ArrayList;
import Util.ConnectionUtil;

public class AccountDAOImpli implements AccountDAO{
   private Connection myConnection = null;

    public List<Account> getAccounts(){
        List<Account> allAccounts = new ArrayList<>();
        
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
            try{   
           
             String stmt = "SELECT * FROM account";
             PreparedStatement  select = myConnection.prepareStatement(stmt);
             ResultSet sResultSet = select.executeQuery();

             while(sResultSet.next()){
                    
                    int id = sResultSet.getInt(1);
                    String user = sResultSet.getString(2);
                    String pass = sResultSet.getString(3);

                    Account newAccount = new Account(id, user, pass);

                    
                    allAccounts.add(newAccount);
             }

            
        }catch(SQLException e){
            e.printStackTrace();
            }
        
            
        System.out.println(allAccounts);
        return allAccounts;
    };
    
    public Account getAccountByUser(Account account){
        
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
            try{   
    
             String compareStmt = "SELECT * FROM account WHERE username = ?";

             PreparedStatement userStmt = myConnection.prepareStatement(compareStmt);

             userStmt.setString(1, account.getUsername());
             
             ResultSet userSet = userStmt.executeQuery();

                    if(userSet.next()) 
                    {
                        if(account.getPassword().equals(userSet.getString(3))){

                            int id = userSet.getInt(1);
                            String user = userSet.getString(2);
                            String pass = userSet.getString(3);

                            Account newAccount = new Account(id, user, pass);
                    
                            return newAccount;
                        }
                        
                    }
            
        }catch(SQLException e){
            e.printStackTrace();
            }
        
        return null;
    };


    public Account addAccount(Account account){
        
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
           
            String stmt = "INSERT INTO account(username, password) VALUES(?, ?)";
            PreparedStatement insertStmt = myConnection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1, account.getUsername());
            insertStmt.setString(2, account.getPassword());
            
            insertStmt.executeUpdate();
            ResultSet addResultSet = insertStmt.getGeneratedKeys();

            while(addResultSet.next()){
                if(!account.getUsername().isBlank() && account.getUsername().length() <= 4){
                    int generatedKey = (int)addResultSet.getInt(1);
                    String user = account.getUsername();
                    String pass = account.getPassword();

                    Account newAccount = new Account(generatedKey, user, pass);
                    return newAccount;
                } 
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    
       return null;
    };
    
}
