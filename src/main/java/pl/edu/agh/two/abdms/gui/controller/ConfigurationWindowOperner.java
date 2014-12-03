package pl.edu.agh.two.abdms.gui.controller;

import pl.edu.agh.two.abdms.gui.components.graph.VertexType;

public class ConfigurationWindowOperner {
	
	
	
	public void openWindow(Integer vertexId, VertexType vertexType) {
		switch (vertexType) {
			case classification:
				openClassificationWindow();
				break;
			default:
				break;
		}
		
	}

	private void openClassificationWindow() {
		
	}

}
