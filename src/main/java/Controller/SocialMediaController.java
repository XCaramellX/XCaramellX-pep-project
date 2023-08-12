package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(){
       accountService = new AccountService();
       messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void registerHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper registerMapper = new ObjectMapper();
        Account account = registerMapper.readValue(ctx.body(), Account.class);
        Account addAccount = accountService.addAccount(account);
        
        if(addAccount == null){
            ctx.status(400);
        }else{
            ctx.json(registerMapper.writeValueAsString(addAccount));
        }
       
    }

    private void loginHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper loginMapper = new ObjectMapper();
        Account account = loginMapper.readValue(ctx.body(), Account.class);
        Account getAccount = accountService.getAccountByUser(account);

        if(getAccount == null){
            ctx.status(401);
        }else{
            ctx.json(loginMapper.writeValueAsString(getAccount));
        }

    }

    private void createMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper addMessageMapper = new ObjectMapper();
        Message message = addMessageMapper.readValue(ctx.body(), Message.class);
        Message addMessage = messageService.addMessage(message);

        if(addMessage == null){
            ctx.status(400);
        }else{
            ctx.json(addMessageMapper.writeValueAsString(addMessage));
        }

    }


}