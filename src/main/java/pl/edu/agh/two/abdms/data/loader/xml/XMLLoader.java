package pl.edu.agh.two.abdms.data.loader.xml;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.two.abdms.data.loader.DataModel;
import org.jdom2.Element;
import org.jdom2.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class XMLLoader {

    @Autowired
    private XMLExtractor xmlExtractor;

    @Autowired
    private SAXBuilder saxBuilder;

    public DataModel load(String pathToXMLSource) {
        try {
            Document document = retrieveDocument(pathToXMLSource);
            Element rootNode = document.getRootElement();
            List<Element> rows = rootNode.getChildren();

            Set<String> columnNames = xmlExtractor.extractColumnNames(rows);
            List<Map<String, String>> values = xmlExtractor.extractValues(rows);

            return DataModel.Create(convertSetToArray(columnNames), values);
        } catch (Exception e) {
            //TODO: Obsluga wyjatkow.
            e.printStackTrace();
        }
        //TODO: Co zwracamy jak poleci wyjatek?
        return null;
    }

    private String[] convertSetToArray(Set<String> columnNames) {
        return columnNames.toArray(new String[columnNames.size()]);
    }

    private Document retrieveDocument(String pathToXMLSource) throws JDOMException, IOException {
        File xmlFile = new File(pathToXMLSource);
        return saxBuilder.build(xmlFile);
    }

}
