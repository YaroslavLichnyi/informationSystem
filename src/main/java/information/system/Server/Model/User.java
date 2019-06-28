package information.system.Server.Model;
import java.util.LinkedList;
import java.util.Objects;

public class User {
    private String name;
    private String login;
    private String password;
    private boolean admin;
    private int id;


    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
     * Signin in application.
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

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password, admin, id);
    }

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
