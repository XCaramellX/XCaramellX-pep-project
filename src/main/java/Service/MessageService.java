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

    public List<Message> getMessagesByPostedById(int posted_by){

        return messageDAO.getMessagesByPostedById(posted_by);
     }

    public Message addMessage(Message message){

       if(message == null){
        return null;
       }
          
       return messageDAO.addMessage(message);
    }

    public Message updateMessage(int message_id, Message message){

        if(messageDAO.getMessageById(message_id) == null){
            return null;
        }
            
        return messageDAO.updateMessage(message_id, message);
    }

    public Message deleteMessageById(int message_id){

       return messageDAO.deleteMessageById(message_id);
    }
    
}
