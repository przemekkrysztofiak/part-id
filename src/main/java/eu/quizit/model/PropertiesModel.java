package eu.quizit.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Properties;

import eu.quizit.common.PartIdProperty;
import eu.quizit.common.exception.PropertyNotFoundException;

public class PropertiesModel {

    private File propertiesFile = new File(System.getProperty("user.home") + "\\" + ".part-id" + "\\" + "part-id.properties");
    private Properties properties = new Properties();

    public void initProperties() throws IOException {
        File dir = propertiesFile.getParentFile();
        if (!dir.isDirectory()) {
            Files.createDirectories(propertiesFile.getParentFile().toPath());
        }
        if (!propertiesFile.exists()) {
            Files.createFile(propertiesFile.toPath());
        }
        try (InputStream in = new FileInputStream(propertiesFile)) {
            properties.load(in);
        }
        for (PartIdProperty partIdProperty : PartIdProperty.values()) {
            String partIdPropertyName = partIdProperty.toString();
            if (properties.getProperty(partIdPropertyName) == null) {
                setProperty(partIdPropertyName, "");
            }
        }
    }

    public String getProperty(String key) throws PropertyNotFoundException {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new PropertyNotFoundException(key);
        }
        return value;
    }

    public void setProperty(String key, String value) throws IOException {
        properties.setProperty(key, value);
        try (OutputStream out = new FileOutputStream(propertiesFile)) {
            properties.store(out, null);
        }
    }

}
