package information.system.Server.Model;

/**
 *
 */
public class Authorization {
    /**
     * Signin in application.
     * @param login is admin's {@link Admin#login}.
     * @param password is admin's {@link Admin#password}.
     * @return Admin object, if there is the admin with the same name
     *        or return null, if there is no admin with the same name
     */
    public static Admin singIn(String login, String password){
        for (Admin admin:InformSystXML.readAdmins(Command.SERVER_FILE_ADMINS)) {
            if (admin.getLogin().equals(login) && admin.getPassword().equals(password)){
                return admin;
            }
        }
        return null;
    }

    /**
     * Check if {@link Admin#login} and {@link Admin#password} were inputted correct.
     * @param login is admin's {@link Admin#login}.
     * @param password is admin's {@link Admin#password}.
     * @return true, if <b>login</b> and <b>password</b> were inputted correct, else return false.
     */
    public static boolean isInputtedDataCorrect(String login, String password){
        if (login != null && password != null && login.length() > 5 && password.length() > 5) return true;
        return false;
    }
}
