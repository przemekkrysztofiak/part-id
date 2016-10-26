package eu.quizit.view;

import eu.quizit.common.EventStream;

public class View {

    public EventStream<Void> launchRequest = new EventStream<>();
    public EventStream<Void> prefixViewCreationRequest = new EventStream<>();

    private PrefixView prefixView = new PrefixView();

    public View() {
        launchRequest.subscribe(nothing -> {
            prefixView.showRequest.publish();
        });
    }

    public PrefixView getPrefixView() {
        return prefixView;
    }

    //    private void onCreationRequest() {
    //        prefixView.showRequest.publish();
    //    }

}
