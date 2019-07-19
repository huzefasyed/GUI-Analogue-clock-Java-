package sample;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Setup implements Runnable {

    private String title;;
    private int size;
    private Display display;
    private Thread thread; // changes the current image to something different

    private BufferStrategy buffer;
    private Graphics2D g; // helps change the needle


    public Setup(String title, int size){

        this.title = title;
        this.size = size;
    }

    public void init (){ // This method will execute the class
        display = new Display(title, size);
    }

    public void draw(){

        buffer = display.canvas.getBufferStrategy();
        if(buffer == null){
            display.canvas.createBufferStrategy(3);
            return;
        }

        int center = size/2;

        g = (Graphics2D)buffer.getDrawGraphics();
        g.clearRect(0,0, size,size); //after every thread it clears the screen
        // draw

        g.setColor(Color.black);
        g.fillOval(10, 10, size-20, size-20); //creates black circle

        g.setColor(Color.white);
        g.fillOval(20,20,size-40,size-40); // creates white circle within black circle

        g.setColor(Color.RED);
        g.fillOval(center-10, center-10, 20,20); //creates red circle in the middle

        //draw Numbers

        int angleX, angleY;
        double position;
        double time = System.currentTimeMillis();
        int radius = center -40;
        for (int i =1; i<=12; i++){
            position = i/12.0 *Math.PI *2;


            angleX= (int)((center) + (Math.sin(position)*radius));
            angleY= (int)((center) - (Math.cos(position)*radius));

            g.setColor(Color.BLACK);


            String a = Integer.toString(i);
            g.drawString(a, angleX, angleY );
        }



        // hour hand

        radius = center - 150;

        time = System.currentTimeMillis()/ (60.0 * 12 * 60* 1000) * Math.PI*2;
        angleX = (int)((center)+ Math.sin(time)*radius);
        angleY = (int)((center) - (Math.cos(time)*radius));

        g.setColor(Color.blue);
        g.setStroke(new BasicStroke(6));
        g.drawLine(center, center, angleX, angleY);
        g.setStroke(new BasicStroke(0));


        // min hand

        radius = center - 70;

        time = System.currentTimeMillis()/ (60.0 * 60* 1000) * Math.PI*2;
        angleX = (int)((center)+ Math.sin(time)*radius);
        angleY = (int)((center) - (Math.cos(time)*radius));

        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        g.drawLine(center, center, angleX, angleY);
        g.setStroke(new BasicStroke(0));


        // seccond hand
        radius = center - 50;
        time = System.currentTimeMillis()/ (60.0 *1000) * Math.PI*2;
        angleX = (int)((center)+ Math.sin(time)*radius);
        angleY = (int)((center) - (Math.cos(time)*radius));

        g.setColor(Color.red);
        g.drawLine(center, center, angleX, angleY);



        //end

            buffer.show();
            g.dispose();

    }

    public synchronized void start(){  // syncs the thread

        thread = new Thread(this);
        thread.start();;
    }

    public synchronized void stop(){

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        init();

        while(true){
            draw();
        }
    }

}
