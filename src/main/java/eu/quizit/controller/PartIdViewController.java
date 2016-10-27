package eu.quizit.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import eu.quizit.model.Model;
import eu.quizit.view.PartIdView;

public class PartIdViewController {

    private PartIdView partIdView;
    private Model model;
    private Controller controller;

    public PartIdViewController(Model model, PartIdView partIdView, Controller controller) {
        this.model = model;
        this.partIdView = partIdView;
        this.controller = controller;
        new PartIdPanelController(model, partIdView.getPartIdPanel(), controller);
        initEvents();
    }

    private void initEvents() {
        partIdView.showRequest.subscribe(nothing -> {
            onShowRequest();
        });
    }

    private void onShowRequest() {
        try {
            String x = model.getPropertiesModel().getProperty("x");
            String y = model.getPropertiesModel().getProperty("y");
            try {
                partIdView.setX.publish("".equals(x) ? null : Double.parseDouble(x));
            } catch (NumberFormatException e) {
                model.getPropertiesModel().setProperty(x, "");
                partIdView.setX.publish(null);
            }
            try {
                partIdView.setY.publish("".equals(y) ? null : Double.parseDouble(y));
            } catch (NumberFormatException e) {
                model.getPropertiesModel().setProperty(y, "");
                partIdView.setX.publish(null);
            }
            partIdView.show.publish();

        } catch (FileNotFoundException e) {
            // TODO handle exception
            e.printStackTrace();
        } catch (IOException e) {
            // TODO handle exception
            e.printStackTrace();
        }
    }

}
