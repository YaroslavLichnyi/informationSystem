package information.system.Server.Model;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;


public class InformSystXML {
    private static final String NAME = "name";
    private static final String DISH_CATEGORY = "dish-category";
    private static final String PRICE = "price";
    private static final String DISH = "dish";
    private static final String DESCRIPTION = "description";
    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private static final String DISH_CATEGORY_ID = "dish-category-id";
    private static final String HEALTHY_FOOD = "healthy-food";
    private static final Logger LOGGER = Logger.getLogger(InformSystXML.class);


    /**
     * Writes data to a file considering the variable values of each of the dishes.
     * @param dishСategories are objects that are written.
     * @param fileName is a file location.
     */
    static public void writeXML(List<DishCategory> dishСategories, String fileName){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("restaurant");
            document.appendChild(rootElement);
            for (DishCategory dishСategory : dishСategories) {
                Element dishCategoryEl = document.createElement(DISH_CATEGORY);
                rootElement.appendChild(dishCategoryEl);

                Attr attrDishCategoryID = document.createAttribute(ID);
                attrDishCategoryID.setValue(String.valueOf(dishСategory.getId()));
                dishCategoryEl.setAttributeNode(attrDishCategoryID);

                Attr attrDishCategoryName = document.createAttribute(NAME);
                attrDishCategoryName.setValue(String.valueOf(dishСategory.getName()));
                dishCategoryEl.setAttributeNode(attrDishCategoryName);

                Attr attrDishCategoryHealthyFood = document.createAttribute(HEALTHY_FOOD);
                attrDishCategoryHealthyFood.setValue(String.valueOf(dishСategory.isHealthyFood()));
                dishCategoryEl.setAttributeNode(attrDishCategoryHealthyFood);

                for ( Dish dish : dishСategory.getDishes()) {
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
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "rules.dtd");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileName));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            LOGGER.error(e);
        }

    }

    /**
     * Writes data to a file considering the variable values of each of the dishes.
     * @param fileName is a file location.
     * @return List of dishes the were read from file.
     */
    static public LinkedList<DishCategory> getMenu(String fileName){
        Document doc = XmlSet.getDocumentFromFile(fileName);
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
     * Writes list of users into xml-file.
     * @param users is written  into document.
     * @param fileName is a path to resource, where information is stored.
     */
    static public void writeAdmins(List<User> users, String fileName){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("users");
            document.appendChild(rootElement);
            for (User user : users) {
                Element dishEL = document.createElement(USER);
                rootElement.appendChild(dishEL);

                Attr attr1 = document.createAttribute(NAME);
                attr1.setValue(String.valueOf(user.getName()));
                dishEL.setAttributeNode(attr1);

                Attr attr2 = document.createAttribute(LOGIN);
                attr2.setValue(String.valueOf(user.getLogin()));
                dishEL.setAttributeNode(attr2);

                Attr attr3 = document.createAttribute(PASSWORD);
                attr3.setValue(String.valueOf(user.getPassword()));
                dishEL.setAttributeNode(attr3);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "rules4admins.dtd");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileName));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            LOGGER.error(e);
        }
    }


    /**
     * Read list of users from xml-file.
     * @param fileName  is a path to resource, where information is stored.
     * @return list of users, what are stored in xml-file.
     */
    static public  LinkedList<User> readAdmins(String fileName){
        LinkedList<User> admins = new LinkedList<>();
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            dbFactory.setValidating(true);
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
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList adminsFromXML = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < adminsFromXML.getLength(); i++){
                if (adminsFromXML.item(i).getNodeName().equals(USER)){
                    User newUser = new User();
                    for (int j = 0; j <adminsFromXML.item(i).getAttributes().getLength() ; j++) {
                        switch(adminsFromXML.item(i).getAttributes().item(j).getNodeName())
                        {
                            case NAME:
                                newUser.setName(adminsFromXML.item(i).getAttributes().item(j).getNodeValue());
                                break;
                            case LOGIN:
                                newUser.setLogin(adminsFromXML.item(i).getAttributes().item(j).getNodeValue());
                                break;
                            case PASSWORD:
                                newUser.setPassword(adminsFromXML.item(i).getAttributes().item(j).getNodeValue());
                                break;
                            default:
                                LOGGER.error("Unknown attribute was detected during parsing XML");
                        }
                    }
                    admins.add(newUser);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Unknown attribute was detected during parsing XML");
        }
        return admins;
    }

}
