package eu.quizit.controller;

import java.awt.geom.Point2D;
import java.io.IOException;

import eu.quizit.common.PartIdProperty;
import eu.quizit.common.exception.PropertyNotFoundException;
import eu.quizit.model.Model;
import eu.quizit.view.PartIdPanel;
import eu.quizit.view.View;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class PartIdPanelController {

    private final Model model;
    private final View view;
    private final PartIdPanel partIdPanel;

    public PartIdPanelController(Model model, View view) {
        this.model = model;
        this.view = view;
        this.partIdPanel = view.getPartIdView().getPartIdPanel();
        initEvents();
    }

    private void initEvents() {
        partIdPanel.saveCoordinatesRequest.subscribe(coordinates -> onSaveCoordiantesRequest(coordinates));
        partIdPanel.partIdRequest.subscribe(partName -> onPartIdRequest(partName));
    }

    private void onPartIdRequest(String partName) {
        try {
            String partId = model.createPartId(partName);
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(partId);
            clipboard.setContent(content);
        } catch (IOException | PropertyNotFoundException e) {
            view.showAlert.publish(e);
        }
    }

    private void onSaveCoordiantesRequest(Point2D.Double coordinates) {
        try {
            model.setProperty(PartIdProperty.X.toString(), String.valueOf(coordinates.getX()));
            model.setProperty(PartIdProperty.Y.toString(), String.valueOf(coordinates.getY()));
        } catch (IOException e) {
            view.showAlert.publish(e);
        }
    }

}
