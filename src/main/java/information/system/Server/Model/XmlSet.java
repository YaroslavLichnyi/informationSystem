package information.system.Server.Model;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.LinkedList;
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
    private static final String NAME = "name";
    private static final String DISH_CATEGORY = "dish-category";
    private static final String PRICE = "price";
    private static final String DISH = "dish";
    private static final String DESCRIPTION = "description";
    private static final String ADMIN = "admin";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String HEALTHY_FOOD = "healthy-food";
    private static final String ID = "id";
    private static final String DISH_CATEGORY_ID = "dish-category-id";

    private String userId;
    private String command;


    public XmlSet() {
    }

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
    public static List<DishCategory> getDishCategoriesFrom(Document document){
        return null;
    }


    /**
     * Inserts new dishes into existing file.
     *
     * @param dishes are added to existing file.
     */
    public static void insertDishesIntoXmlFile(List<Dish> dishes, String fileName){
        Document document = getDocumentFromFile(fileName);

        Element root = document.getDocumentElement();
        for (Dish dish : dishes) {
            NodeList dishCategoriesFromXML = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < dishCategoriesFromXML.getLength(); i++) {
                if (dishCategoriesFromXML.item(i).getNodeName().equals(DISH_CATEGORY)) {
                    DishCategory dishСategory = new DishCategory();
                    for (int j = 0; j < dishCategoriesFromXML.item(i).getAttributes().getLength(); j++) {
                        if ("id".equals(dishCategoriesFromXML.item(i).getAttributes().item(j).getNodeName())
                                && (String.valueOf(dish.getDishCategoryId())).equals(dishCategoriesFromXML.item(i).getAttributes()
                                .item(j).getNodeValue())){

                            Element dishEl = document.createElement(DISH);
                            dishCategoriesFromXML.item(i).appendChild(dishEl);

                            Attr attrDishId = document.createAttribute(ID);
                            attrDishId.setValue(String.valueOf(dish.getId()));
                            dishEl.setAttributeNode(attrDishId);

                            Attr attrDishCategoryId = document.createAttribute(DISH_CATEGORY_ID);
                            attrDishCategoryId.setValue(String.valueOf(dish.getDishCategoryId()));
                            dishEl.setAttributeNode(attrDishCategoryId);

                            Attr attrDishName = document.createAttribute(NAME);
                            attrDishName.setValue(String.valueOf(dish.getName()));
                            dishEl.setAttributeNode(attrDishName);

                            Attr attrDishPrice = document.createAttribute(PRICE);
                            attrDishPrice.setValue(String.valueOf(dish.getPrice()));
                            dishEl.setAttributeNode(attrDishPrice);

                            Element description = document.createElement(DESCRIPTION);
                            description.appendChild(document.createTextNode(dish.getDescription()));
                            dishEl.appendChild(description);

                            dishCategoriesFromXML.item(i).appendChild(dishEl);
                        }
                    }
                }
            }
        }
        DOMSource source = new DOMSource(document);
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(fileName);
            transformer.transform(source, result);
        } catch (TransformerException e) {

        }
    }

    /**
     * Inserts new dishCategories into existing file.
     *
     * @param dishCategory is added to existing file.
     */
    public static void insertDishCategoriesIntoXmlFile( DishCategory dishCategory, String fileName){
        Document document = getDocumentFromFile(fileName);
        Element rootElement = document.getDocumentElement();

        Element dishCategoryEl = document.createElement(DISH_CATEGORY);
        rootElement.appendChild(dishCategoryEl);

        Attr attrDishCategoryID = document.createAttribute(ID);
        attrDishCategoryID.setValue(String.valueOf(dishCategory.getId()));
        dishCategoryEl.setAttributeNode(attrDishCategoryID);

        Attr attrDishCategoryName = document.createAttribute(NAME);
        attrDishCategoryName.setValue(String.valueOf(dishCategory.getName()));
        dishCategoryEl.setAttributeNode(attrDishCategoryName);

        Attr attrDishCategoryHealthyFood = document.createAttribute(HEALTHY_FOOD);
        attrDishCategoryHealthyFood.setValue(String.valueOf(dishCategory.isHealthyFood()));
        dishCategoryEl.setAttributeNode(attrDishCategoryHealthyFood);
        DOMSource source = new DOMSource(document);
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(fileName);
            transformer.transform(source, result);
        } catch (TransformerException e) {

        }
    }

    /**
     * Deletes given dishes from XML-file information about the dishes is stored.
     *
     * @param dishId is an if of the dish which is deleted from XML-file.
     */
    public static void deleteDishesFromXmlFile(int dishId, String fileName){
        Document document = getDocumentFromFile(fileName);
        NodeList nodes = document.getElementsByTagName(DISH);


        document.getDocumentElement().normalize();
        NodeList dishCategoriesFromXML = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < dishCategoriesFromXML.getLength(); i++) {
            if (dishCategoriesFromXML.item(i).getNodeName().equals(DISH_CATEGORY)) {
                NodeList dishElements = dishCategoriesFromXML.item(i).getChildNodes();
//                for (int j = 0; j < dishElements.getLength(); j++) {
                for (int j = 0; j < 5; j++) {

                    if (dishElements.item(i).getNodeName().equals(DISH)) {
                        System.out.println(dishElements.item(j).getNodeName());
                        for (int k = 0; k < dishElements.item(j).getAttributes().getLength(); k++) {
                            if("id".equals(dishElements.item(j).getAttributes().item(k).getNodeName())
                            && Integer.valueOf(dishElements.item(j).getAttributes().item(k).getNodeValue()) == dishId)
                                System.out.println(Integer.valueOf(dishElements.item(j).getAttributes().item(k).getNodeValue()));
                            return;
                        }
                    }
                }
            }
        }





