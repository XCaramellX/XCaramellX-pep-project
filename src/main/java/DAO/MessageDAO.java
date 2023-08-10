package DAO;
import Model.Message;
import java.util.List;


public interface MessageDAO {

    List<Message> getMessages();
    Message getMessageById(int message_id);
    
    Message addMessage(Message message);
    void updateMessage(int id, Message message);
    Message deleteMessageById(int message_id);
    
}
