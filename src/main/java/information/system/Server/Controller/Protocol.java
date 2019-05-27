package information.system.Server.Controller;

/**
 * @author Yaroslav Lichnyi
 * @author Alexands Plachkovskyy
 */
public interface Protocol {
    public static final String ADD  = "add";
    public static final String DELETE  = "delete";
    public static final String EDIT  = "edit";
    public static final String GET_INFORMATION   = "get_information";
    public static final String END_OF_SESSION   = "end_of_session";
    public static final String SIGN_UP = "sign_up";
    public static final String SIGN_IN = "sign_in";
    public static final String FIND_DISH = "find_dish";
    public static final String SORT_BY_PRICE = "sort_by_price";
    public static final String SORT_BY_DISH_CATEGORY = "sort_by_dish_category";


}
