package observer;

import java.util.Observable;



public class Main {
    public static void main(String args[]) {
        
        Observable pacient = new Covid19Pacient("Yassine", 24);
        Observable pacient2 = new Covid19Pacient("Maximiliano",18);

        new PacientObserverGUI(pacient);
        new PacientSymptomGUI((Covid19Pacient)pacient);
        
        new PacientObserverGUI(pacient2);
        new PacientSymptomGUI((Covid19Pacient)pacient2);
    }
}
