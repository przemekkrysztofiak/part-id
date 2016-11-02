package eu.quizit.view;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import eu.quizit.common.EventStream;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PartIdView extends Stage {

    public EventStream<Point2D.Double> show = new EventStream<>();

    private PartIdPanel partIdPanel = new PartIdPanel();

    public PartIdView() {
        initEvents();
    }

    private void initEvents() {
        show.subscribe(coordinates -> onShow(coordinates));
    }

    private void onShow(Point2D.Double coordinates) {
        setX(coordinates.getX());
        setY(coordinates.getY());
        initTray();
        initStyle(StageStyle.UNDECORATED);
        setScene(new Scene(partIdPanel));
        setAlwaysOnTop(true);
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

        MenuItem centerMenuItem = new MenuItem("center");
        centerMenuItem.addActionListener(event -> {
            Platform.runLater(() -> {
                centerOnScreen();
                partIdPanel.saveCoordinatesRequest.publish(new Point2D.Double(getX(), getY()));
            });
        });
        popupMenu.add(centerMenuItem);

        Image trayIconImage = null;
        try {
            trayIconImage = ImageIO.read(PartIdView.class.getClassLoader().getResourceAsStream("resources/cog-wheel-silhouette.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        TrayIcon trayIcon = new TrayIcon(trayIconImage, "part-id", popupMenu);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        Platform.setImplicitExit(false);
    }

    public PartIdPanel getPartIdPanel() {
        return partIdPanel;
    }

}
