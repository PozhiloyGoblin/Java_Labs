import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMExample {
    static List<ValCars> values = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("src\\text.xml"));
        NodeList elements = doc.getElementsByTagName("Valute");
        for (int i = 0; i < elements.getLength(); i++) {
            if (elements.item(i) instanceof Element) {
                String id = elements.item(i).getAttributes().getNamedItem("ID").getNodeValue();
                System.out.println("ID " + id);
                NodeList childs = elements.item(i).getChildNodes();
                List<String> mas = new ArrayList<>();
                for (int j = 0; j < childs.getLength(); j++) {
                    if (childs.item(j) instanceof Element) {
                        mas.add(childs.item(j).getTextContent());
                        System.out.println("\t" + childs.item(j).getTextContent());
                    }
                }
                setValues(mas, id);
                mas.clear();
            }
        }
        for (var s : values) {
            System.out.println("List: " + s.toString());
        }
    }

    private static void setValues(List<String> mas, String id) {
        values.add(new ValCars(id,
                Integer.parseInt(mas.get(0)),
                mas.get(1), Integer.parseInt(mas.get(2)),
                mas.get(3),
                Double.parseDouble(mas.get(4).replace(",","."))));
    }
}
