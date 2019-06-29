package information.system;

import information.system.Client.Controller.Client;
import information.system.Client.View.ChangePortForm;

public class ClientApp {
    public static void main(String[] args) {
        new ChangePortForm(new Client());
    }
}
