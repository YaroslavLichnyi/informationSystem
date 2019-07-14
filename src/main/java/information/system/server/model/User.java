package information.system.server.model;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Class describes users of system.
 */
public class User {
    private String name;
    private String login;
    private String password;
    private boolean admin;
    private int id;


    /**
     * Constructor 2.
     */
    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    /**
     * Constructor 1.
      */
    public User() {
    }

    /**
     * Getter for name.
      * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
      * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for login.
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for login.
      * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter for password.
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password.
      * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for "admin".
     * @return
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Setter for "admin".
      * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Getter for id.
      * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id.
      * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Generation of unique id.
     * @return
     */
    private static int generateUniqueId(){
        int id = 0;
        boolean free = false;
        LinkedList <User> users = InformSystXML.readUsers(Command.SERVER_FILE_ADMINS);
        if(users.size() > 0){
            while (!free){
                for (User user : users) {
                    if (user.getId() == id){
                        id++;
                        continue;
                    }
                    free = true;
                }
            }
        } else {
            return 0;
        }
        return id;
    }

    /**
     * Edit user.
     * @param oldUser
     * @param newUser
     * @return boolean
     */
    public static boolean edit(User oldUser, User newUser) {
        for (User user : InformSystXML.readUsers(Command.SERVER_FILE_ADMINS)) {
            if (user.equals(oldUser)){
                user.setName(newUser.getName());
                user.setLogin(newUser.getLogin());
                user.setPassword(newUser.getPassword());
                return true;
            }
        }
        return false;
    }

    /**
     * Sign in into application.
     * @param login is admin's <code>login</code>.
     * @param password is admin's <code>password</code>.
     * @return Admin object, if there is the admin with the same name
     *        or return null, if there is no admin with the same name
     */
    public static User signIn(String login, String password){
        for (User user:InformSystXML.readUsers(Command.SERVER_FILE_ADMINS)) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    /**
     * Create new user.
     * @param user
     * @return
     */
    public static boolean signUp(User user){
        if (isLoginFree(user.getLogin())){
            LinkedList<User> users = InformSystXML.readUsers(Command.SERVER_FILE_ADMINS);
            users.add(user);
            user.setId(generateUniqueId());
            InformSystXML.writeUsers(users, Command.SERVER_FILE_ADMINS);
            return true;
        }
        return false;
    }


    /**
     * Checks if a user with the same login exists.
     * @param login is a login which is checked.
     * @return true if there is no user with the same login.
     */
    public static boolean isLoginFree(String login) {
        for (User admin:InformSystXML.readUsers(Command.SERVER_FILE_ADMINS)) {
            if (admin.getLogin().equals(login)){
                return false;
            }
        }
        return true;
    }

    public static boolean delete(User userToDelete){
        List<User> users = InformSystXML.readUsers(Command.SERVER_FILE_ADMINS);
        if (users.remove(userToDelete)){
            InformSystXML.writeUsers(users, Command.SERVER_FILE_ADMINS);
            return true;
        }
        return false;
    }

    public static boolean makeAdmin(User user54){
        List<User> users = InformSystXML.readUsers(Command.SERVER_FILE_ADMINS);
        for (User user : users) {
            if (user.equals(user54) && !user.isAdmin()){
                user.setAdmin(true);
                InformSystXML.writeUsers(users, Command.SERVER_FILE_ADMINS);
                return true;
            }
        }
        return false;
    }

    /**
     * Mandatory method.
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return admin == user.admin &&
                id == user.id &&
                name.equals(user.name) &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    /**
     * Mandatory method.
      * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, login, password, admin, id);
    }

    /**
     * Mandatory method.
      * @return String
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", id=" + id +
                '}';
    }

}
