package co.edu.unicartagena.concesionario.domain.services;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
 
import co.edu.unicartagena.concesionario.domain.entities.GarantiaVehiculo;
import co.edu.unicartagena.concesionario.domain.entities.PiezaRepuesto;
import co.edu.unicartagena.concesionario.domain.valueobjects.Dinero;
import co.edu.unicartagena.concesionario.domain.valueobjects.Matricula;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
 
class CalculadorCoberturaGarantiaServiceTest {
 
    private final CalculadorCoberturaGarantiaService servicio =
            new CalculadorCoberturaGarantiaServiceImpl();
 
    private static Dinero cop(String valor) {
        return new Dinero(new BigDecimal(valor), "COP");
    }
 
    @Test
    void debeCobrarCeroSiVehiculoEstaEnGarantiaYPiezaEstaCubierta() {
        GarantiaVehiculo garantia = new GarantiaVehiculo(new Matricula("ABC123"),
                LocalDate.now().plusYears(1), List.of("PZ-ALT"));
        PiezaRepuesto alternador = new PiezaRepuesto("PZ-ALT", "Alternador", cop("800000"));
 
        Dinero costo = servicio.calcularCostoPiezaParaCliente(garantia, alternador, LocalDate.now());
 
        assertEquals(0, costo.getMonto().compareTo(BigDecimal.ZERO));
    }
 
    @Test
    void debeCobrarElPrecioDeVentaSiLaPiezaNoEstaCubierta() {
        GarantiaVehiculo garantia = new GarantiaVehiculo(new Matricula("ABC123"),
                LocalDate.now().plusYears(1), List.of("PZ-ALT"));
        PiezaRepuesto frenos = new PiezaRepuesto("PZ-FRE", "Pastillas de freno", cop("500000"));
 
        Dinero costo = servicio.calcularCostoPiezaParaCliente(garantia, frenos, LocalDate.now());
 
        assertEquals(cop("500000"), costo);
    }
 
    @Test
    void debeCobrarElPrecioDeVentaSiLaGarantiaYaVencio() {
        GarantiaVehiculo garantiaVencida = new GarantiaVehiculo(new Matricula("ABC123"),
                LocalDate.now().minusDays(1), List.of("PZ-ALT"));
        PiezaRepuesto alternador = new PiezaRepuesto("PZ-ALT", "Alternador", cop("800000"));
 
        Dinero costo = servicio.calcularCostoPiezaParaCliente(garantiaVencida, alternador,
                LocalDate.now());
 
        assertEquals(cop("800000"), costo);
    }
}
