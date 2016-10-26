package eu.quizit.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import eu.quizit.common.EventStream;

public class PropertiesModel {

    public EventStream<Boolean> propertiesFileExists = new EventStream<>();

    private File propertiesFile = new File(
            System.getProperty("user.home") + "\\" + ".part-id" + "\\" + "part-id.properties");

    public boolean propertiesFileExists() {
        boolean propertiesFileExists = propertiesFile.exists() && !propertiesFile.isDirectory();
        this.propertiesFileExists.publish(propertiesFileExists);
        return propertiesFileExists;
    }
    
    public boolean containsKey(String key)
            throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        try (InputStream in = new FileInputStream(propertiesFile)) {
            properties.load(in);
        }
        boolean containsKey = properties.containsKey(key);
        if (containsKey) {
            String value = properties.getProperty(key);
            boolean valueExists = value != null && !"".equals(value);
            return valueExists;
        }
        return false;
    }

    public void createPropertiesFile() throws IOException {
        propertiesFile.getParentFile().mkdirs();
        propertiesFile.createNewFile();
    }

    public void setProperty(String key, String value) throws IOException {
        if (!propertiesFileExists()) {
            createPropertiesFile();
        }
        Properties properties = new Properties();
        properties.setProperty(key, value);

        try (OutputStream out = new FileOutputStream(propertiesFile)) {
            properties.store(out, null);
        }
    }

    public String getProperty(String key) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        try (InputStream in = new FileInputStream(propertiesFile)) {
            properties.load(in);
        }
        return properties.getProperty(key);
    }

}
