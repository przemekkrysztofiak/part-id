package eu.quizit.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import eu.quizit.common.exception.PropertyNotFoundException;
import eu.quizit.model.Model;
import eu.quizit.view.View;

public class Controller {

    private Model model;
    private View view;

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        new PrefixViewController(model, view);
        new PartIdViewController(model, view);
        try {
            model.initProperties();
        } catch (IOException | PropertyNotFoundException e) {
            view.showAlert.publish(e);
        }
    }

    public void launch() {
        view.launch.publish();
    }

    public String createPartId(String partName) throws FileNotFoundException, IOException, PropertyNotFoundException {
        return model.createPartId(partName);
    }

}
