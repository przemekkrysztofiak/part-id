package eu.quizit.view;

import eu.quizit.common.EventStream;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrefixView extends Stage {

    public EventStream<Void> showRequest = new EventStream<>();
    public EventStream<Void> show = new EventStream<>();
    public EventStream<Void> close = new EventStream<>();

    private PrefixPanel prefixPanel = new PrefixPanel();

    public PrefixView() {
        initEvents();
    }

    private void initEvents() {
        prefixPanel.closePrefixViewRequest.subscribe(nothing -> {
            close.publish();
        });

        close.subscribe(nothing -> {
            onClose();
        });

        show.subscribe(nothing -> {
            onShow();
        });
    }

    private void onShow() {
        setScene(new Scene(prefixPanel));
        show();
    }

    private void onClose() {
        close();
    }

    public PrefixPanel getPrefixPanel() {
        return prefixPanel;
    }

}
