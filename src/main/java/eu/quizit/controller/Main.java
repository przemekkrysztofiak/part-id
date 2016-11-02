package eu.quizit.controller;

import java.awt.geom.Point2D;

public class Main {

    public static void main(String[] args) {

        Point2D.Double point = new Point2D.Double();
        System.out.println("x=" + point.getX());
        System.out.println("y=" + point.getY());
        // try {
        // Double.parseDouble("dupa");
        // } catch (NumberFormatException e) {
        // System.out.println("e zlapany");
        // metoda();
        // }
        // System.out.println("koniec");
    }

    public static void metoda() {
        System.out.println("metoda");
    }
}
