package DAO;
import Model.Message;
import java.util.List;

import java.sql.SQLException;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Util.ConnectionUtil;


public class MessageDAOImpli implements MessageDAO{
    
  private Connection myConnection = null;

    public List<Message> getMessages() {
        List<Message> allMessages = new ArrayList<>();
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{   
             
             String stmt = "SELECT * FROM message";
             PreparedStatement  select = myConnection.prepareStatement(stmt);
             ResultSet sResultSet = select.executeQuery();

             while(sResultSet.next()){
                    
                    int message_id = sResultSet.getInt(1);
                    int posted_by = sResultSet.getInt(2);
                    String message_text = sResultSet.getString(3);
                    long time_posted_epoch = sResultSet.getLong(4);

                    Message newMessage = new Message(message_id, posted_by, message_text, time_posted_epoch);
                    
                    allMessages.add(newMessage);
             }

            
        }catch(SQLException e){
            e.printStackTrace();
            }
        
            
        System.out.println(allMessages);
        return allMessages;
    }


    public Message getMessageById(int messageId) {

        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{   
           
            
             String stmt = "SELECT * FROM message WHERE message_id = ?";
             PreparedStatement  select = myConnection.prepareStatement(stmt);

             select.setInt(1, messageId);
             
             ResultSet sResultSet = select.executeQuery();

             while(sResultSet.next()){
                    
                    int id = sResultSet.getInt(1);
                    int posted_by = sResultSet.getInt(2);
                    String message_text = sResultSet.getString(3);
                    long time_posted_epoch = sResultSet.getLong(4);

                    Message newMessage = new Message(id, posted_by, message_text, time_posted_epoch);
                    
                    System.out.println(newMessage.toString());
                    return newMessage;
             }

            
        }catch(SQLException e){
            e.printStackTrace();
            }
        
            
       
        return null;
    }


    public Message addMessage(Message message) {
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
            String stmt = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?)";
            PreparedStatement insertStmt = myConnection.prepareStatement(stmt);
                
            insertStmt.setInt(1, message.getPosted_by());
            insertStmt.setString(2, message.getMessage_text());
            insertStmt.setLong(3, message.getTime_posted_epoch());

            insertStmt.executeUpdate();

            ResultSet addResultSet = insertStmt.getGeneratedKeys();
        
            while(addResultSet.next()){
                   
                   int generatedKey = (int)addResultSet.getInt(1);
                   int posted_by = addResultSet.getInt(2);
                   String message_text = addResultSet.getString(3);
                   long time_posted_epoch = addResultSet.getLong(4);

                   Message newMessage = new Message(generatedKey, posted_by, message_text, time_posted_epoch);
                   
                   return newMessage;
            }
        
            
        }catch(SQLException e){
                e.printStackTrace();
            }

        return null;
    }


    public void updateMessage(int id, Message message) {
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
           
            String stmt = "UPDATE message SET posted_by = ?, message_text = ?, time_posted_epoch = ? WHERE message_id = ?";
            PreparedStatement updateStmt = myConnection.prepareStatement(stmt);

            updateStmt.setInt(1, message.getPosted_by());
            updateStmt.setString(2, message.getMessage_text());
            updateStmt.setLong(3, message.getTime_posted_epoch());
            updateStmt.setInt(4, id);
           

            updateStmt.executeUpdate();

            System.out.println("Successfully updated " + 1 + " message");
        }catch(SQLException e){
                e.printStackTrace();
            }
    }

  
    public Message deleteMessageById(int message_id) {
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
           
            String stmt = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement deleteStmt = myConnection.prepareStatement(stmt);

        
            deleteStmt.setInt(1, message_id);

            getMessageById(message_id);

            deleteStmt.executeUpdate();
            
        }catch(SQLException e){
                e.printStackTrace();
            }

            return null;
        }
    
    
}
