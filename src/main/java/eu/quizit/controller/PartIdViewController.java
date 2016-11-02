package eu.quizit.controller;

import eu.quizit.model.Model;
import eu.quizit.view.PartIdView;
import eu.quizit.view.View;

public class PartIdViewController {

    private final Model model;
    private final View view;
    private final PartIdView partIdView;

    public PartIdViewController(Model model, View view) {
        this.model = model;
        this.view = view;
        this.partIdView = view.getPartIdView();
        new PartIdPanelController(model, view);
        initEvents();
    }

    private void initEvents() {
    }
}
