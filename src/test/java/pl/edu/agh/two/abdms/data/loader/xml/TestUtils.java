package pl.edu.agh.two.abdms.data.loader.xml;

import java.util.Collection;

public class TestUtils {

	 public static String[] convertToArray(Collection<String> values ) {
		return values.toArray(new String[values.size()]);
	}
}
