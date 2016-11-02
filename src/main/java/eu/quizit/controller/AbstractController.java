package eu.quizit.controller;

import eu.quizit.model.Model;
import eu.quizit.view.View;

public abstract class AbstractController {

    protected Model model;
    protected View view;

    public AbstractController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

}
