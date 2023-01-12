import GUI.WindowMain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        WindowMain wMain = new WindowMain();
        wMain.setVisible(true);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        System.out.println("Hora actual: " + dateFormat.format(date));
    }
}
