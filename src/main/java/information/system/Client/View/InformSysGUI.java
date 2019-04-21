package information.system.Client.View;

/**
 * Determines which methods are needed to work with the client GUI.
 * @author Yaroslav Lichnyi
 */
public interface InformSysGUI {
    /**
     *
     */
    public void init();

    /**
     * Adds new object in the table.
     * @return true if the addition was successful, else return false.
     */
    public boolean add();

    /**
     * Edits existing data in the table.
     * @return true if the edition was successful, else return false.
     */
    public boolean edit();

    /**
     * Edits existing objects in the table.
     * @return true if the deleting was successful, else return false.
     */
    public boolean delete();

    /**
     * Updates content, getting actual information.
     * @return true if the update was successful, else return false.
     */
    public boolean updateContent();
}
