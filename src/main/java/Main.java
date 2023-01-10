import GUI.WindowMain;
import bussinesLogic.factorys.VehicleFactory;
import bussinesLogic.models.ParkingController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        
        WindowMain wMain = new WindowMain();
        wMain.setVisible(true);
/*
        // sacar la diferencia entre horas

        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        String in = txtHoraInicio.getText();
        String fin = txtHoraSalida.getText();
        int inicio = Integer.parseInt(in);
        int salida = Integer.parseInt(fin);
        int diferencia = (salida - inicio);
        String dif = Integer.toString(diferencia);
        txtTiempo.setText(dif);
*/
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        System.out.println("Hora actual: " + dateFormat.format(date));


    }
}
