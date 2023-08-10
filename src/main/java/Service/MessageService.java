package Service;
import Model.Message;
import java.util.List;
import DAO.MessageDAO;
import DAO.MessageDAOImpli;

public class MessageService {

    private MessageDAO messageDAO;


    public MessageService(){
        messageDAO = new MessageDAOImpli();
    }

    public List<Message> getAllMessages(){
        
       return messageDAO.getMessages();
    }

    public List<Message> getMessageById(int message_id){

       return messageDAO.getMessageById(message_id);
    }


    public void addMessage(int posted_by, Message message){
        if(message.getMessage_text() != null && message.getMessage_text().length() < 255 && message
        .getPosted_by() == posted_by){
            messageDAO.addMessage(message);
        }
    }

    public void updateMessageId(int message_id, Message message){
        if(message.getMessage_id() == message_id && message.getMessage_text().length() < 255 && 
        message.getMessage_text() != null){
            messageDAO.updateMessage(message_id, message);
        }
    }

    public void deleteMessageById(int message_id){
        messageDAO.deleteMessageById(message_id);
    }
    
}
