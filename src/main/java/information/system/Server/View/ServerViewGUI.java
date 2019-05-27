package information.system.Server.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ServerViewGUI {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ServerStopButton;

    @FXML
    private Button ServerTerminateButton;

    @FXML
    private Button ServerRebootButton;

    @FXML
    private Button ServerStartButton;

    @FXML
    private Label ServerStateLabel;

    @FXML
    private Button ServerPortApplyButton;

    @FXML
    void initialize() {
        assert ServerStopButton != null : "fx:id=\"ServerStopButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerTerminateButton != null : "fx:id=\"ServerTerminateButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerRebootButton != null : "fx:id=\"ServerRebootButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerStartButton != null : "fx:id=\"ServerStartButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerStateLabel != null : "fx:id=\"ServerStateLabel\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";
        assert ServerPortApplyButton != null : "fx:id=\"ServerPortApplyButton\" was not injected: check your FXML file 'ServerViewGUI.fxml'.";

    }

}

