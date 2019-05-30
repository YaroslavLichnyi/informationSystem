package information.system.Server.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import information.system.Server.Controller.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;



public class ServerViewGUI implements ServerViewGeneral {

    final static Logger logger = Logger.getLogger(ServerViewGeneral.class);
    Server server;

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
    /**
     * Event handler onServerStartButtonPressed.
     */
    public void onServerStartButtonPressed(ActionEvent event) {
        server.start();
    }

    @FXML
    /**
     * Event handler onServerRestartButtonPressed.
     */
    public void onServerRestartButtonPressed(ActionEvent event) {
        server.restart();
    }

    @FXML
    /**
     * Event handler onServerStopButtonPressed.
     */
    public void onServerStopButtonPressed(ActionEvent event) {
        if (server.isRunning()) {
            server.stop();
        } else {
            logger.info("fault: attempt to stop a down server");
        }
    }

    @FXML
    /**
     * Event handler onServerTerminateButtonPressed.
     */
    public void onServerTerminateButtonPressed(ActionEvent event) {
        logger.info("server is terminating.");
        if (server.isRunning()) {
            server.stop();
        }
        System.exit(0);
    }

    @FXML
    /**
     * Event handler onServerPortApplyButtonPressed.
     */
    public void onServerPortApplyButtonPressed(ActionEvent event) {
        int newPort = Integer.parseInt(portTextField.getText());
        server.changePort(newPort);
        if (server.portChanged()) {
            portTextField.setText(String.valueOf(server.getPort()));
            server.setPortChanged(false);
        }
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
        assert serverStopButton != null :
                "fx:id=\"serverStopButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverTerminateButton != null :
                "fx:id=\"serverTerminateButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverStartButton != null :
                "fx:id=\"serverStartButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverStateLabel != null :
                "fx:id=\"serverStateLabel\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverPortApplyButton != null :
                "fx:id=\"serverPortApplyButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert serverRestartButton != null :
                "fx:id=\"serverRestartButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";

        serverTerminateButton.setOnAction(event -> {
            onServerTerminateButtonPressed(new ActionEvent());
        });

        portTextField.setText(String.valueOf(server.getPort()));

        logger.info("ServerViewGUI started.");

    }

    @Override
    public void display(String message) {
        serverStateLabel.setText(message);
    }

    @Override
    public void closeView() {
        logger.info("ServerView will be closed.");
        serverTerminateButton.getScene().getWindow().hide();
        //System.exit(0); // is needed to be added into Controller
    }

}

