package sample;

import javax.swing.*;
import java.awt.*;

public class Display {

    private String title;
    private int size;
    private JFrame frame;
    public static Canvas canvas;

    public Display(String title, int size){
        this.title = title;
        this.size = size;

        createDisplay();

    }

    public void createDisplay(){

        frame = new JFrame(title); //Sets title when you intialise display

        frame.setSize(size, size); // sets size by using var size

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window when the Cross is pressed

        frame.setResizable(false); // adjusting screen won't resize it.

        frame.setVisible(true);

        canvas = new Canvas(); // create canvas
        canvas.setPreferredSize(new Dimension(size, size)); // setting size of the canvas
        canvas.setBackground(new Color(102, 70, 230 )); // sets colour of canvas
        frame.add(canvas); // adds the canvas to the frame
        frame.pack();


    }
}
