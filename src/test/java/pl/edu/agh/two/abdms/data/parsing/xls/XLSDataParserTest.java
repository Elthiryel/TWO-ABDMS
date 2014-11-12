package pl.edu.agh.two.abdms.data.parsing.xls;

import java.io.File;

import pl.edu.agh.two.abdms.data.parsing.test.AbstractDataParserTest;

public class XLSDataParserTest extends AbstractDataParserTest {

	private static final String CORRECT_FILE_PATH = "src/test/java/test_data/test_data.xls";
	
	public void testTestFilesExist() throws Exception {
		File f = new File(CORRECT_FILE_PATH);
		assertTrue(isAnExistingFile(f));
	}
	
	private boolean isAnExistingFile(File f) {
		return f.exists() && !f.isDirectory();
	}
	
	public void testParseCorrectFile() {
		whenCorrectDataFileHasBeenParsed();
		thenReceivedObjectIsNotNull();
		thenCorrectColumnNamesHaveBeenObtained();
		thenAllTheRowsValuesHaveBeenObtained();
	}
	
	protected void whenCorrectDataFileHasBeenParsed() {
		mAcquiredModel = new XLSDataParser().parse(CORRECT_FILE_PATH);
	}
	
}
