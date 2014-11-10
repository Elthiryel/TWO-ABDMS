package pl.edu.agh.two.abdms;

import pl.edu.agh.two.abdms.gui.GuiLauncher;

import java.io.File;

public class Main {

    public static void serveFile(File file) {
        System.out.println("FILE RECEIVED: " + file.getName());
    }

    public static void main(String[] args) {
        new GuiLauncher().startGui();
    }
}
