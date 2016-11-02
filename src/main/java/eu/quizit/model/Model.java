package eu.quizit.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import eu.quizit.common.PartIdProperty;
import eu.quizit.common.exception.PropertyNotFoundException;

public class Model {

    private PropertiesModel propertiesModel = new PropertiesModel();

    public PropertiesModel getPropertiesModel() {
        return propertiesModel;
    }

    public String createPartId(String partName) throws FileNotFoundException, IOException, PropertyNotFoundException {
        String prefix = propertiesModel.getProperty(PartIdProperty.PREFIX.toString());
        String id = propertiesModel.getProperty(PartIdProperty.ID.toString());
        int intId = Integer.valueOf(id);
        propertiesModel.setProperty("id", String.valueOf(++intId));
        Calendar calendar = GregorianCalendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);

        StringBuilder partIdStringBuilder = new StringBuilder();
        partIdStringBuilder.append("[");
        partIdStringBuilder.append(prefix);
        partIdStringBuilder.append("-");
        partIdStringBuilder.append(year);
        partIdStringBuilder.append("-");
        partIdStringBuilder.append(month);
        partIdStringBuilder.append("]");
        partIdStringBuilder.append(partName);
        partIdStringBuilder.append("-");
        partIdStringBuilder.append(id);

        return partIdStringBuilder.toString();
    }

    public void initProperties() throws IOException, PropertyNotFoundException {
        propertiesModel.initProperties();
        if (propertiesModel.getProperty(PartIdProperty.ID.toString()).isEmpty()) {
            propertiesModel.setProperty(PartIdProperty.ID.toString(), "1");
        }
        if (propertiesModel.getProperty(PartIdProperty.X.toString()).isEmpty()) {
            propertiesModel.setProperty(PartIdProperty.X.toString(), "0.0");
        }
        if (propertiesModel.getProperty(PartIdProperty.Y.toString()).isEmpty()) {
            propertiesModel.setProperty(PartIdProperty.Y.toString(), "0.0");
        }
    }

    public String getProperty(String key) throws PropertyNotFoundException {
        return propertiesModel.getProperty(key);
    }

    public void setProperty(String key, String value) throws IOException {
        propertiesModel.setProperty(key, value);
    }

}