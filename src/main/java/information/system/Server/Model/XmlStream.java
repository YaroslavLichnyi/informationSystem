package information.system.Server.Model;
        import org.apache.log4j.Logger;
        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.xml.sax.SAXException;

        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.ParserConfigurationException;
        import javax.xml.transform.*;
        import javax.xml.transform.dom.DOMSource;
        import javax.xml.transform.stream.StreamResult;
        import java.io.File;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.Socket;

public class XmlStream {
    private static final Logger LOGGER = Logger.getLogger(XmlStream.class);

    public void output(Document document, Socket socket){
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            OutputStream outputStream = socket.getOutputStream();
            t.transform(new DOMSource(document), new StreamResult(outputStream));
            //System.out.println(outputStream.toString());
        } catch (TransformerException | IOException e){
            LOGGER.error(e);
        }
    }

    public void input (InputStream in){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //    XmlSet xmlSet = new XmlSet(-1);
            Document document;
            try{
                document = builder.parse(in);             //it will test in thread!!!
                document.getDocumentElement().normalize();
                //осоздаём файл с получеными данными
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                try {
                    Transformer transformer = transformerFactory.newTransformer();
                  //  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                   // transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "rules.dtd");
                    System.out.println(document.toString());
                    DOMSource domSource = new DOMSource(document);
                    StreamResult streamResult = new StreamResult(new File("result.xml"));
                    transformer.transform(domSource, streamResult);
                } catch (TransformerConfigurationException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
                //String result ;
                // parsing id of user
       /*         if ((result = readChild(document, ID_USER)) != null) {
                    xmlSet.setIdUser(Long.parseLong(result));
                } */
            } catch (SAXException | IOException e){
                LOGGER.error(e);
            }
        } catch (ParserConfigurationException e){
            LOGGER.error(e);
        }
    }
}



/*
    Чтение из InputStream:
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        XmlSet xmlSet = new XmlSet(-1);
        Document document;
        document = builder.parse(in);                                       //it will test in thread!!!
        document.getDocumentElement().normalize()
        String result;
        // parsing id of user
        if ((result = readChild(document, ID_USER)) != null) {
        xmlSet.setIdUser(Long.parseLong(result));
        }
*/