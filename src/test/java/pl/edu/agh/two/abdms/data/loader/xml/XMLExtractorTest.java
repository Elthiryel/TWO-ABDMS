package pl.edu.agh.two.abdms.data.loader.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.jdom.Element;
import org.junit.Test;
import org.mockito.Mockito;

public class XMLExtractorTest {

	private final XMLExtractor XMLExtractor = new XMLExtractor();

	@Test
	public void shoulReturnColumnsNames() {
		// given
		List<Element> givenRows = new ArrayList<Element>();
		Element row = Mockito.mock(Element.class);
		List<Element> valuesInRow = new ArrayList<Element>();

		Element nameColumn = Mockito.mock(Element.class);
		Element surnameColumn = Mockito.mock(Element.class);
		Element ageColumn = Mockito.mock(Element.class);

		String expectedNameColumnName = "Imie";
		String expectedAgeColumnName = "Wiek";
		String expectedSurnameColumnName = "Nazwisko";

		Mockito.when(nameColumn.getName()).thenReturn(expectedNameColumnName);
		Mockito.when(surnameColumn.getName()).thenReturn(expectedSurnameColumnName);
		Mockito.when(ageColumn.getName()).thenReturn(expectedAgeColumnName);
		Mockito.when(row.getChildren()).thenReturn(valuesInRow);

		valuesInRow.add(nameColumn);
		valuesInRow.add(surnameColumn);
		valuesInRow.add(ageColumn);

		givenRows.add(row);

		// when
		List<String> columnNames = XMLExtractor.extractColumnNames(givenRows);

		// then
		Assert.assertTrue(columnNames.equals(Arrays.asList(expectedNameColumnName, expectedSurnameColumnName,
				expectedAgeColumnName)));
	}
}
