package eu.quizit.view;

import java.awt.geom.Point2D;

import eu.quizit.common.EventStream;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class PartIdPanel extends HBox {

    private double dragDeltaX;
    private double dragDeltaY;

    public EventStream<Point2D.Double> saveCoordinatesRequest = new EventStream<>();

    public EventStream<Void> idRequest = new EventStream<>();
    public EventStream<Integer> setId = new EventStream<>();
    public EventStream<String> partIdRequest = new EventStream<>();
    public EventStream<String> partIdReceived = new EventStream<>();

    private TextField partIdTextField = new TextField();

    public PartIdPanel() {
        initEvents();
        initComponents();
    }

    private void initEvents() {
        partIdReceived.subscribe(partId -> {
            onPartIdReceived(partId);
        });
    }

    private void initComponents() {
        partIdTextField.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            dragDeltaX = partIdTextField.getScene().getWindow().getX() - event.getScreenX();
            dragDeltaY = partIdTextField.getScene().getWindow().getY() - event.getScreenY();
        });

        partIdTextField.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            partIdTextField.getScene().getWindow().setX(event.getScreenX() + dragDeltaX);
            partIdTextField.getScene().getWindow().setY(event.getScreenY() + dragDeltaY);
            partIdTextField.setCursor(Cursor.MOVE);
        });

        partIdTextField.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            saveCoordinatesRequest.publish(
                    new Point2D.Double(partIdTextField.getScene().getWindow().getX(), partIdTextField.getScene().getWindow().getY()));
            partIdTextField.setCursor(Cursor.DEFAULT);
        });

        partIdTextField.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String partName = partIdTextField.getText();
                if (!"".equals(partName)) {
                    partIdRequest.publish(partName);
                    partIdTextField.clear();
                }
            }
        });

        getChildren().add(partIdTextField);
    }

    private void onPartIdReceived(String partId) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(partId);
        clipboard.setContent(clipboardContent);
    }
}
