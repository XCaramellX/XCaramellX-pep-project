package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
  private AccountService accountService;

    public SocialMediaController(){
       accountService = new AccountService();
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
        Account addAccount = accountService.addAccountByUserPass(account);

        if(addAccount == null){
            ctx.status(401);
        }else{
            ctx.json(loginMapper.writeValueAsString(addAccount));
        }

    }


}