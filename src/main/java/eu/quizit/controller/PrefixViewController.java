package eu.quizit.controller;

import java.awt.geom.Point2D;

import eu.quizit.common.PartIdProperty;
import eu.quizit.common.exception.PropertyNotFoundException;
import eu.quizit.model.Model;
import eu.quizit.view.PrefixView;
import eu.quizit.view.View;

public class PrefixViewController {

    private final Model model;
    private final View view;
    private final PrefixView prefixView;

    public PrefixViewController(Model model, View view) {
        this.model = model;
        this.view = view;
        this.prefixView = view.getPrefixView();
        new PrefixPanelController(model, prefixView.getPrefixPanel());
        initEvents();
    }

    private void initEvents() {
        prefixView.showRequest.subscribe(nothing -> onShowRequest());
    }

    private void onShowRequest() {
        try {
            String prefix = model.getProperty(PartIdProperty.PREFIX.toString());
            if ("".equals(prefix)) {
                prefixView.show.publish();
            } else {
                try {
                    Point2D.Double coordinates = new Point2D.Double(Double.parseDouble(model.getProperty(PartIdProperty.X.toString())),
                            Double.parseDouble(model.getProperty(PartIdProperty.Y.toString())));
                    view.getPartIdView().show.publish(coordinates);
                } catch (NumberFormatException e) {
                    view.showAlert.publish(e);
                }
            }
        } catch (PropertyNotFoundException e) {
            view.showAlert.publish(e);
        }
    }
}
