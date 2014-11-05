package pl.edu.agh.two.abdms.data.util;

import java.io.Closeable;
import java.io.IOException;

public final class CloseablesUtil {

	private CloseablesUtil(){}
	
	public static void silentlyClose(Closeable objectToClose) {
		if (objectToClose != null) {
			try {
				objectToClose.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
