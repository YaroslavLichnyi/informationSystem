package information.system;
import information.system.Client.Controller.Client;
import information.system.Client.View.ChangePortForm;

class ClientApp {
    public static void main(String[] args) {
        new ChangePortForm(new Client());
    }
}
