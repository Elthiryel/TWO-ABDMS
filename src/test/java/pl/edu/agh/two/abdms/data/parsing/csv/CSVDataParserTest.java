package pl.edu.agh.two.abdms.data.parsing.csv;

import java.io.File;
import java.util.Map;

import junit.framework.TestCase;
import pl.edu.agh.two.abdms.data.loader.DataModel;

public class CSVDataParserTest extends TestCase {

	private static final String CORRECT_FILE_PATH = "src/test/java/test_data/correct_file.csv";

	private DataModel mAcquiredModel;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mAcquiredModel = null;
	}
	
	public void testTestFilesExist() throws Exception {
		File f = new File(CORRECT_FILE_PATH);
		assertTrue(isAnExistingFile(f));
	}
	
	private boolean isAnExistingFile(File f) {
		return f.exists() && !f.isDirectory();
	}
	
	public void testParseCorrectFileWithCorrectSeparator() {
		whenCorrectDataFileHasBeenParsed();
		thenReceivedObjectIsNotNull();
		thenCorrectColumnNamesHaveBeenObtained();
		thenAllTheRowsValuesHaveBeenObtained();
	}
	
	protected void whenCorrectDataFileHasBeenParsed() {
		mAcquiredModel = new CSVDataParser().parse(CORRECT_FILE_PATH, ";");
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
		assertEquals("1", firstRow.get("Fourth Column"));
		
		firstRow = mAcquiredModel.getRow(1);
		assertEquals("2", firstRow.get("First Column"));
		assertEquals("2", firstRow.get("Second Column"));
		assertEquals("2", firstRow.get("Third Column"));
		assertEquals("2", firstRow.get("Fourth Column"));
		
		firstRow = mAcquiredModel.getRow(2);
		assertEquals("3", firstRow.get("First Column"));
		assertEquals("4", firstRow.get("Second Column"));
		assertEquals("3", firstRow.get("Third Column"));
		assertEquals("3", firstRow.get("Fourth Column"));
		
		firstRow = mAcquiredModel.getRow(3);
		assertEquals("4", firstRow.get("First Column"));
		assertEquals("3", firstRow.get("Second Column"));
		assertEquals("4", firstRow.get("Third Column"));
		assertEquals("4", firstRow.get("Fourth Column"));
	}
}
