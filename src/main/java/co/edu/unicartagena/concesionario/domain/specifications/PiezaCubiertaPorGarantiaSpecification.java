package co.edu.unicartagena.concesionario.domain.specifications;
 
import co.edu.unicartagena.concesionario.domain.entities.GarantiaVehiculo;
import co.edu.unicartagena.concesionario.domain.entities.PiezaRepuesto;
import java.time.LocalDate;
 
public class PiezaCubiertaPorGarantiaSpecification {
 
    public boolean isSatisfiedBy(GarantiaVehiculo garantia, PiezaRepuesto pieza,
                                 LocalDate fecha) {
        return garantia.estaVigenteEn(fecha) && garantia.cubre(pieza.getId());
    }
}
