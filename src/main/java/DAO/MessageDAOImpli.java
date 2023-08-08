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

    @Override
    public List<Message> getMessages() {
        List<Message> allMessages = new ArrayList<>();

        Connection myConnection = null;
        try{   
           
             myConnection = ConnectionUtil.getConnection();
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

    @Override
    public List<Message> getMessageById(int message_id) {
        List<Message> allMessages = new ArrayList<>();

        Connection myConnection = null;
        try{   
           
             myConnection = ConnectionUtil.getConnection();
             String stmt = "SELECT * FROM message WHERE message_id = ?";
             PreparedStatement  select = myConnection.prepareStatement(stmt);
             ResultSet sResultSet = select.executeQuery();

             while(sResultSet.next()){
                    
                    int messageId = sResultSet.getInt(message_id);
                    int posted_by = sResultSet.getInt(2);
                    String message_text = sResultSet.getString(3);
                    long time_posted_epoch = sResultSet.getLong(4);

                    Message newMessage = new Message(messageId, posted_by, message_text, time_posted_epoch);
                    
                    allMessages.add(newMessage);
             }

            
        }catch(SQLException e){
            e.printStackTrace();
            }
            
        System.out.println(allMessages);
        return allMessages;
    }

    @Override
    public void addMessage(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMessage'");
    }

    @Override
    public void updateMessage(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMessage'");
    }

    @Override
    public void deleteMessage(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMessage'");
    }

    @Override
    public void deleteMessageById(int message_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMessageById'");
    }
    
}
