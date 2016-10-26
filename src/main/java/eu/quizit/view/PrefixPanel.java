package eu.quizit.view;


import eu.quizit.common.EventStream;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrefixPanel extends VBox {

    public EventStream<String> savePrefixRequest = new EventStream<>();
    public EventStream<Void> prefixSaved = new EventStream<>();
    public EventStream<Exception> savePrefixRequestException = new EventStream<>();
    public EventStream<Void> closePrefixViewRequest = new EventStream<>();

    private HBox savePrefixPane = new HBox();
    private TextField prefixTextField = new TextField();
    private Button savePrefixButton = new Button("Save");

    public PrefixPanel() {
        initEvents();
        initComponents();
    }

    private void initEvents() {
        prefixSaved.subscribe(nothing -> {
            onPrefixSaved();
        });

        savePrefixRequestException.subscribe(e -> {
            onSavePrefixRequestException(e);
        });
    }

    private void initComponents() {
        savePrefixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String prefix = prefixTextField.getText();
            if (prefix != null && !"".equals(prefix)) {
                prefix = prefix.replaceAll("\\s+", "");
                onPrefixSaveRequest(prefix);
            }
        });

        savePrefixPane.getChildren().addAll(prefixTextField, savePrefixButton);
        getChildren().addAll(savePrefixPane);
    }

    private void onPrefixSaveRequest(String prefix) {
        savePrefixRequest.publish(prefix);
    }

    private void onPrefixSaved() {
        closePrefixViewRequest.publish();
    }

    private void onSavePrefixRequestException(Exception e) {
        //TODO handleException
        e.printStackTrace();
    }
}