/*

        for (int i = 0; i < nodes.getLength(); i++) {
            Element dishEl = (Element)nodes.item(i);
            //Element id = (Element)dishEl.getElementsByTagName("name").item(0);
            System.out.println(dishEl.getAttribute("id"));
            if (dishId == Integer.valueOf(dishEl.getAttribute("id"))){
                dishEl.getParentNode().removeChild(dishEl);
            }
            /*
            for (int j = 0; j < dishEl.getAttributes().getLength(); j++) {
                switch (dishEl.getAttributes().item(j).getNodeName()) {

                    case ID:

                        break;
                    case NAME:
                        dishСategory.setName(dishCategoriesFromXML.item(i).getAttributes().item(j).getNodeValue());
                        break;
                    case HEALTHY_FOOD:
                        dishСategory.setHealthyFood(Boolean.parseBoolean(dishCategoriesFromXML.item(i).getAttributes().item(j).getNodeValue()));
                        break;
                    default:
                        LOGGER.error("Unknown attribute was detected during parsing XML");
                }
            }
            */ /*
        }
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            //    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(getDocumentFromFile(fileName));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        */


    }


    /**
     * Deletes given dishes from XML-file information about the dish categories is stored.
     *
     * @param dishCategories are deleted from XML-file.
     */
    public static void deleteDishCategoriesFromXmlFile(List<DishCategory> dishCategories){
    }

    /**
     * Gets a document from the given xml-file.
     *
     * @param fileName is a name of the the file or file path.
     * @return document of the file.
     */
    public static Document getDocumentFromFile(String fileName) {
            //////////////////////////////////////////////////////
        /*
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            dbFactory.setValidating(true);
            try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                dBuilder.setErrorHandler(new ErrorHandler() {
                    @Override
                    public void error(SAXParseException exception) throws SAXException {
                        LOGGER.error(exception);
                        return;
                    }

                    @Override
                    public void fatalError(SAXParseException exception) throws SAXException {
                        LOGGER.error(exception);
                        return;
                    }

                    @Override
                    public void warning(SAXParseException exception) throws SAXException {
                        LOGGER.error(exception);
                        return;
                    }
                });
                */
                //////////////////////////////////////////////////////////
        /*
                return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                LOGGER.error("Getting a document from " + fileName + " was failed.");
            }
        return null;
    */
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
        } catch (SAXException  |IOException | ParserConfigurationException e ) {
            e.printStackTrace();
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
