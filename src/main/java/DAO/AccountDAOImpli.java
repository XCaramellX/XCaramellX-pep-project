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
    
    public void addAccount(Account account){
        
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
           
            String stmt = "INSERT INTO account(username, password) VALUES(?, ?)";
            PreparedStatement insertStmt = myConnection.prepareStatement(stmt);
                
            insertStmt.setString(1, account.getUsername());
            insertStmt.setString(2, account.getPassword());
            

            insertStmt.executeUpdate();
        
            System.out.println("Successfully added " + 1 + " account!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    
       
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
    public void deleteAccount(int account_id){
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
           
            String stmt = "DELETE FROM account WHERE account_id = ?";
            PreparedStatement deleteStmt = myConnection.prepareStatement(stmt);

            
            deleteStmt.setInt(1, account_id);
           
            deleteStmt.executeUpdate();
            
            
            System.out.println("Successfully deleted " + 1 + " account!");
        }catch(SQLException e){
                e.printStackTrace();
            } 
    };
}
