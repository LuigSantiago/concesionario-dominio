package co.edu.unicartagena.concesionario.domain.entities;
 
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
import co.edu.unicartagena.concesionario.domain.exceptions.GarantiaExpiradaException;
import co.edu.unicartagena.concesionario.domain.valueobjects.Matricula;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
 
class GarantiaVehiculoTest {
 
    @Test
    void debeLanzarGarantiaExpiradaSiLaGarantiaYaVencio() {
        GarantiaVehiculo garantiaVencida = new GarantiaVehiculo(new Matricula("ABC123"),
                LocalDate.now().minusDays(1), List.of("PZ-ALT"));
 
        assertThrows(GarantiaExpiradaException.class,
                () -> garantiaVencida.validarVigenteEn(LocalDate.now()));
    }
 
    @Test
    void noDebeLanzarExcepcionSiLaGarantiaEstaVigente() {
        GarantiaVehiculo garantiaVigente = new GarantiaVehiculo(new Matricula("ABC123"),
                LocalDate.now().plusMonths(6), List.of("PZ-ALT"));
 
        assertDoesNotThrow(() -> garantiaVigente.validarVigenteEn(LocalDate.now()));
    }
}
