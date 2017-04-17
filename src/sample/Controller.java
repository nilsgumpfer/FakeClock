package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button btnSetTime;
    @FXML
    private Button btnStartReset;
    @FXML
    public Label lblTime;
    @FXML
    private TextField tfHour;
    @FXML
    private TextField tfMinute;
    @FXML
    private TextField tfSecond;

    private int hourSet = 0;
    private int minSet = 0;
    private int secSet = 0;

    public boolean hourSetManual = false;
    public boolean minSetManual = false;
    public boolean secSetManual = false;

    private boolean started = false;

    WorkerThread workerThread;

    public void pressBtnStartReset(ActionEvent event){
        if(started == false) {
            started = true;
            workerThread = new WorkerThread(lblTime, hourSetManual, minSetManual, secSetManual, hourSet, minSet, secSet);
            workerThread.setDaemon(true);
            workerThread.start();
        }else
        {
            started = false;
            //workerThread.
        }
    }

    public void pressBtnSetTime(ActionEvent event){
        //workerThread.interrupt();
        setTime(tfHour.getText(), tfMinute.getText(), tfSecond.getText());
    }

    private void setTime(String hour, String min, String sec) {
        if(hour!=""){
            try{
                hourSet = Integer.valueOf(hour);
                hourSetManual = true;
            } catch (Exception e){
                System.out.println("Bitte nur gerade Zahlen eingeben!");
            }
        }

        if(min!=""){
            try{
                minSet = Integer.valueOf(min);
                minSetManual = true;
            } catch (Exception e){
                System.out.println("Bitte nur gerade Zahlen eingeben!");
            }

        }

        if(sec!=""){
            try{
                secSet = Integer.valueOf(sec);
                secSetManual = true;
            } catch (Exception e){
                System.out.println("Bitte nur gerade Zahlen eingeben!");
            }
        }

        workerThread.setThreadRunning(false);
        workerThread = new WorkerThread(lblTime, hourSetManual, minSetManual, secSetManual, hourSet, minSet, secSet);
        workerThread.setDaemon(true);
        workerThread.start();
        //workerThread.setThreadRunning(true);
    }

    public int getHour(){
        return hourSet;
    }

    public int getMin(){
        return minSet;
    }

    public int getSec(){
        return secSet;
    }

    private boolean isInt(javafx.scene.control.TextField input, String message){
        try
        {
            int hour = Integer.parseInt(input.getText());
            System.out.println("Eingegebene Stunde: " + hour);
            return true;
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error: " + message + " is not a Number");
            return false;
        }
    }
}
