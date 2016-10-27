package eu.quizit.controller;

public class Main {

    public static void main(String[] args) {
        String s = "dupa";
        try {
            Double d = Double.valueOf(s);
        } catch (NumberFormatException e) {
            System.out.println("e");
        }
        System.out.println("ale fajnie");
    }
}
