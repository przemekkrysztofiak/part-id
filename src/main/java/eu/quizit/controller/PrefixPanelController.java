package eu.quizit.controller;

import java.io.IOException;

import eu.quizit.common.PartIdProperty;
import eu.quizit.model.Model;
import eu.quizit.view.PrefixPanel;

public class PrefixPanelController {

    private final Model model;
    private final PrefixPanel prefixPanel;

    public PrefixPanelController(Model model, PrefixPanel prefixPanel) {
        this.model = model;
        this.prefixPanel = prefixPanel;
        initEvents();
    }

    private void initEvents() {
        prefixPanel.savePrefix.subscribe(prefix -> onSavePrefix(prefix));
    }

    private void onSavePrefix(String prefix) {
        try {
            model.setProperty(PartIdProperty.PREFIX.toString(), prefix);
            prefixPanel.closePrefixView.publish();

        } catch (IOException e) {
            prefixPanel.showAlert.publish(e);
        }
    }
}
