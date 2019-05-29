package information.system.Server.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;



public class ServerViewGUI implements ServerViewGeneral {

    final static Logger logger = Logger.getLogger(ServerViewGeneral.class);

    public ServerViewGUI() {

    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ServerStopButton;

    @FXML
    private Button ServerTerminateButton;

    @FXML
    private Button ServerStartButton;

    @FXML
    private Label ServerStateLabel;

    @FXML
    private Button ServerPortApplyButton;

    @FXML
    private Button ServerRestartButton;

    @FXML
    /**
     * Event handler onServerStartButtonPressed.
     */
    public void onServerStartButtonPressed(ActionEvent event) {

    }

    @FXML
    /**
     * Event handler onServerRestartButtonPressed.
     */
    public void onServerRestartButtonPressed(ActionEvent event) {

    }

    @FXML
    /**
     * Event handler onServerStopButtonPressed.
     */
    public void onServerStopButtonPressed(ActionEvent event) {

    }

    @FXML
    /**
     * Event handler onServerTerminateButtonPressed.
     */
    public void onServerTerminateButtonPressed(ActionEvent event) {
        logger.info("server is terminating.");
        System.exit(0);
    }

    @FXML
    /**
     * Event handler onServerPortApplyButtonPressed.
     */
    public void onServerPortApplyButtonPressed(ActionEvent event) {

    }

    // onCloseWindow ***************************************************************************************************
    /**                                                                                                              //*
     * Event handler onCloseWindow                                                                                   //*
     */                                                                                                              //*
    private javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>() {//*
        @Override                                                                                                    //*
        public void handle(WindowEvent event) {                                                                      //*
            onServerTerminateButtonPressed(new ActionEvent());                                                       //*
        }                                                                                                            //*
    };                                                                                                               //*
                                                                                                                     //*
    /**                                                                                                              //*
     * Getter for event handler.                                                                                     //*
     * @return EventHandler<WindowEvent>.                                                                            //*
     */                                                                                                              //*
    public javafx.event.EventHandler<WindowEvent> getCloseEventHandler(){                                            //*
        return closeEventHandler;                                                                                    //*
    }                                                                                                                //*
    //******************************************************************************************************************


    @FXML
    void initialize() {
        assert ServerStopButton != null :
                "fx:id=\"ServerStopButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerTerminateButton != null :
                "fx:id=\"ServerTerminateButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerStartButton != null :
                "fx:id=\"ServerStartButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerStateLabel != null :
                "fx:id=\"ServerStateLabel\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerPortApplyButton != null :
                "fx:id=\"ServerPortApplyButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerRestartButton != null :
                "fx:id=\"ServerRestartButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";

        ServerTerminateButton.setOnAction(event -> {
            onServerTerminateButtonPressed(new ActionEvent());
        });

        logger.info("ServerViewGUI started.");

    }

    @Override
    public void display(String message) {
        ServerStateLabel.setText(message);
    }

    @Override
    public void closeView() {
        logger.info("ServerView will be closed.");
        ServerTerminateButton.getScene().getWindow().hide();
        //System.exit(0); // is needed to be added into Controller
    }

}

