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
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;


public class InformSystXML {
    private static final String NAME = "name";
    private static final String DISH_CATEGORY = "dishcategory";
    private static final String PRICE = "price";
    private static final String DISH = "dish";
    private static final String DESCRIPTION = "description";
    private static final String ADMIN = "admin";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final Logger LOGGER = Logger.getLogger(InformSystXML.class);


    /**
     * Writes data to a file considering the variable values of each of the dishes.
     * @param menu are objects that are written.
     * @param fileName is a file location.
     */
    static public void writeXML(List<Dish> menu, String fileName){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("restaurant");
            document.appendChild(rootElement);
            for (Dish dish : menu) {
                Element dishEL = document.createElement(DISH);
                rootElement.appendChild(dishEL);

                Attr attr1 = document.createAttribute(NAME);
                attr1.setValue(String.valueOf(dish.getName()));
                dishEL.setAttributeNode(attr1);

                Attr attr3 = document.createAttribute(PRICE);
                attr3.setValue(String.valueOf(dish.getPrice()));
                dishEL.setAttributeNode(attr3);

                Element description = document.createElement(DESCRIPTION);
                description.appendChild(document.createTextNode(dish.getDescription()));
                dishEL.appendChild(description);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "rules.dtd");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileName));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        } catch (TransformerConfigurationException e) {
            LOGGER.error(e);
        } catch (TransformerException e) {
            LOGGER.error(e);
        }

    }

    /**
     * Writes data to a file considering the variable values of each of the dishes.
     * @param fileName is a file location.
     * @return List of dishes the were read from file.
     */
    static public  LinkedList<Dish> readXML(String fileName)  {
        LinkedList<Dish> menu = new LinkedList<>();
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
            //NodeList nList = doc.getElementsByTagName(DISH);
            //Dish[] dishes = new Dish[nList.getLength()];
            NodeList dishesFromXML = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < dishesFromXML.getLength(); i++){
                if (dishesFromXML.item(i).getNodeName().equals(DISH)){
                    Dish newDish = new Dish();
                    for (int j = 0; j <dishesFromXML.item(i).getAttributes().getLength() ; j++) {
                        switch(dishesFromXML.item(i).getAttributes().item(j).getNodeName())
                        {
                            case NAME:
                                newDish.setName( dishesFromXML.item(i).getAttributes().item(j).getNodeValue());
                                break;
                            case PRICE:
                                newDish.setPrice(Double.parseDouble(dishesFromXML.item(i).getAttributes().item(j).getNodeValue()));
                                break;
                            default:
                                LOGGER.error("Unknown attribute was detected during parsing XML");
                        }
                    }
                    NodeList childrenOfStudent = dishesFromXML.item(i).getChildNodes();
                    List<String> subjects = new LinkedList<>();
                    for (int j = 0; j < childrenOfStudent.getLength(); j++) {
                        if (DESCRIPTION.equals(childrenOfStudent.item(j).getNodeName())){
                            newDish.setDescription(childrenOfStudent.item(j).getTextContent());
                        } else {
                            LOGGER.error("Problem with reading XML-file");
                        }
                    }
                    menu.add(newDish);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Unknown attribute was detected during parsing XML");
        }
        return menu;
    }

    static public void writeAdmins(List<Admin> admins, String fileName){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("admins");
            document.appendChild(rootElement);
            for (Admin admin : admins) {
                Element dishEL = document.createElement(ADMIN);
                rootElement.appendChild(dishEL);

                Attr attr1 = document.createAttribute(NAME);
                attr1.setValue(String.valueOf(admin.getName()));
                dishEL.setAttributeNode(attr1);

                Attr attr2 = document.createAttribute(LOGIN);
                attr2.setValue(String.valueOf(admin.getLogin()));
                dishEL.setAttributeNode(attr2);

                Attr attr3 = document.createAttribute(PASSWORD);
                attr3.setValue(String.valueOf(admin.getPassword()));
                dishEL.setAttributeNode(attr3);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "rules4admins.dtd");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileName));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        } catch (TransformerConfigurationException e) {
            LOGGER.error(e);
        } catch (TransformerException e) {
            LOGGER.error(e);
        }

    }

    static public  LinkedList<Admin> readAdmins(String fileName){
        LinkedList<Admin> admins = new LinkedList<>();
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
                if (adminsFromXML.item(i).getNodeName().equals(ADMIN)){
                    Admin newAdmin = new Admin();
                    for (int j = 0; j <adminsFromXML.item(i).getAttributes().getLength() ; j++) {
                        switch(adminsFromXML.item(i).getAttributes().item(j).getNodeName())
                        {
                            case NAME:
                                newAdmin.setName(adminsFromXML.item(i).getAttributes().item(j).getNodeValue());
                                break;
                            case LOGIN:
                                newAdmin.setLogin(adminsFromXML.item(i).getAttributes().item(j).getNodeValue());
                                break;
                            case PASSWORD:
                                newAdmin.setPassword(adminsFromXML.item(i).getAttributes().item(j).getNodeValue());
                                break;
                            default:
                                LOGGER.error("Unknown attribute was detected during parsing XML");
                        }
                    }
                    admins.add(newAdmin);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Unknown attribute was detected during parsing XML");
        }
        return admins;
    }


}
