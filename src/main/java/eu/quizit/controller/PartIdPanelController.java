package eu.quizit.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import eu.quizit.model.Model;
import eu.quizit.view.PartIdPanel;

public class PartIdPanelController {

    private Model model;
    private PartIdPanel partIdPanel;
    private Controller controller;

    public PartIdPanelController(Model model, PartIdPanel partIdPanel, Controller controller) {
        this.model = model;
        this.partIdPanel = partIdPanel;
        this.controller = controller;
        initEvents();
    }

    private void initEvents() {
        partIdPanel.idRequest.subscribe(nothing -> {
            onIdRequest();
        });

        partIdPanel.partIdRequest.subscribe(partName -> {
            try {
                String partId = controller.createPartId(partName);
                partIdPanel.partIdReceived.publish(partId);
            } catch (FileNotFoundException e) {
                // TODO Handle exception
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Handle exception
                e.printStackTrace();
            }
        });
    }

    private void onIdRequest() {
        try {
            String id = model.getPropertiesModel().getProperty("id");
            Integer intId;
            try {
                intId = Integer.parseInt(id);
                partIdPanel.setId.publish(intId);
            } catch (NumberFormatException e) {
                intId = 1;
                partIdPanel.setId.publish(intId);
            }
            model.getPropertiesModel().setProperty("id", String.valueOf(++intId));
        } catch (IOException e) {
            //TODO handle exception
        }
    }

}
