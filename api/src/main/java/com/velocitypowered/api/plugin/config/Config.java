package com.velocitypowered.api.plugin.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SuppressWarnings("unchecked")
public class Config {
    Path dataPath;
    File dataDirectory;
    Yaml yaml;
    public Config(Path path){
        this.dataPath = path;
        dataDirectory = dataPath.toFile();
        yaml = new Yaml();
    }
    /**
     * This only works if the config folder is not generated yet in "plugins" folder
     * Copy a json text and put it in a generated folder inside the plugins folder
     */
    public void generateDefaultConfig(String yaml){
        File configFile = new File(dataDirectory, "config.yml");

        if(dataDirectory.mkdirs() && configFile.exists()){
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write(yaml);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getString(String path){
        try {
            String yamlString = Files.readString(Paths.get(dataPath.toUri()));
            Map<String, Object> config = yaml.load(yamlString);
            String[] subPaths = path.split("\\.");
            Object subSector = "notFound";
            for (String subPath : subPaths) {
                subSector = config.get(subPath);
            }
            return (String) subSector;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public int getInt(String path){
        try {
            String yamlString = Files.readString(Paths.get(dataPath.toUri()));
            Map<String, Object> config = yaml.load(yamlString);
            String[] subPaths = path.split("\\.");
            Object subSector = -1;
            for (String subPath : subPaths) {
                subSector = config.get(subPath);
            }
            return (int) subSector;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> getStringList(String path){
        try {
            String yamlString = Files.readString(Paths.get(dataPath.toUri()));
            Map<String, Object> config = yaml.load(yamlString);
            String[] subPaths = path.split("\\.");
            Object subSector = new ArrayList<String>();
            for (String subPath : subPaths) {
                subSector = config.get(subPath);
            }
            return (ArrayList<String>) subSector;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
