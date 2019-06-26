package information.system.Server.Model;
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

    private int generateUniqueId(){
        /*
        int id = 0;
        boolean free = false;
        InformSystXML.readAdmins("");
        if(dishCategories.size() > 0){
            while (!free){
                for (Dish dish: menu) {
                    if (dish.getId() == id){
                        id++;
                        continue;
                    }
                    free = true;
                }
            }
        } else return 0;
        */
        return id;
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
