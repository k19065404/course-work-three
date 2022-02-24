/**
 * Write a description of class time here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Time
{
    private int minutes;
    private int hours;
    private int days;
    private boolean isNight;
    
    public Time(){
        minutes = -1;
        hours = 0;
        days = 0;
        isNight = true;
        incrimentMinutes();
    }
    
    public void incrimentDays(){
        days++;
    }
    
    public void incrimentHours(){
        hours++;
        if(hours >=24){
            hours = 0;
            incrimentDays();
        }
    }
    
    public void incrimentMinutes(){
        minutes+=30;
        if(minutes >= 60){
            minutes = 0;
            incrimentHours();
        }
    }
    
    public String getTime(){
        String Minutes, Hours, Days;
        if(minutes < 10){
            Minutes = "0"+minutes;
        } else {
            Minutes = ""+minutes;
        }
        
        if(hours < 10){
            Hours = "0"+hours;
        } else {
            Hours = ""+hours;
        }
        if(days < 10){
            Days = "0"+days;
        } else {
            Days = ""+days;
        }
        String Time = Days + " Days " + Hours + ":" + Minutes;
        return Time;
    }
    
    public boolean timeOfDay(){
        if(hours < 9 || hours > 18){
            isNight = true;
        } else {
            isNight = false;
        }
        return isNight;
    }
    
    public void resetTime(){
        minutes = -1;
        hours = 0;
        days = 0;
        isNight = true;
    }
}