package information.system.Server.Model;
import org.w3c.dom.Document;
import java.util.List;

/**
 * This class was created to work with XML-document.
 * It can change file content:
 *          1) Adds new data;
 *          2) Deletes data;
 *          4) Gets objects from XML-file;
 *          5) Converts a string to a document;
 *          6) Converts a document to a string.
 */
public class XmlSet {
    /**
     * Parses given document and extracts dishes from it.
     *
     * @param document is a resource from which dishes come from.
     * @return dishes from <code>document</code>
     */
    public static List<Dish> getDishesFrom(Document document){
        return null;
    }

    /**
     * Parses given document and extracts dish categories from it.
     *
     * @param document is a resource from which dish categories come from.
     * @return dishes from <code>document</code>
     */
    public static List<DishСategory> getDishCategoriesFrom(Document document){
        return null;
    }


    /**
     * Inserts new dishes into existing file.
     *
     * @param dishes are added to existing file.
     */
    public static void insertDishesIntoXmlFile(List<Dish> dishes){
    }

    /**
     * Inserts new dishCategories into existing file.
     *
     * @param dishCategories are added to existing file.
     */
    public static void insertDishCategoriesIntoXmlFile(List<DishСategory> dishCategories){
    }

    /**
     * Deletes given dishes from XML-file information about the dishes is stored.
     *
     * @param dishes are deleted from XML-file.
     */
    public static void deleteDishesFromXmlFile(List<Dish> dishes){
    }

    /**
     * Deletes given dishes from XML-file information about the dish categories is stored.
     *
     * @param dishCategories are deleted from XML-file.
     */
    public static void deleteDishCategoriesFromXmlFile(List<DishСategory> dishCategories){
    }

    /**
     * Gets a document from the given xml-file.
     *
     * @param fileName is a name of the the file or file path.
     * @return document of the file.
     */
    public static Document getDocumentFromFile(String fileName){
        return null;
    }

    /**
     * Converts a document to a string.
     *
     * @param document is converted to a string.
     * @return a string created from the document.
     */
    public static String convertDocumentToString(Document document){
        return null;
    }

    /**
     * Converts a string to a document.
     *
     * @param str is converted to a document.
     * @return a document created from the string.
     */
    public static Document convertStringToDocument(String str){
        return null;
    }

}
