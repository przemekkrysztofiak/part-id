package eu.quizit.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import eu.quizit.model.Model;
import eu.quizit.view.View;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void launch() {
        new PrefixViewController(model, view.getPrefixView(), this);
        view.launchRequest.publish();
    }

    public void showPartIdView() {
        new PartIdViewController(model, view.getPartIdView(), this);
        view.partIdViewShowRequest.publish();
    }

    public String createPartId(String partName) throws FileNotFoundException, IOException {
        return model.createPartId(partName);
    }

}
