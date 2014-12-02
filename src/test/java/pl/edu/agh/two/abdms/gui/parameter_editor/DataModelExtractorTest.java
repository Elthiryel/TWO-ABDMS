package pl.edu.agh.two.abdms.gui.parameter_editor;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRule;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.data.loader.xml.TestUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class DataModelExtractorTest {
	 private final String givenNameColumnName = "Name";
	    private final String givenAgeColumnName = "Age";
	    private final String givenSurnameColumnName = "Surname";
	    private final List<Element> givenRows = Lists.newArrayList();
	@Test
	public void shouldCreateCorrectArray() {
		//	given
		  Set<String> expectedColumnsNames = createExpectedColumnsNames();
		  List<Map<String, String>> expectedValues = createExpectedValues();
	      DataModel expectedDataModel = DataModel.Create(TestUtils.convertToArray(expectedColumnsNames), expectedValues);
	      // when
	      String[][] expectedArray = DataModelExtractor.parseDataModel(expectedDataModel);
	      
	      //then
	      assertEquals("Tom", expectedArray[0][2]);	
	      assertEquals("Name", expectedDataModel.getColumnValues()[2]);
	      
	}
	private Set<String> createExpectedColumnsNames() {
        return Sets.newHashSet(givenNameColumnName, givenAgeColumnName, givenSurnameColumnName);
    }

    private List<Map<String, String>> createExpectedValues() {
        List<Map<String, String>> values = Lists.newArrayList();
        Map<String, String> columnValueMap = Maps.newHashMap();
        columnValueMap.put(givenNameColumnName, "Tom");
        columnValueMap.put(givenAgeColumnName, "30");
        columnValueMap.put(givenSurnameColumnName, "Smith");
        values.add(columnValueMap);
        return values;
    }

}


   
