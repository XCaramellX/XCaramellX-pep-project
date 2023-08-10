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

    public Message getMessageById(int message_id){

       return messageDAO.getMessageById(message_id);
    }


    public Message addMessage(int posted_by, Message message){
        if(message.getMessage_text() != null && message.getMessage_text().length() < 255 && message
        .getPosted_by() == posted_by){
          return  messageDAO.addMessage(message);
        }

        return null;
    }

    public Message updateMessageId(int message_id, Message message){
        if(message.getMessage_id() == message_id && message.getMessage_text().length() < 255 && 
        message.getMessage_text() != null){
            messageDAO.updateMessage(message_id, message);
            return  messageDAO.getMessageById(message_id);
        }

        return null;
    }

    public Message deleteMessageById(int message_id){
        if(messageDAO.deleteMessageById(message_id) == null){
            return null;
        }
       return messageDAO.deleteMessageById(message_id);
    }
    
}
