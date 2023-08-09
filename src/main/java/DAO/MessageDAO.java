package DAO;
import Model.Message;
import java.util.List;


public interface MessageDAO {

    List<Message> getMessages();
    List<Message> getMessageById(int message_id);
    
    void addMessage(Message message);
    void updateMessage(int id, Message message);
    void deleteMessage(Message message);
    void deleteMessageById(int message_id);
    
}
