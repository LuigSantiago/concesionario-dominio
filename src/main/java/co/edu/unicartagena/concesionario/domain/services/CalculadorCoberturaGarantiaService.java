package co.edu.unicartagena.concesionario.domain.services;
 
import co.edu.unicartagena.concesionario.domain.entities.GarantiaVehiculo;
import co.edu.unicartagena.concesionario.domain.entities.PiezaRepuesto;
import co.edu.unicartagena.concesionario.domain.valueobjects.Dinero;
import java.time.LocalDate;
 
public interface CalculadorCoberturaGarantiaService {
 
    Dinero calcularCostoPiezaParaCliente(GarantiaVehiculo garantia,
                                         PiezaRepuesto pieza,
                                         LocalDate fechaServicio);
}