package eu.quizit.view;

import eu.quizit.common.EventStream;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class PartIdPanel extends HBox {

    public EventStream<Void> idRequest = new EventStream<>();
    public EventStream<Integer> setId = new EventStream<>();
    public EventStream<String> partIdRequest = new EventStream<>();
    public EventStream<String> partIdReceived = new EventStream<>();

    private Integer id;
    private TextField partIdTextField = new TextField();

    public PartIdPanel() {
        initEvents();
        initComponents();
    }

    private void initEvents() {
        setId.subscribe(id -> {
            onSetId(id);
        });

        partIdReceived.subscribe(partId -> {
            onPartIdReceived(partId);
        });
    }

    private void onSetId(Integer id) {
        this.id = id;
    }

    private void initComponents() {
        partIdTextField.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String partName = partIdTextField.getText();
                if (!"".equals(partName)) {
                    partIdRequest.publish(partName);
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
