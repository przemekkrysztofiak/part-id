package eu.quizit.view;

import eu.quizit.common.EventStream;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrefixView extends Stage {

    public EventStream<Void> showRequest = new EventStream<>();
    public EventStream<Void> show = new EventStream<>();

    public EventStream<Exception> showAlert = new EventStream<>();
    public EventStream<Void> showPartIdView = new EventStream<>();
    public EventStream<Void> close = new EventStream<>();

    private PrefixPanel prefixPanel = new PrefixPanel();

    public PrefixView() {
        initEvents();
    }

    private void initEvents() {
        show.subscribe(nothing -> onShow());

        close.subscribe(nothing -> onClose());
        prefixPanel.closePrefixView.subscribe(nothing -> close.publish());
        prefixPanel.showAlert.subscribe(e -> showAlert.publish(e));
        prefixPanel.showPartIdView.subscribe(nothing -> showPartIdView.publish());
    }

    private void onShow() {
        Scene scene = new Scene(prefixPanel);
        setScene(scene);
        show();
    }

    private void onClose() {
        close();
    }

    public PrefixPanel getPrefixPanel() {
        return prefixPanel;
    }

}
