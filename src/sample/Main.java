package main;

import sample.Display;
import sample.Setup;

public class Main{

    public static void main(String[] args) {

        new Display("Analog Clock", 500);

        Setup set = new Setup("Analogue", 500);
        set.start();
    }
}