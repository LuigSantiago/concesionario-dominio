package co.edu.unicartagena.concesionario.domain.entities;
 
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import co.edu.unicartagena.concesionario.domain.valueobjects.Dinero;
import java.util.Objects;
 
public class OpcionSeleccionada {
 
    private final String id; // identidad
    private final String nombre;
    private final Dinero precio;
 
    public OpcionSeleccionada(String id, String nombre, Dinero precio) {
        if (id == null || id.isBlank()) {
            throw new ExcepcionDominio("El id de la opcion es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new ExcepcionDominio("El nombre de la opcion es obligatorio");
        }
        this.id = id;
        this.nombre = nombre;
        this.precio = Objects.requireNonNull(precio, "El precio es obligatorio");
    }
 
    public String getId() {
        return id;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public Dinero getPrecio() {
        return precio;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpcionSeleccionada)) return false;
        return id.equals(((OpcionSeleccionada) o).id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}