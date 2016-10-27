package eu.quizit.controller;

import java.io.IOException;

import eu.quizit.model.Model;
import eu.quizit.view.PrefixPanel;

public class PrefixPanelController {

    private Model model;
    private PrefixPanel prefixPanel;
    private Controller controller;

    public PrefixPanelController(Model model, PrefixPanel prefixPanel, Controller controller) {
        this.model = model;
        this.prefixPanel = prefixPanel;
        this.controller = controller;
        prefixPanel.savePrefixRequest.subscribe(prefix -> {
            onSavePrefixRequest(prefix);
        });
    }

    private void onSavePrefixRequest(String prefix) {
        try {
            model.getPropertiesModel().setProperty("prefix", prefix);
            onPrefixSaved();
        } catch (IOException e) {
            onSavePrefixRequestException(e);
        }
    }

    private void onPrefixSaved() {
        prefixPanel.prefixSaved.publish();
        controller.showPartIdView();
    }

    private void onSavePrefixRequestException(Exception e) {
        prefixPanel.savePrefixRequestException.publish(e);
    }
}
