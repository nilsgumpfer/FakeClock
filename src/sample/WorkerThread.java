package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Created by Carlo on 15.04.2017.
 */

public class WorkerThread extends Thread{

    private Label lbl_time = null;
    Calendar cal;

    int hour;
    int min;
    int sec;
    int AM_PM;
    String day_night = "";

    boolean hourSetManual;
    boolean minSetManual;
    boolean secSetManual;
    int hourSet;
    int minSet;
    int secSet;
    boolean threadRunning = true;

    public WorkerThread(Label lbl_time, boolean hourSetManual, boolean minSetManual, boolean secSetManual, int hourSet, int minSet, int secSet) {
        this.hourSetManual = hourSetManual;
        this.minSetManual = minSetManual;
        this.secSetManual = secSetManual;
        this.hourSet = hourSet;
        this.minSet = minSet;
        this.secSet = secSet;
        threadRunning = true;

        setDaemon(true);
        this.lbl_time = lbl_time;
    }

    @Override
    public void run() {
        //while (!this.isInterrupted()) {
            while (threadRunning) {
            // UI updaten
            Platform.runLater(() -> {
                // entsprechende UI Komponente updaten
                if(AM_PM == 1)
                {
                    day_night = "PM";
                }
                else
                {
                    day_night = "AM";
                }

                cal = new GregorianCalendar();


                System.out.println("durchlauf");

                if(hourSetManual==false)
                {
                    hour = cal.get(Calendar.HOUR);
                } else {
                    hour = hourSet;
                }

                if(minSetManual==false)
                {
                    min = cal.get(Calendar.MINUTE);
                }else {
                    min = minSet;
                }

                if(secSetManual==false)
                {
                    sec = cal.get(Calendar.SECOND);
                }else {
                    sec = secSet;
                }

                AM_PM = cal.get(Calendar.AM_PM);

                String time = hour + ":" + min + ":" + sec + " "  + day_night;

                lbl_time.setFont(new Font(40));
                lbl_time.setText(time);
            });

            // Thread schlafen
            try {
                // fuer 0,5 Sekunde
                sleep(500);
                //sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException ex) {
                //Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setThreadRunning(boolean running){
        threadRunning = running;
        System.out.println("threadrunning");
        System.out.println(threadRunning);
    }
}