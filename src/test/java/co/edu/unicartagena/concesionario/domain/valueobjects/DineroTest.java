package co.edu.unicartagena.concesionario.domain.valueobjects;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
import co.edu.unicartagena.concesionario.domain.exceptions.ExcepcionDominio;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
 
class DineroTest {
 
    @Test
    void noDebePermitirCrearDineroConMontoNegativo() {
        assertThrows(ExcepcionDominio.class,
                () -> new Dinero(new BigDecimal("-50000"), "COP"));
    }
 
    @Test
    void noDebePermitirCrearDineroSinMoneda() {
        assertThrows(ExcepcionDominio.class,
                () -> new Dinero(new BigDecimal("50000"), " "));
    }
 
    @Test
    void noDebePermitirSumarMonedasDistintas() {
        Dinero pesos = new Dinero(new BigDecimal("1000"), "COP");
        Dinero dolares = new Dinero(new BigDecimal("10"), "USD");
 
        assertThrows(ExcepcionDominio.class, () -> pesos.sumar(dolares));
    }
 
    @Test
    void sumarDebeDevolverUnDineroNuevoSinCambiarLosOriginales() {
        Dinero a = new Dinero(new BigDecimal("1000"), "COP");
        Dinero b = new Dinero(new BigDecimal("500"), "COP");
 
        Dinero resultado = a.sumar(b);
 
        assertEquals(new Dinero(new BigDecimal("1500"), "COP"), resultado);
        assertEquals(new Dinero(new BigDecimal("1000"), "COP"), a);
    }
}