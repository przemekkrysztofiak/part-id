package eu.quizit.view;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;

import javax.imageio.ImageIO;

import eu.quizit.common.EventStream;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PartIdView extends Stage {

    public EventStream<Void> showRequest = new EventStream<>();
    public EventStream<Double> setX = new EventStream<>();
    public EventStream<Double> setY = new EventStream<>();
    public EventStream<Void> show = new EventStream<>();

    private PartIdPanel partIdPanel = new PartIdPanel();

    public PartIdView() {
        initEvents();
    }

    private void initEvents() {
        setX.subscribe(x -> {
            onSetX(x);
        });

        setY.subscribe(y -> {
            onSetY(y);
        });

        show.subscribe(nothing -> {
            onShow();
        });

    }

    private void onShow() {
        initTray();
        initStyle(StageStyle.UNDECORATED);
        setScene(new Scene(partIdPanel));
        show();
    }

    private void initTray() {
        SystemTray tray = SystemTray.getSystemTray();
        PopupMenu popupMenu = new PopupMenu();

        MenuItem showMenuItem = new MenuItem("show");
        showMenuItem.addActionListener(event -> {
            Platform.runLater(() -> {
                show();
            });
        });
        popupMenu.add(showMenuItem);

        MenuItem closeMenuItem = new MenuItem("close");
        closeMenuItem.addActionListener(event -> {
            Platform.runLater(() -> {
                System.exit(0);
            });
        });
        popupMenu.add(closeMenuItem);

        Image trayIconImage = null;
        try {
            trayIconImage = ImageIO.read(
                    getClass().getClassLoader().getResourceAsStream("cog-wheel-silhouette.png"));
        } catch (IOException e) {
            // TODO handle exception
            e.printStackTrace();
        }

        TrayIcon trayIcon = new TrayIcon(trayIconImage, "part-id", popupMenu);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            // TODO handle exception
            e.printStackTrace();
        }
        
        Platform.setImplicitExit(false);
    }

    private void onSetY(Double y) {
        if (y != null) {
            setY(y);
        }
    }

    private void onSetX(Double x) {
        if (x != null) {
            setX(x);
        }
    }

    public PartIdPanel getPartIdPanel() {
        return partIdPanel;
    }

}
