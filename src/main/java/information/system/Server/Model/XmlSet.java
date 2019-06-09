package information.system.Server.Model;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * This class was created to work with XML-document.
 * It can change file content:
 *          1) Adds new data;
 *          2) Deletes data;
 *          4) Gets objects from XML-file;
 *          5) Converts a string to a document;
 *          6) Converts a document to a string.
 *
 * @author Yaroslav Lichnyi
 */
public class XmlSet {
    private static final Logger LOGGER = Logger.getLogger(XmlSet.class);

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
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
        } catch (IOException | SAXException | ParserConfigurationException  e) {
            LOGGER.error("Getting a document from " + fileName + " was failed.");
        }
        return null;
    }

    /**
     * Converts a document to a string.
     *
     * @param document is converted to a string.
     * @return a string created from the document.
     */
    public static String convertDocumentToString(Document document){
        DOMImplementationLS domImplementation = (DOMImplementationLS) document.getImplementation();
        LSSerializer serializer = domImplementation.createLSSerializer();
        return serializer.writeToString(document);
        //сделать логгирование
        /*
        аналог
        try {
        StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        return sw.toString();
    } catch (Exception ex) {
        throw new RuntimeException("Error converting to String", ex);
    }
         */

    }

    /**
     * Converts a string to a document.
     *
     * @param str is converted to a document.
     * @return a document created from the string.
     */
    public static Document convertStringToDocument(String str){
        Document document = null;
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(str));
            document = db.parse(is);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            LOGGER.error("Converting string \"" + str + "\" was failed.",e);
        }
        return document;
    }

}
