package eu.quizit.controller;

import java.io.IOException;

import eu.quizit.model.Model;
import eu.quizit.view.PrefixView;

public class PrefixViewController {

    private PrefixView prefixView;
    private Model model;

    public PrefixViewController(PrefixView prefixView, Model model) {
        this.prefixView = prefixView;
        this.model = model;
        prefixView.showRequest.subscribe(nothing -> {
            onShowRequest();
        });
        new PrefixPanelController(prefixView.getPrefixPanel(), model);
    }

    private void onShowRequest() {
        if (!model.getPropertiesModel().propertiesFileExists()) {
            prefixView.show.publish();
        } else {
            try {
                if (!model.getPropertiesModel().containsKey("prefix")) {
                    prefixView.show.publish();
                }
            } catch (IOException e) {
                //TODO handle exception
                e.printStackTrace();
            }
        }
    }
}
