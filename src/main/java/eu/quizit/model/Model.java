package eu.quizit.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Model {

    private PropertiesModel propertiesModel = new PropertiesModel();

    public PropertiesModel getPropertiesModel() {
        return propertiesModel;
    }

    public String createPartId(String partName) throws FileNotFoundException, IOException {
        String prefix = propertiesModel.getProperty("prefix");
        String id = propertiesModel.getProperty("id");
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

}