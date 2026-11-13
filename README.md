# concesionario-dominio
Aca voy a subir lo relacionado al CEA del concesionario
# Modelo de Dominio DDD - Gestión de Concesionario

Implementación de la capa de dominio en Java puro aplicando los patrones tácticos de **Domain-Driven Design (DDD)**, sin dependencia de frameworks ni bases de datos.

## Información Académica
* **Universidad:** Universidad de Cartagena
* **Programa:** Ingeniería de Software
* **CEA:** [Nombre de tu CEA]
* **Integrantes:**
  * [Tu Nombre Completo]
  * [Nombre Compañero 2]
  * [Nombre Compañero 3]

## Estructura del Dominio (`domain/`)
* `aggregates`: Raíz de agregado (`VentaVehiculo`), estados y fábrica (`VentaVehiculoFactory`).
* `entities`: Entidades del dominio (`ModeloVehiculo`, `OpcionSeleccionada`, `VehiculoUsado`, `GarantiaVehiculo`, `PiezaRepuesto`).
* `valueobjects`: Objetos de valor inmutables (`Dinero`, `Matricula`).
* `services`: Servicios de dominio para reglas que involucran múltiples entidades (`CalculadorCoberturaGarantiaService`).
* `specifications`: Reglas de negocio encapsuladas (`PiezaCubiertaPorGarantiaSpecification`).
* `events`: Eventos de dominio (`VehiculoVendidoEvent`).
* `exceptions`: Excepciones específicas del negocio (`ExcepcionDominio`, `GarantiaExpiradaException`).

## Requisitos y Ejecución de Pruebas
* **Java:** JDK 17 o superior
* **Gestor de dependencias:** Apache Maven 3.8+

Para ejecutar las pruebas unitarias del modelo de dominio desde la terminal:

```bash
mvn test