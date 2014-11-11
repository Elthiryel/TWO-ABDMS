package pl.edu.agh.two.abdms.data.loader.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import junit.framework.Assert;

import org.jdom.Element;
import org.junit.Test;
import org.mockito.Mockito;

public class XMLExtractorTest {

	private final XMLExtractor xmlExtractor = new XMLExtractor();
	private final String expectedNameColumnName = "Imie";
	private final String expectedAgeColumnName = "Wiek";
	private final String expectedSurnameColumnName = "Nazwisko";

	@Test
	public void shoulReturnColumnsNames() {
		// given
		List<Element> givenRows = new ArrayList<Element>();
		Element firstRow = createRowForColumns(expectedAgeColumnName, expectedSurnameColumnName);
		Element secondRow = createRowForColumns(expectedNameColumnName, expectedAgeColumnName,
				expectedSurnameColumnName);
		givenRows.add(firstRow);
		givenRows.add(secondRow);

		// when
		List<String> columnNames = xmlExtractor.extractColumnNames(givenRows);

		// then
		Assert.assertTrue(Arrays.asList(expectedNameColumnName, expectedSurnameColumnName,
				expectedAgeColumnName).equals(columnNames));
	}

	@Test
	public void shouldReturnValues() {
		// given
		Map<String, String> expectedValuesInRow = createColumnValueMap("Tom", "30", "Smith");
		List<Element> columnsWithValues = createColumns((x) -> createColumnWithValues(x, expectedValuesInRow.get(x)),
				TestUtils.convertToArray(expectedValuesInRow.keySet()));
		Element row = createRowForColumns(columnsWithValues);
		List<Element> givenRows = new ArrayList<Element>();
		givenRows.add(row);

		// when
		List<Map<String, String>> values = xmlExtractor.extractValues(givenRows);

		// then
		List<Map<String, String>> expectedValues = new ArrayList<Map<String, String>>();
		expectedValues.add(expectedValuesInRow);
		Assert.assertTrue(expectedValues.equals(values));
	}

	private Map<String, String> createColumnValueMap(String nameValue, String ageValue, String surnameValue) {
		Map<String, String> columnValueMap = new HashMap<String, String>();
		columnValueMap.put(expectedNameColumnName, nameValue);
		columnValueMap.put(expectedAgeColumnName, ageValue);
		columnValueMap.put(expectedSurnameColumnName, surnameValue);
		return columnValueMap;
	}

	private Element createRowForColumns(String... columns) {
		List<Element> columnsInRow = createColumns((x) -> createColumn(x), columns);
		return createRowForColumns(columnsInRow);
	}

	private Element createRowForColumns(List<Element> columns) {
		Element row = Mockito.mock(Element.class);
		Mockito.when(row.getChildren()).thenReturn(columns);
		return row;
	}

	private List<Element> createColumns(Function<String, Element> createColumnFunction, String... columnsName) {
		List<Element> columns = new ArrayList<Element>();
		for (String columnName : columnsName)
			columns.add(createColumnFunction.apply(columnName));
		return columns;
	}

	private Element createColumn(String columnName) {
		Element columnElement = Mockito.mock(Element.class);
		Mockito.when(columnElement.getName()).thenReturn(columnName);
		return columnElement;
	}

	private Element createColumnWithValues(String columnName, String value) {
		Element columnElement = Mockito.mock(Element.class);
		Mockito.when(columnElement.getName()).thenReturn(columnName);
		Mockito.when(columnElement.getText()).thenReturn(value);
		return columnElement;
	}
}
