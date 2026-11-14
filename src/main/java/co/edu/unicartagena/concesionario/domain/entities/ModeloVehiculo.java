package co.edu.unicartagena.concesionario.domain.entities;
 
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import co.edu.unicartagena.concesionario.domain.valueobjects.Dinero;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
 
public class ModeloVehiculo {
 
    private final String id; // identidad
    private final String marca;
    private final String nombre;
    private final int cilindrada;
    private Dinero precio;
    private final Set<String> opcionesDisponibles = new HashSet<>();
 
    public ModeloVehiculo(String id, String marca, String nombre,
                          int cilindrada, Dinero precio) {
        if (id == null || id.isBlank()) {
            throw new ExcepcionDominio("El id del modelo es obligatorio");
        }
        if (marca == null || marca.isBlank()) {
            throw new ExcepcionDominio("La marca es obligatoria");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new ExcepcionDominio("El nombre del modelo es obligatorio");
        }
        if (cilindrada <= 0) {
            throw new ExcepcionDominio("La cilindrada debe ser mayor a 0");
        }
        this.id = id;
        this.marca = marca;
        this.nombre = nombre;
        this.cilindrada = cilindrada;
        this.precio = Objects.requireNonNull(precio, "El precio es obligatorio");
    }
 
    public void cambiarPrecio(Dinero nuevoPrecio) {
        this.precio = Objects.requireNonNull(nuevoPrecio, "El precio es obligatorio");
    }
 
    public void habilitarOpcion(String opcionId) {
        if (opcionId == null || opcionId.isBlank()) {
            throw new ExcepcionDominio("El id de la opcion es obligatorio");
        }
        opcionesDisponibles.add(opcionId);
    }
 
    public String getId() {
        return id;
    }
 
    public String getMarca() {
        return marca;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public int getCilindrada() {
        return cilindrada;
    }
 
    public Dinero getPrecio() {
        return precio;
    }
 
    public Set<String> getOpcionesDisponibles() {
        return Collections.unmodifiableSet(opcionesDisponibles);
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModeloVehiculo)) return false;
        return id.equals(((ModeloVehiculo) o).id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
