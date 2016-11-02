package eu.quizit.view;

import eu.quizit.common.EventStream;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrefixPanel extends VBox {

    public EventStream<String> savePrefix = new EventStream<>();
    public EventStream<Exception> showAlert = new EventStream<>();
    public EventStream<Void> closePrefixView = new EventStream<>();
    public EventStream<Void> showPartIdView = new EventStream<>();

    private HBox savePrefixPane = new HBox();
    private TextField prefixTextField = new TextField();
    private Button savePrefixButton = new Button("Save prefix");

    public PrefixPanel() {
        initComponents();
    }

    private void initComponents() {
        prefixTextField.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (KeyCode.ENTER.equals(event.getCode())) {
                String prefix = prefixTextField.getText();
                if (!"".equals(prefix)) {
                    prefix = prefix.replaceAll("\\s+", "");
                    savePrefix.publish(prefix);
                }
            }
        });

        savePrefixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String prefix = prefixTextField.getText();
            if (!"".equals(prefix)) {
                prefix = prefix.replaceAll("\\s+", "");
                savePrefix.publish(prefix);
            }
        });

        savePrefixPane.getChildren().addAll(prefixTextField, savePrefixButton);
        getChildren().addAll(savePrefixPane);
    }
}
