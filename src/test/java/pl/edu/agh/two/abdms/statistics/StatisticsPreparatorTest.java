package pl.edu.agh.two.abdms.statistics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import junit.framework.TestCase;

public class StatisticsPreparatorTest extends TestCase {

	public void testStatisticsPreparator() {
		DataModel dataModel = prepareDataModel();
		StatisticsPreparator preparator = new StatisticsPreparator(dataModel);
		assertEquals("Averages computed for wrong number of columns.", 1, preparator.getAverages().size());
		assertEquals("Medians computed for wrong number of columns.", 1, preparator.getMedians().size());
		assertEquals("Skewnesses computed for wrong number of columns.", 1, preparator.getSkewnesses().size());
		assertEquals("Unique value counts computed for wrong number of columns.", 1, preparator.getUniqueValueCounts().size());
		assertEquals("Wrong average value computed.", 2.5, preparator.getAverage("colnums"));
		assertEquals("Wrong median value computed.", 2.5, preparator.getMedian("colnums"));
		assertEquals("Wrong skewness value computed.", 0.0, preparator.getSkewness("colnums"));
		assertEquals("Wrong unique value count computed.", 2, preparator.getUniqueValueCount("coltext").intValue());
	}
	
	private DataModel prepareDataModel() {
		String[] columnValues = { "coltext", "colnums" };
		Map<String, String> value1 = new HashMap<String, String>();
		value1.put("coltext", "TEXT1");
		value1.put("colnums", "1");
		Map<String, String> value2 = new HashMap<String, String>();
		value2.put("coltext", "TEXT1");
		value2.put("colnums", "2.5");
		Map<String, String> value3 = new HashMap<String, String>();
		value3.put("coltext", "TEXT2");
		value3.put("colnums", "4");
		List<Map<String, String>> valueList = new LinkedList<Map<String,String>>();
		valueList.add(value1);
		valueList.add(value2);
		valueList.add(value3);
		DataModel dataModel = DataModel.Create(columnValues, valueList);
		return dataModel;
	}
}
