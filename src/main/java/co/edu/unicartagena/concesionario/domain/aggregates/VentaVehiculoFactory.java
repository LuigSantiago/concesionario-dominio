package co.edu.unicartagena.concesionario.domain.aggregates;
 
import co.edu.unicartagena.concesionario.domain.entities.ModeloVehiculo;
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import co.edu.unicartagena.concesionario.domain.valueobjects.Matricula;
import java.time.LocalDate;
import java.util.UUID;
 
public class VentaVehiculoFactory {
 
    private VentaVehiculoFactory() {
    }
 
    public static VentaVehiculo crear(String clienteId, String vendedorId,
                                      ModeloVehiculo modelo, Matricula matricula,
                                      LocalDate fechaVenta) {
        if (modelo == null) {
            throw new ExcepcionDominio("El modelo es obligatorio");
        }
        return new VentaVehiculo(
                UUID.randomUUID().toString(),
                clienteId,
                vendedorId,
                modelo.getId(),
                modelo.getOpcionesDisponibles(),
                modelo.getPrecio(),
                matricula,
                fechaVenta);
    }
}
