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
    
    public Account getAccountById(int account_id){
        
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
            try{   
           
             String stmt = "SELECT * FROM account WHERE account_id = ?";
             PreparedStatement  select = myConnection.prepareStatement(stmt);

             select.setInt(1, account_id);
             ResultSet sResultSet = select.executeQuery();

             while(sResultSet.next()){
                    
                    int id = sResultSet.getInt(1);
                    String user = sResultSet.getString(2);
                    String pass = sResultSet.getString(3);

                    Account newAccount = new Account(id, user, pass);

                    System.out.println(newAccount.toString());
                    
                    return newAccount;
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
            PreparedStatement insertStmt = myConnection.prepareStatement(stmt);
                
            insertStmt.setString(1, account.getUsername());
            insertStmt.setString(2, account.getPassword());
            

            insertStmt.executeUpdate();

            ResultSet addResultSet = insertStmt.getGeneratedKeys();
            

            while(addResultSet.next()){

                int generatedKey = (int)addResultSet.getInt(1);
                String user = addResultSet.getString(2);
                String pass = addResultSet.getString(3);

                Account newAccount = new Account(generatedKey, user, pass);

                return newAccount;
            }

            

        }catch(SQLException e){
            e.printStackTrace();
        }
    
       return null;
    };
    public void updateAccount(int id, Account account){

        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
           
            String stmt = "UPDATE account SET username = ?, password = ? WHERE account_id = ?";
            PreparedStatement updateStmt = myConnection.prepareStatement(stmt);

            updateStmt.setString(1, account.getUsername());
            updateStmt.setString(2, account.getPassword());
            updateStmt.setInt(3, id);
           

            updateStmt.executeUpdate();

            System.out.println("Successfully updated " + 1 + " account!");
        }catch(SQLException e){
                e.printStackTrace();
            }
        
    };
    public Account deleteAccount(int account_id){
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
           
            String stmt = "CREATE TRIGGER get_account BEFORE DELETE ON account FOR EACH ROW SELECT * FROM account WHERE account_id = ?";
            PreparedStatement deleteStmt = myConnection.prepareStatement(stmt);

            
            deleteStmt.setInt(1, account_id);
            
            ResultSet dResultSet = deleteStmt.getGeneratedKeys();

            deleteStmt.executeUpdate();
            
            int generatedKey = (int) dResultSet.getInt(1);

            while(dResultSet.next()){
                
                String user = dResultSet.getString(2);
                String pass = dResultSet.getString(3);

                Account deletedAccount = new Account(generatedKey, user, pass);

                System.out.println("Successfully deleted " + 1 + " account!");
                
                return deletedAccount;
            }
            
        }catch(SQLException e){
                e.printStackTrace();
            } 

        return null;
    };
}
