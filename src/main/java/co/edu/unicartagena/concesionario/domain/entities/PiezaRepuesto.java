package co.edu.unicartagena.concesionario.domain.entities;
 
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import co.edu.unicartagena.concesionario.domain.valueobjects.Dinero;
import java.util.Objects;
 
public class PiezaRepuesto {
 
    private final String id; // identidad
    private final String nombre;
    private final Dinero precioVenta;
 
    public PiezaRepuesto(String id, String nombre, Dinero precioVenta) {
        if (id == null || id.isBlank()) {
            throw new ExcepcionDominio("El id de la pieza es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new ExcepcionDominio("El nombre de la pieza es obligatorio");
        }
        this.id = id;
        this.nombre = nombre;
        this.precioVenta = Objects.requireNonNull(precioVenta, "El precio de venta es obligatorio");
    }
 
    public String getId() {
        return id;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public Dinero getPrecioVenta() {
        return precioVenta;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PiezaRepuesto)) return false;
        return id.equals(((PiezaRepuesto) o).id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}