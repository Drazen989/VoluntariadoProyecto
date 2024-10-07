package proyecto.voluntariado;

public class Constructor extends Personas {
    private String proyecto;

    public Constructor(String nombre, String primerApellido, String segundoApellido, String rut, String proyecto) {
        super(nombre, primerApellido, segundoApellido, rut);
        this.proyecto = proyecto;
    }

    public String getProyecto() {
        return proyecto;
    }
    
    public void setProyecto(String proyecto){
        this.proyecto = proyecto;
    }

    @Override
    public String getNombre() {
        return "Constructor: " + super.getNombre();
    }
    
 
}
