package information.system.Server.Model;

public interface Command {
    static final String SERVER_FILE_RESTAURANT = ".\\server_data\\restaurant.xml";
    static final String CLIENT_FILE_RESTAURANT = ".\\client_data\\restaurant.xml";
    static final String SERVER_FILE_ADMINS     = ".\\server_data\\users.xml";
    static final String CLIENT_FILE_ADMINS     = ".\\client_data\\users.xml";
    static final String FILE                   = "file";
    static final String ADD                    = "add";
    static final String REMOVE                 = "remove";
    static final String EDIT                   = "edit";
    static final String GET_ALL_INFORMATION    = "get_all_information";
    static final String STOP                   = "stop";
    static final String SIGN_IN                = "sign_in";
    static final String CORRECT                = "correct";
    static final String INCORRECT              = "incorrect";
    static final String PRICE                  = "price";
    static final String DISH_CATEGORY          = "dish category";
}
