package pl.edu.agh.two.abdms;

import jade.MicroBoot;
import jade.core.MicroRuntime;
import pl.edu.agh.two.abdms.agent.AgentManager;
import pl.edu.agh.two.abdms.gui.GuiLauncher;


public class Main extends MicroBoot {

    public static void main(String[] args) {
        startAgents(args);
        new GuiLauncher().startGui();
    }

    private static void startAgents(String[] args) {
        MicroBoot.main(args);
        try {
            MicroRuntime.startAgent("MainAgent", "pl.edu.agh.two.abdms.agent.MainAgent", null);
            MicroRuntime.startAgent("MainAgent", "pl.edu.agh.two.abdms.agent.DataPreprocessorAgent", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
