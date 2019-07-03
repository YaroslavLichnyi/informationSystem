package information.system.Server.View;

import org.apache.log4j.Logger;

/**
 * Console component View for MVC-pattern of Information System - class ServerView.
 */
public class ServerView implements ServerViewGeneral {

    final static Logger logger = Logger.getLogger(ServerViewGeneral.class);

    /**
     * Display server state when it is necessary.
     * @param message
     */
    @Override
    public void display(String message){
        System.out.println(message);
    }

    /**
     * Event handler for View closing.
     */
    @Override
    public void closeView(){
        logger.info("Application is terminating.");
        System.exit(0);
    }

    /**
     * Logging server events.
     * @param message
     */
    @Override
    public void logging(String message) {
        System.out.println(message);
    }
}
