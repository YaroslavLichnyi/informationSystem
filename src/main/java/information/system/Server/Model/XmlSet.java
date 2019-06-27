package information.system.Server.Model;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
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
    private static final String USER = "user";
    private static final String ADMIN = "admin";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String HEALTHY_FOOD = "healthy-food";
    private static final String ID = "id";
    private static final String DISH_CATEGORY_ID = "dish-category-id";
    private static final String ROOT_ELEMENT = "message";
    private static final String COMMAND = "command";
    private Document document;

    public XmlSet() {
        cleanOutDocument();
    }

    /**
     * Cleans out the document and put the root in it.
     */
    public  void cleanOutDocument(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            Element rootElement = document.createElement(ROOT_ELEMENT);
            document.appendChild(rootElement);
        } catch (ParserConfigurationException e) {
            LOGGER.error("Exception while document creating", e);
        }
    }

    /**
     * Converts objects of type User into xml format and puts them into a {@link XmlSet#document}.
     *
     * @param users are added into {@link XmlSet#document} as an element in xml-file.
     */
    public void setUsersToDocument(List<User> users) {
     Element root = document.getDocumentElement();
     Element rootAdmins = document.createElement("users");
     root.appendChild(rootAdmins);
     for (User user : users) {
         Element dishEL = document.createElement(USER);
         rootAdmins.appendChild(dishEL);

         Attr attrId = document.createAttribute(ID);
         attrId.setValue(String.valueOf(user.getId()));
         dishEL.setAttributeNode(attrId);

         Attr attrAdmin = document.createAttribute(ADMIN);
         attrAdmin.setValue(String.valueOf(user.isAdmin()));
         dishEL.setAttributeNode(attrAdmin);

         Attr attrLogin = document.createAttribute(LOGIN);
         attrLogin.setValue(user.getLogin());
         dishEL.setAttributeNode(attrLogin);

         Attr attrPassword = document.createAttribute(PASSWORD);
         attrPassword.setValue(user.getPassword());
         dishEL.setAttributeNode(attrPassword);

         Attr attrName = document.createAttribute(NAME);
         attrName.setValue(user.getName());
         dishEL.setAttributeNode(attrName);
     }
    }

    /**
     * Gets users from giving document.
     *
     * @param doc is a resource where users are getted from.
     * @return list of users
     */
    public static List<User> getUsersFromDocument(Document doc) {
        List<User> users = new LinkedList<>();
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("user");
        for (int i = 0; i < nodes.getLength(); i++) {
            User user = new User();
            for (int j = 0; j <  nodes.item(i).getAttributes().getLength(); j++) {
                switch (nodes.item(i).getAttributes().item(j).getNodeName()) {
                    case ID:
                        user.setId(Integer.valueOf(nodes.item(i).getAttributes().item(j).getNodeValue()));
                        break;
                    case ADMIN:
                        user.setAdmin(Boolean.parseBoolean(nodes.item(i).getAttributes().item(j).getNodeValue()));
                        break;
                    case NAME:
                        user.setName(nodes.item(i).getAttributes().item(j).getNodeValue());
                        break;
                    case LOGIN:
                        user.setLogin(nodes.item(i).getAttributes().item(j).getNodeValue());
                        break;
                    case PASSWORD:
                        user.setPassword(nodes.item(i).getAttributes().item(j).getNodeValue());
                        break;
                    default:
                        LOGGER.error("Unknown attribute was detected during parsing XML");
                }
            }
            users.add(user);
        }
        return users;
    }

    /**
     * Sets command into a {@link XmlSet#document}.
     *
     * @param command is added into {@link XmlSet#document} as an element in xml-file.
     */
    public void setCommandToDocument(String command) {
        deleteCommandFromDocument(document);
        Element root = document.getDocumentElement();
        Element commandEl = document.createElement(COMMAND);
        commandEl.setTextContent(command);
        root.appendChild(commandEl);
    }

    /**
     * Gets command from a {@link XmlSet#document}.
     *
     * @param doc is a resource where command is got from.
     * @return command from a {@link XmlSet#document}.
     */
    public static String getCommandFromDocument(Document doc) {
        NodeList nodes = doc.getElementsByTagName(COMMAND);
        if (nodes.getLength() > 1){
            LOGGER.error("XML-file cannot contain more than 1 command");
        }
        return nodes.item(0).getTextContent();
    }

    /**
     * Deletes command from {@link XmlSet#document}.
     *
     * @param doc s a resource where command is deleted from.
     */
    public void deleteCommandFromDocument(Document doc) {
        NodeList nodes = doc.getElementsByTagName(COMMAND);
        for (int i = 0; i < nodes.getLength(); i++) {
            nodes.item(i).getParentNode().removeChild( nodes.item(i));
        }
    }

    /**
     * Parses given document and extracts dishes from it.
     *
     * @param doc is a resource from which dishes come from.
     * @return dishes from {@link XmlSet#document}.
     */
    public static List<Dish> getDishesFrom(Document doc){
        NodeList nodes = doc.getElementsByTagName(DISH);
        List<Dish> dishes = new LinkedList<>();
        for (int j = 0; j < nodes.getLength(); j++) {
            if (nodes.item(j).getNodeName().equals(DISH)) {
                Dish newDish = new Dish();
                for (int k = 0; k < nodes.item(j).getAttributes().getLength(); k++) {
                    switch (nodes.item(j).getAttributes().item(k).getNodeName()) {
                        case ID:
                            newDish.setId(Integer.valueOf(nodes.item(j).getAttributes().item(k).getNodeValue()));
                            break;
                        case DISH_CATEGORY_ID:
                            newDish.setDishCategoryId(Integer.valueOf(nodes.item(j).getAttributes().item(k).getNodeValue()));
                            break;
                        case NAME:
                            newDish.setName(nodes.item(j).getAttributes().item(k).getNodeValue());
                            break;
                        case PRICE:
                            newDish.setPrice(Double.parseDouble(nodes.item(j).getAttributes().item(k).getNodeValue()));
                            break;
                        default:
                            LOGGER.error("Unknown attribute was detected during parsing XML");
                    }
                }
                NodeList childrenOfDishNode = nodes.item(j).getChildNodes();
                // for (int n = 0; n < childrenOfDishNode.getLength(); n++) {
                if (DESCRIPTION.equals(childrenOfDishNode.item(0).getNodeName())) {
                    newDish.setDescription(childrenOfDishNode.item(0).getTextContent());
                } else {
                    LOGGER.error("Problem with reading XML-file");
                }
                dishes.add(newDish);
            }
        }
        return dishes;
    }

    /**
     * Converts objects of type Dish into xml format and puts them into a {@link XmlSet#document}.
     *
     * @param dishes are added into {@link XmlSet#document}.
     */
    public  void setDishesToDocument(List<Dish> dishes){
        Element root = document.getDocumentElement();
        Element rootDishes = document.createElement("dishes");
        root.appendChild(rootDishes);
        for (Dish dish : dishes) {
            Element dishEl = document.createElement(DISH);
            rootDishes.appendChild(dishEl);

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
        }
    }

    /**
     * Converts objects of type DishCategory into xml format and puts them into a {@link XmlSet#document}.
     *
     * @param dishCategories are added into {@link XmlSet#document}.
     */
    public void setDishCategoriesToDocument(List <DishCategory> dishCategories){
        Element root = document.getDocumentElement();
        Element rootDishes = document.createElement("dish-categories");
        root.appendChild(rootDishes);
        for (DishCategory dishCategory : dishCategories) {
            Element dishCategoryEl = document.createElement(DISH_CATEGORY);
            rootDishes.appendChild(dishCategoryEl);

            Attr attrDishCategoryID = document.createAttribute(ID);
            attrDishCategoryID.setValue(String.valueOf(dishCategory.getId()));
            dishCategoryEl.setAttributeNode(attrDishCategoryID);

            Attr attrDishCategoryName = document.createAttribute(NAME);
            attrDishCategoryName.setValue(String.valueOf(dishCategory.getName()));
            dishCategoryEl.setAttributeNode(attrDishCategoryName);

            Attr attrDishCategoryHealthyFood = document.createAttribute(HEALTHY_FOOD);
            attrDishCategoryHealthyFood.setValue(String.valueOf(dishCategory.isHealthyFood()));
            dishCategoryEl.setAttributeNode(attrDishCategoryHealthyFood);
        }
    }

    /**
     * Parses given document and extracts dish categories from it.
     *
     * @param doc is a resource from which dish categories come from.
     * @return dishes from {@link XmlSet#document}.
     */
    public static List<DishCategory> getDishCategoriesFrom(Document doc){
        NodeList nodes = doc.getElementsByTagName(DISH_CATEGORY);
        List<DishCategory> dishCategories = new LinkedList<>();
        for (int j = 0; j < nodes.getLength(); j++) {
            if (nodes.item(j).getNodeName().equals(DISH_CATEGORY)) {
                DishCategory newDishCategory = new DishCategory();
                for (int k = 0; k < nodes.item(j).getAttributes().getLength(); k++) {
                    switch (nodes.item(j).getAttributes().item(k).getNodeName()) {
                        case ID:
                            newDishCategory.setId(Integer.valueOf(nodes.item(j).getAttributes().item(k).getNodeValue()));
                            break;
                        case NAME:
                            newDishCategory.setName(nodes.item(j).getAttributes().item(k).getNodeValue());
                            break;
                        case HEALTHY_FOOD:
                            newDishCategory.setHealthyFood(Boolean.parseBoolean(nodes.item(j).getAttributes().item(k).getNodeValue()));
                            break;
                        default:
                            LOGGER.error("Unknown attribute was detected during parsing XML");
                    }
                }
                dishCategories.add(newDishCategory);
            }
        }
        return dishCategories;
    }


    /**
     * Sets given list of dish categories and list of dishes, which dish categories contains to document.
     * @param menu is a list data of which is set in document.
     */
    public void setMenuToDocument(List<DishCategory> menu) {
        Element rootMenu = document.createElement("menu");
        for (DishCategory dishCategory: menu) {
            Element dishCategoryEl = document.createElement(DISH_CATEGORY);
            rootMenu.appendChild(dishCategoryEl);

            Attr attrDishCategoryID = document.createAttribute(ID);
            attrDishCategoryID.setValue(String.valueOf(dishCategory.getId()));
            dishCategoryEl.setAttributeNode(attrDishCategoryID);

            Attr attrDishCategoryName = document.createAttribute(NAME);
            attrDishCategoryName.setValue(String.valueOf(dishCategory.getName()));
            dishCategoryEl.setAttributeNode(attrDishCategoryName);

            Attr attrDishCategoryHealthyFood = document.createAttribute(HEALTHY_FOOD);
            attrDishCategoryHealthyFood.setValue(String.valueOf(dishCategory.isHealthyFood()));
            dishCategoryEl.setAttributeNode(attrDishCategoryHealthyFood);

            for ( Dish dish : dishCategory.getDishes()) {
                Element dishEl = document.createElement(DISH);
                dishCategoryEl.appendChild(dishEl);

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
            }
        }
    }

    /**
     * Parses given document and extracts dish categories <b>and</b> dishes from it.
     *
     * @param doc
     * @return
     */
    public static List<DishCategory> getMenuFromDocument(Document doc) {
        NodeList nodes = doc.getElementsByTagName(DISH_CATEGORY);
        LinkedList<DishCategory> dishCategories = new LinkedList();
        for (int i = 0; i < nodes.getLength() ; i++) {
            if (DISH_CATEGORY.equals(nodes.item(i).getNodeName())) {
                DishCategory newDishCategory = new DishCategory();
                for (int k = 0; k < nodes.item(i).getAttributes().getLength(); k++) {
                    switch (nodes.item(i).getAttributes().item(k).getNodeName()) {
                        case ID:
                            newDishCategory.setId(Integer.valueOf(nodes.item(i).getAttributes().item(k).getNodeValue()));
                            break;
                        case NAME:
                            newDishCategory.setName(nodes.item(i).getAttributes().item(k).getNodeValue());
                            break;
                        case HEALTHY_FOOD:
                            newDishCategory.setHealthyFood(Boolean.parseBoolean(nodes.item(i).getAttributes().item(k).getNodeValue()));
                            break;
                        default:
                            LOGGER.error("Unknown attribute was detected during parsing XML");
                    }
                }
                NodeList dishes = nodes.item(i).getChildNodes();
                for (int j = 0; j < dishes.getLength(); j++) {
                    if (dishes.item(j).getNodeName().equals(DISH)) {
                        Dish newDish = new Dish();
                        for (int k = 0; k < dishes.item(j).getAttributes().getLength(); k++) {
                            switch (dishes.item(j).getAttributes().item(k).getNodeName()) {
                                case ID:
                                    newDish.setId(Integer.valueOf(dishes.item(j).getAttributes().item(k).getNodeValue()));
                                    break;
                                case DISH_CATEGORY_ID:
                                    newDish.setDishCategoryId(Integer.valueOf(dishes.item(j).getAttributes().item(k).getNodeValue()));
                                    break;
                                case NAME:
                                    newDish.setName(dishes.item(j).getAttributes().item(k).getNodeValue());
                                    break;
                                case PRICE:
                                    newDish.setPrice(Double.parseDouble(dishes.item(j).getAttributes().item(k).getNodeValue()));
                                    break;
                                default:
                                    LOGGER.error("Unknown attribute was detected during parsing XML");
                            }
                        }
                        NodeList childrenOfDishNode = dishes.item(j).getChildNodes();
                        for (int k = 0; k < childrenOfDishNode.getLength(); k++) {
                            if (DESCRIPTION.equals(childrenOfDishNode.item(k).getNodeName())) {
                                newDish.setDescription(childrenOfDishNode.item(k).getTextContent());
                            }
                        }
                        newDishCategory.addDish(newDish);
                    }
                }
                dishCategories.add(newDishCategory);
            }
        }
        return dishCategories;
    }


    /**
     * Gets a document from the given xml-file.
     *
     * @param fileName is a name of the the file or file path.
     * @return document of the file.
     */
    public static Document getDocumentFromFile(String fileName) {
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

    /**
     * Gets {@link XmlSet#document}.
     *
     * @return {@link XmlSet#document}.
     */
    public Document getDocument() {
        return document;
    }
}
