
package proyecto.voluntariado;
public class Resto {
    private String ocupacionActual;
    private String comoTeEnteraste;

    public Resto(String ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
    }

    public Resto(String ocupacionActual, String comoTeEnteraste) {
        this.ocupacionActual = ocupacionActual;
        this.comoTeEnteraste = comoTeEnteraste;
    }

    public String getOcupacionActual() {
        return ocupacionActual;
    }

    public void setOcupacionActual(String ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
    }

    public String getComoTeEnteraste() {
        return comoTeEnteraste;
    }

    public void setComoTeEnteraste(String comoTeEnteraste) {
        this.comoTeEnteraste = comoTeEnteraste;
    }
}
