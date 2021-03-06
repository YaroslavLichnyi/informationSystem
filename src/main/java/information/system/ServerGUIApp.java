package information.system;

import information.system.server.view.ServerViewGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import java.io.IOException;

/**
 * Class for lunching server GUI version
 */
public class ServerGUIApp extends Application {

    final static Logger logger = Logger.getLogger(ServerGUIApp.class);

    /**
     * Start application.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) {

        logger.info("application started.");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ServerViewGUI.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("error starting ServerViewGUI. " + e.getMessage());
        }
        ServerViewGUI serverViewGUI = new ServerViewGUI();
        loader.setController(serverViewGUI);
        Parent root = loader.getRoot();
        primaryStage.setTitle("Information System server");
        primaryStage.setScene(new Scene(root, 590, 390));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(serverViewGUI.getCloseEventHandler());
        primaryStage.show();

    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);// -> autocalling start(Stage primaryStage)
    }

}
