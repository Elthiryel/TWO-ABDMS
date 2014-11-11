package pl.edu.agh.two.abdms.data.loader.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRule;

import pl.edu.agh.two.abdms.data.loader.DataModel;

public class XMLLoaderTest {
	
	
	private final String givenNameColumnName = "Name";
	private final String givenAgeColumnName = "Age";
	private final String givenSurnameColumnName = "Surname";
	private final List<Element> givenRows = new ArrayList<Element>();
	private final String givenPathToXML = "MockedPath";
	
	@Rule
	public MockitoJUnitRule mockitoJUnitRule = new MockitoJUnitRule(this);
	
	@Mock
	private XMLExtractor xmlExtractor;	
	
	@Mock
	private SAXBuilder saxBuilder;
	
	@InjectMocks
	private XMLLoader xmlLoader;
	
	@Before
	public void initSAXBuilderMock() throws Exception {
		Document document = Mockito.mock(Document.class);
		Element rootElement = Mockito.mock(Element.class);
		Mockito.when(saxBuilder.build(givenPathToXML)).thenReturn(document);
		Mockito.when(document.getRootElement()).thenReturn(rootElement);
		Mockito.when(rootElement.getChildren()).thenReturn(givenRows);
	}
	
	@Test
	public void shouldReturnValidDataModel() {
		//given
		List<String> expectedColumnsNames = createExpectedColumnsNames();
		List<Map<String, String>> expectedValues = createExpectedValues();
		DataModel expectedDataModel = DataModel.Create(TestUtils.convertToArray(expectedColumnsNames), expectedValues);
		Mockito.when(xmlExtractor.extractColumnNames(givenRows)).thenReturn(expectedColumnsNames);
		Mockito.when(xmlExtractor.extractValues(givenRows)).thenReturn(expectedValues);
		
		//when
		DataModel dataModel = xmlLoader.load(givenPathToXML);
		
		//then
		Assert.assertNotNull(dataModel);
		Assert.assertEquals(expectedDataModel.getColumnValues(), dataModel.getColumnValues());
		Assert.assertEquals(expectedDataModel.getValues(), dataModel.getValues());
	}

	private List<String> createExpectedColumnsNames() {
		List<String> columnsNames = new ArrayList<String>();
		columnsNames.add(givenNameColumnName);
		columnsNames.add(givenAgeColumnName);
		columnsNames.add(givenSurnameColumnName);
		return columnsNames;
	}

	private List<Map<String, String>> createExpectedValues() {
		List<Map<String, String>> values = new ArrayList<Map<String,String>>();
		Map<String, String> columnValueMap = new HashMap<String, String>();
		columnValueMap.put(givenNameColumnName, "Tom");
		columnValueMap.put(givenAgeColumnName, "30");
		columnValueMap.put(givenSurnameColumnName, "Smith");
		values.add(columnValueMap);
		return values;
	}
}
