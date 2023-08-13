package DAO;
import Model.Message;
import java.util.List;


public interface MessageDAO {

    List<Message> getMessages();
    Message getMessageById(int message_id);
    List<Message> getMessagesByPostedById(int posted_by);
    
    Message addMessage(Message message);
    Message updateMessage(int id, Message message);
    Message deleteMessageById(int message_id);
    
}
