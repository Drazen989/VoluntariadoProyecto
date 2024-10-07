package proyecto.voluntariado;

public class Administrativo extends Personas {
    private String area;

    public Administrativo(String nombre, String primerApellido, String segundoApellido, String rut, String area) {
        super(nombre, primerApellido, segundoApellido, rut);
        this.area = area;
    }

    public String getArea() {
        return area;
    }
    
    public void setArea(String area){
        this.area = area;
    }

    @Override
    public String getNombre() {
        return "Administrativo: " + super.getNombre();
    }
}

