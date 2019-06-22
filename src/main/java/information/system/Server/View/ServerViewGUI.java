package information.system.Server.View;

import information.system.Server.Controller.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Graphic component View for MVC-pattern of Information System - class ServerViewGUI.
 */
public class ServerViewGUI implements ServerViewGeneral {

    final static Logger logger = Logger.getLogger(ServerViewGeneral.class);
    private Server server;

    /**
     * Constructor of class ServerViewGUI.
     */
    public ServerViewGUI() {
        this.server = new Server(this);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField portTextField;

    @FXML
    private Button serverStopButton;

    @FXML
    private Button serverTerminateButton;

    @FXML
    private Button serverStartButton;

    @FXML
    public Label serverStateLabel;

    @FXML
    private Button serverPortApplyButton;

    @FXML
    private Button serverRestartButton;

    @FXML
    private TextArea serverLogTextArea;

    /**
     * Event handler onServerStartButtonPressed.
     */
    @FXML
    public void onServerStartButtonPressed(ActionEvent event) {
        server.start();
    }

    /**
     * Event handler onServerRestartButtonPressed.
     */
    @FXML
    public void onServerRestartButtonPressed(ActionEvent event) {
        server.restart();
    }

    /**
     * Event handler onServerStopButtonPressed.
     */
    @FXML
    public void onServerStopButtonPressed(ActionEvent event) {
        server.stop();
    }

    /**
     * Event handler onServerTerminateButtonPressed.
     */
    @FXML
    public void onServerTerminateButtonPressed(ActionEvent event) {
        logger.info("server is being terminated.");
//        logging("Server is terminating.");        // generates an error
        if (server.isRunning()) {
            server.stop();
        }
        logger.info("application is being closed.");
//        logging("Application is being closed.");  // generates an error
        System.exit(0);
    }

    /**
     * Event handler onServerPortApplyButtonPressed.
     */
    @FXML
    public void onServerPortApplyButtonPressed(ActionEvent event) {
        int newPort = Integer.parseInt(portTextField.getText());
        server.changePort(newPort);
        if (server.portChanged()) {
            portTextField.setText(String.valueOf(server.getPort()));
            server.setPortChanged(false);
        }
    }

    // onCloseWindow ***************************************************************************************************
                                                                                                                     //*
                                                                                                                     //*
                                                                                                                     //*
    /**                                                                                                              //*
     * Event handler onCloseWindow.                                                                                  //*
     */                                                                                                              //*
    private javafx.event.EventHandler<WindowEvent> closeEventHandler = event -> {                                    //*
        onServerTerminateButtonPressed(new ActionEvent());                                                           //*
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
        assert serverStopButton != null :
                "fx:id=\"serverStopButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverTerminateButton != null :
                "fx:id=\"serverTerminateButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverStartButton != null :
                "fx:id=\"serverStartButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverPortApplyButton != null :
                "fx:id=\"serverPortApplyButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverRestartButton != null :
                "fx:id=\"serverRestartButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverStateLabel != null :
                "fx:id=\"serverStateLabel\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert portTextField != null :
                "fx:id=\"portTextField\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverLogTextArea != null :
                "fx:id=\"serverLogTextArea\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";


        /*
         * Event handler for terminateButton press.
         */
        serverTerminateButton.setOnAction((ActionEvent event) -> {
            onServerTerminateButtonPressed(new ActionEvent());
        });

        portTextField.setText(String.valueOf(server.getPort()));

        logger.info("ServerViewGUI started.");

    }

    /**
     * Display server state in serverStateLabel.
     */
    @Override
    public void display(String message) {
        serverStateLabel.setText(message);
    }

    /**
     * Event handler for View closing.
     */
    @Override
    public void closeView() {
        logger.info("ServerView will be closed.");
        serverTerminateButton.getScene().getWindow().hide();
        //System.exit(0); // is needed to be added into Controller
    }

    /**
     * Logging server events into serverLogTextArea.
     */
    @Override
    public void logging(String message) {
        serverLogTextArea.appendText(LocalDateTime.now() + " - " + message + "\n");

    }

}
