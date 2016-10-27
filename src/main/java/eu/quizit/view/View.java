package eu.quizit.view;

import eu.quizit.common.EventStream;

public class View {

    public EventStream<Void> launchRequest = new EventStream<>();
    public EventStream<Void> prefixViewCreationRequest = new EventStream<>();
    public EventStream<Void> partIdViewShowRequest = new EventStream<>();

    private PrefixView prefixView = new PrefixView();
    private PartIdView partIdView = new PartIdView();

    public View() {
        launchRequest.subscribe(nothing -> {
            prefixView.showRequest.publish();
        });
        partIdViewShowRequest.subscribe(nothing -> {
            partIdView.showRequest.publish();
        });
    }

    public PrefixView getPrefixView() {
        return prefixView;
    }

    public PartIdView getPartIdView() {
        return partIdView;
    }

    //    private void onCreationRequest() {
    //        prefixView.showRequest.publish();
    //    }

}
