package eu.quizit.controller;

import java.io.IOException;

import eu.quizit.model.Model;
import eu.quizit.view.PrefixView;

public class PrefixViewController {

    private Model model;
    private PrefixView prefixView;
    private Controller controller;

    public PrefixViewController(Model model, PrefixView prefixView, Controller controller) {
        this.model = model;
        this.prefixView = prefixView;
        this.controller = controller;
        prefixView.showRequest.subscribe(nothing -> {
            onShowRequest();
        });
        new PrefixPanelController(model, prefixView.getPrefixPanel(), controller);
    }

    private void onShowRequest() {
        boolean prefixExists = false;
        try {
            prefixExists = model.getPropertiesModel().containsProperty("prefix");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (model.getPropertiesModel().propertiesFileExists() && prefixExists) {
            controller.showPartIdView();
        } else {
            prefixView.show.publish();
        }
    }
}
