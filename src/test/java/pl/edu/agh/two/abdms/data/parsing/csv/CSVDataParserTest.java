package pl.edu.agh.two.abdms.data.parsing.csv;

import java.io.File;

import pl.edu.agh.two.abdms.data.parsing.test.AbstractDataParserTest;

public class CSVDataParserTest extends AbstractDataParserTest {

	private static final String CORRECT_FILE_PATH = "src/test/java/test_data/correct_file.csv";
	
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
}
