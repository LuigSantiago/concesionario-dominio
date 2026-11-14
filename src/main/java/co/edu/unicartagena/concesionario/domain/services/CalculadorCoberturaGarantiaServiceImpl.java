package co.edu.unicartagena.concesionario.domain.services;
 
import co.edu.unicartagena.concesionario.domain.entities.GarantiaVehiculo;
import co.edu.unicartagena.concesionario.domain.entities.PiezaRepuesto;
import co.edu.unicartagena.concesionario.domain.specifications.PiezaCubiertaPorGarantiaSpecification;
import co.edu.unicartagena.concesionario.domain.valueobjects.Dinero;
import java.time.LocalDate;
 
public class CalculadorCoberturaGarantiaServiceImpl
        implements CalculadorCoberturaGarantiaService {
 
    private final PiezaCubiertaPorGarantiaSpecification piezaCubierta =
            new PiezaCubiertaPorGarantiaSpecification();
 
    @Override
    public Dinero calcularCostoPiezaParaCliente(GarantiaVehiculo garantia,
                                                PiezaRepuesto pieza,
                                                LocalDate fechaServicio) {
        if (piezaCubierta.isSatisfiedBy(garantia, pieza, fechaServicio)) {
            return Dinero.cero(pieza.getPrecioVenta().getMoneda());
        }
        return pieza.getPrecioVenta();
    }
}