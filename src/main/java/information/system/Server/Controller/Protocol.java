package information.system.Server.Controller;

/**
 * @author Yaroslav Lichnyi
 * @author Alexands Plachkovskyy
 */
public interface Protocol {
    String ADD_DISH              = "add_dish";
    String ADD_DISH_CATEGORY     = "add_dish_category";
    String SIGN_UP               = "sign_up";
    String DELETE_DISH           = "delete_dish";
    String DELETE_DISH_CATEGORY  = "delete_dish_caetgory";
    String DELETE_USER           = "delete_user";
    String EDIT_DISH             = "edit_dish";
    String EDIT_DISH_CATEGORY    = "edit_dish_category";
    String EDIT_USER             = "edit_user";
    String END_OF_SESSION        = "end_of_session";
    String SIGN_IN               = "sign_in";
    String FIND_DISH             = "find_dish";
    String SORT_BY_PRICE         = "sort_by_price";
    String SORT_BY_DISH_CATEGORY = "sort_by_dish_category";
    String UPDATE_INFORMATION    = "update_information";
    String GET_USERS             = "get_users";
    String MAKE_ADMIN            = "make_admin";
    String TRUE                  = "true";
    String FALSE                 = "false";
}
