package eu.quizit.controller;

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
        new PrefixViewController(view.getPrefixView(), model);
        view.launchRequest.publish();
    }

}
