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


    public Message addMessage(Message message){
       if(message == null){
        return null;
       }
          
       return  messageDAO.addMessage(message);
    }

    public Message updateMessageId(int message_id, Message message){
        if(message == null){
            return null;
        }
            
        messageDAO.updateMessage(message_id, message);
        return  messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id){
        if(messageDAO.deleteMessageById(message_id) == null){
            return null;
        }
       return messageDAO.deleteMessageById(message_id);
    }
    
}
