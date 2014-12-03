package pl.edu.agh.two.abdms.agent;

import jade.core.Agent;

public class MainAgent extends Agent {

    protected void setup() {
        System.out.println("Hello World! My name is " + getLocalName());

        // Make this agent terminate
//  	doDelete();
    }
}
