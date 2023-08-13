package DAO;
import Model.Message;
import java.util.List;

import java.sql.SQLException;
import java.sql.Statement;
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
                    
                    return newMessage;
             }

        }catch(SQLException e){
            e.printStackTrace();
            }
        
        return null;
    }

    public List<Message> getMessagesByPostedById(int posted_by) {

        List<Message> allMessages = new ArrayList<>();

        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{   
           
             String stmt = "SELECT * FROM message WHERE posted_by = ?";
             PreparedStatement  select = myConnection.prepareStatement(stmt);

             select.setInt(1, posted_by);
             
             ResultSet sResultSet = select.executeQuery();

             while(sResultSet.next()){
                    
                    int id = sResultSet.getInt(1);
                    int postedby = sResultSet.getInt(2);
                    String message_text = sResultSet.getString(3);
                    long time_posted_epoch = sResultSet.getLong(4);

                    
                    Message newMessage = new Message(id, postedby, message_text, time_posted_epoch);

                    allMessages.add(newMessage);
                
             }

        }catch(SQLException e){
            e.printStackTrace();
            }
        
            return allMessages;
    }


    public Message addMessage(Message message) {
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
            String stmt = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?)";
            PreparedStatement insertStmt = myConnection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
                
            insertStmt.setInt(1, message.getPosted_by());
            insertStmt.setString(2, message.getMessage_text());
            insertStmt.setLong(3, message.getTime_posted_epoch());

            insertStmt.executeUpdate();

            ResultSet addResultSet = insertStmt.getGeneratedKeys();
        
            while(addResultSet.next()){
                if(!message.getMessage_text().isBlank() && message.getMessage_text().length() < 255){

                   int generatedKey = (int)addResultSet.getInt(1);
                   int posted_by = message.getPosted_by();
                   String message_text = message.getMessage_text();
                   long time_posted_epoch = message.getTime_posted_epoch();

                   Message newMessage = new Message(generatedKey, posted_by, message_text, time_posted_epoch);
                   
                   return newMessage;
                }
            }
        
            
        }catch(SQLException e){
                e.printStackTrace();
            }

        return null;
    }


    public Message updateMessage(int id, Message message) {
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
           
            String selectStmt = "SELECT * FROM message WHERE message_id = ?";
            String stmt = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement select = myConnection.prepareStatement(selectStmt);
            PreparedStatement updateStmt = myConnection.prepareStatement(stmt);
     
            select.setInt(1, id);

            updateStmt.setString(1, message.getMessage_text());
            updateStmt.setInt(2, id);
           
            ResultSet sSet = select.executeQuery();

            while(sSet.next()){
            if(message.getMessage_text().length() < 255 && !message.getMessage_text().isBlank()){

                updateStmt.executeUpdate();
                Message newMessage = new Message(sSet.getInt(1), sSet.getInt(2), 
                message.getMessage_text(), sSet.getLong(4));
                
                return newMessage;
            }
        }
        }catch(SQLException e){
                e.printStackTrace();
            }
        return null;
    }

    public Message deleteMessageById(int message_id) {
        
        if(myConnection == null){
            myConnection = ConnectionUtil.getConnection();
        } 
        try{
           
            String selectStmt = "SELECT * FROM message WHERE message_id = ?";
            String stmt = "DELETE FROM message WHERE message_id = ?";
        
            PreparedStatement select = myConnection.prepareStatement(selectStmt);
            PreparedStatement deleteStmt = myConnection.prepareStatement(stmt);

            select.setInt(1, message_id);
            deleteStmt.setInt(1, message_id);
            
            ResultSet sSet = select.executeQuery();
           
             if(sSet.next()){
               
                Message message = new Message(sSet.getInt(1), sSet.getInt(2), sSet.getString(3), sSet.getLong(4));

                return message;
        
            }
            
            deleteStmt.executeUpdate();
            

        }catch(SQLException e){
                e.printStackTrace();
            }

            return null;
            
        }
    
}
