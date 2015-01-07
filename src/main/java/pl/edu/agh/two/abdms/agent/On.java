package pl.edu.agh.two.abdms.agent;

import jade.content.Predicate;

@SuppressWarnings("serial")
public class On implements Predicate {

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    private String propertyName;

}
