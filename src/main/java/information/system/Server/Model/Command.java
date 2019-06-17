package information.system.Server.Model;

public interface Command {
    public static final String SERVER_FILE_RESTAURANT = ".\\server_data\\restaurant.xml";
    public static final String CLIENT_FILE_RESTAURANT = ".\\client_data\\restaurant.xml";
    public static final String SERVER_FILE_ADMINS  = ".\\server_data\\users.xml";
    public static final String CLIENT_FILE_ADMINS  = ".\\client_data\\users.xml";
    public static final String FILE = "file";
    public static final String ADD  = "add";
    public static final String REMOVE = "remove";
    public static final String EDIT = "edit";
    public static final String GET_ALL_INFORMATION = "get_all_information";
    public static final String STOP = "stop";
    public static final String SIGN_IN = "sign_in";
    public static final String CORRECT = "correct";
    public static final String INCORRECT = "incorrect";
    public static final String PRICE = "price";
    public static final String DISH_CAREGORY = "dish category";
}
