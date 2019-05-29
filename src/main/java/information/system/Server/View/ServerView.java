package information.system.Server.View;

import information.system.Server.Controller.Server;
import org.apache.log4j.Logger;

public class ServerView implements ServerViewGeneral {

    final static Logger logger = Logger.getLogger(ServerViewGeneral.class);

    @Override
    public void display(String message){
        System.out.println(message);
    }

    @Override
    public void closeView(){
        logger.info("Application is terminating.");
        System.exit(0);
    }
}
