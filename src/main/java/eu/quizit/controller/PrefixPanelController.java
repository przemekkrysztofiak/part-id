package eu.quizit.controller;

import java.io.IOException;

import eu.quizit.model.Model;
import eu.quizit.view.PrefixPanel;

public class PrefixPanelController {

    private PrefixPanel prefixPanel;
    private Model model;

    public PrefixPanelController(PrefixPanel prefixPanel, Model model) {
        this.prefixPanel = prefixPanel;
        this.model = model;
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
    }

    private void onSavePrefixRequestException(Exception e) {
        prefixPanel.savePrefixRequestException.publish(e);
    }
}
