package proyecto.voluntariado;

public class InformacionPersonal {
    private String genero;
    private String fechaNacimiento;
    private String correo;
    private int numero;
    private String region;
    private String comuna;

    public InformacionPersonal(String genero, String fechaNacimiento, String correo, int numero) {
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.numero = numero;
    }

    public String getGenero() {
        return genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public int getNumero() {
        return numero;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    
}
