package pl.edu.agh.two.abdms;

import jade.MicroBoot;
import jade.core.MicroRuntime;
import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.gui.GuiLauncher;


public class Main extends MicroBoot {

    public static void main(String[] args) {
        MicroBoot.main(args);
        try {
            MicroRuntime.startAgent("MainAgent", "pl.edu.agh.two.abdms.agent.DataPreprocessorAgent", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new GuiLauncher().startGui();
    }

}
