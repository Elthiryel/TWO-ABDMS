package pl.edu.agh.two.abdms.data.parsing.test;

import java.util.Map;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import junit.framework.TestCase;

public abstract class AbstractDataParserTest extends TestCase {
	
	protected DataModel mAcquiredModel;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mAcquiredModel = null;
	}
	
	protected void thenReceivedObjectIsNotNull() {
		assertNotNull(mAcquiredModel);
	}
	
	protected void thenCorrectColumnNamesHaveBeenObtained() {
		assertEquals("First Column", mAcquiredModel.getColumnValues()[0]);
		assertEquals("Second Column", mAcquiredModel.getColumnValues()[1]);
		assertEquals("Third Column", mAcquiredModel.getColumnValues()[2]);
		assertEquals("Fourth column", mAcquiredModel.getColumnValues()[3]);
	}
	
	protected void thenAllTheRowsValuesHaveBeenObtained() {
		
		Map<String, String> firstRow = mAcquiredModel.getRow(0);
		
		assertEquals("1", firstRow.get("First Column"));
		assertEquals("1", firstRow.get("Second Column"));
		assertEquals("1", firstRow.get("Third Column"));
		assertEquals("1", firstRow.get("Fourth column"));
		
		firstRow = mAcquiredModel.getRow(1);
		assertEquals("2", firstRow.get("First Column"));
		assertEquals("2", firstRow.get("Second Column"));
		assertEquals("2", firstRow.get("Third Column"));
		assertEquals("2", firstRow.get("Fourth column"));
		
		firstRow = mAcquiredModel.getRow(2);
		assertEquals("3", firstRow.get("First Column"));
		assertEquals("4", firstRow.get("Second Column"));
		assertEquals("3", firstRow.get("Third Column"));
		assertEquals("3", firstRow.get("Fourth column"));
		
		firstRow = mAcquiredModel.getRow(3);
		assertEquals("4", firstRow.get("First Column"));
		assertEquals("3", firstRow.get("Second Column"));
		assertEquals("4", firstRow.get("Third Column"));
		assertEquals("4", firstRow.get("Fourth column"));
	}
}
