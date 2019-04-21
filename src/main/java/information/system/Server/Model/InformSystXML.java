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
//import org.apache.log4j.Logger;

import static java.lang.System.exit;

public class InformSystXML {
    private static final String NAME = "name";
    private static final String DISH_CATEGORY = "dishcategory";
    private static final String PRICE = "price";
    private static final String DISH = "dish";
    private static final String DESCRIPTION = "description";

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
                //создали элемент студент
                Element dishEL = document.createElement(DISH);
                rootElement.appendChild(dishEL);
                //добавили атрибут
                Attr attr1 = document.createAttribute(NAME);
                attr1.setValue(String.valueOf(dish.getName()));
                dishEL.setAttributeNode(attr1);

                Attr attr2 = document.createAttribute(DISH_CATEGORY);
                attr2.setValue(String.valueOf(dish.getDishСategory().getName()));
                dishEL.setAttributeNode(attr2);

                Attr attr3 = document.createAttribute(PRICE);
                attr3.setValue(String.valueOf(dish.getPrice()));
                dishEL.setAttributeNode(attr3);

                Element description = document.createElement(DESCRIPTION);
                description.appendChild(document.createTextNode(dish.getDescription()));
                dishEL.appendChild(description);

            }
            //Теперь запишем контент в XML файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            ///////////////////////////////////////////////
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "rules.dtd");
            ///////////////////////////////////////////////

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileName));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    static public  List<Dish> readXML(String fileName){
        List<Dish> menu = new LinkedList<>();
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            dbFactory.setValidating(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    System.out.println( exception);
                    exit(1);
                }
                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    System.out.println(exception);
                    exit(1);
                }

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    System.out.println(exception);
                    exit(1);
                }
            });
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(DISH);
            Dish[] dishes = new Dish[nList.getLength()];
            NodeList dishesFromXML = doc.getDocumentElement().getChildNodes();
            //iterate students
            for (int i = 0; i < dishesFromXML.getLength(); i++){
                if (dishesFromXML.item(i).getNodeName().equals(DISH)){
                    Dish newDish = new Dish();
                    //adding attributes
                    for (int j = 0; j <dishesFromXML.item(i).getAttributes().getLength() ; j++) {
                        switch(dishesFromXML.item(i).getAttributes().item(j).getNodeName())
                        {
                            case NAME:
                                newDish.setName( dishesFromXML.item(i).getAttributes().item(j).getNodeValue());
                                break;
                            case DISH_CATEGORY:
                                //поиск есть ли уже такая диш категори
                                newDish.setDishСategory(new DishСategory(dishesFromXML.item(i).getAttributes().item(j).getNodeValue()));
                                break;
                            case PRICE:
                                newDish.setPrice(Double.parseDouble(dishesFromXML.item(i).getAttributes().item(j).getNodeValue()));
                                break;
                            default:
                                //throws exception
                                System.out.println("no match");
                        }
                    }
                    //adding subjects
                    NodeList childrenOfStudent = dishesFromXML.item(i).getChildNodes();
                    List<String> subjects = new LinkedList<>();
                    for (int j = 0; j < childrenOfStudent.getLength(); j++) {
                        if (DESCRIPTION.equals(childrenOfStudent.item(j).getNodeName())){
                            newDish.setDescription(childrenOfStudent.item(j).getTextContent());
                        } else {
                            throw new Exception();
                        }
                    }
                    menu.add(newDish);
                    System.out.println(newDish.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }

}
