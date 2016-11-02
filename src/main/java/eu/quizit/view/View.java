package eu.quizit.view;

import java.io.PrintWriter;
import java.io.StringWriter;

import eu.quizit.common.EventStream;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class View {

    public EventStream<Void> launch = new EventStream<>();
    public EventStream<Exception> showAlert = new EventStream<>();

    private PrefixView prefixView = new PrefixView();
    private PartIdView partIdView = new PartIdView();

    public View() {
        initEvents();
    }

    private void initEvents() {
        launch.subscribe(nothing -> prefixView.showRequest.publish());

        prefixView.showAlert.subscribe(e -> onShowAlert(e));
        prefixView.showPartIdView.subscribe(nothing -> partIdView.show.publish());
        prefixView.getPrefixPanel().showPartIdView.subscribe(nothing -> partIdView.show.publish());

        showAlert.subscribe(e -> onShowAlert(e));
    }

    public PrefixView getPrefixView() {
        return prefixView;
    }

    public PartIdView getPartIdView() {
        return partIdView;
    }

    private void onShowAlert(Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(new StringWriter()));
        String exceptionAsString = sw.toString();
        System.out.println(exceptionAsString);
        System.out.println("dupa");

        PrintWriter out = new PrintWriter(new StringWriter());
        e.printStackTrace(out);

        alert.setContentText(e.toString());
        alert.show();
    }
}
