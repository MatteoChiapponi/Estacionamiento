
package bussinesLogic.models;

public class Data {
    private String tiempoTotal;
    private int precioTotal;

    public Data(String tiempoTotal, int precioTotal) {
        this.tiempoTotal = tiempoTotal;
        this.precioTotal = precioTotal;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(String tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }
    
    
}
