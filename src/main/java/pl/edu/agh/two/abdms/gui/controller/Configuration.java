package pl.edu.agh.two.abdms.gui.controller;

import java.io.File;

public class Configuration {
    private File file;
    private String name;

    public Configuration(File file, String name) {
        this.file = file;
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
