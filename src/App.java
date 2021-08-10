
//Our main method which just contains a couple of lines of code calling the other classes
public class App {
    public static void main(String[] args) throws Exception 
    {
        //Timer
        timer mainTimer = new timer();

        getSubDir getDirectories = new getSubDir();                                                 //Creates a getSubDir object

        getDirectories.firstEntry("C:/Users/CJ/Desktop/Program Projects");                                 //Hardcoded path to test the app on, changed to command line args
        getDirectories.startTreeDisplay();
        //getDirectories.displayFiles();
        //getDirectories.displayInfo();

        
        mainTimer.startStop = false;
        mainTimer.stopTime();
        mainTimer.printTime("main");
        
    }
}
