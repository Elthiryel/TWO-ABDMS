package pl.edu.agh.two.abdms.data.loader.xml;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.jdom2.Element;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class XMLExtractor {

    public Set<String> extractColumnNames(List<Element> rows) {
        Set<String> columnNames = Sets.newHashSet();
        for (Element row : rows) {
            columnNames.addAll(getColumnNamesFromRow(row));
        }
        return columnNames;
    }

    private Set<String> getColumnNamesFromRow(Element row) {
        Set<String> columnNamesFromRow = Sets.newHashSet();

        for (Element elementInRow : row.getChildren()) {
            String columnName = elementInRow.getName();
            columnNamesFromRow.add(columnName);
        }

        return columnNamesFromRow;
    }

    public List<Map<String, String>> extractValues(List<Element> rows) {
        List<Map<String, String>> xmlValues = Lists.newArrayList();
        xmlValues.addAll(rows.stream().map(this::getValuesFromRow).collect(Collectors.toList()));
        return xmlValues;
    }

    private Map<String, String> getValuesFromRow(Element row) {
        Map<String, String> valuesInRow = Maps.newHashMap();
        for (Element elementInRow : row.getChildren()) {
            valuesInRow.put(elementInRow.getName(), elementInRow.getText());
        }
        return valuesInRow;
    }
}
