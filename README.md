# Proyecto del Segundo Cuatrimestre Fundamentos de Programación (Curso  22-23)
Autor: Diego Terrón Hernández

uvus: JCT6889

Correo: dieterher@alum.us.es

En este proyecto trataremos una serie de datos sobre accidentes de avión.


## Estructura de las carpetas del proyecto
#
* **/src**: Directorio con el código fuente.
  * **fp.accidentes**: Paquete que contiene los tipos del proyecto.
  * **fp.accidentes.test**: Paquete que contiene las clases de test del proyecto.
  * **fp.common**: Paquete que contiene los tipos auxiliares del proyecto
  * **fp.utiles**:  Paquete que contiene las clases de utilidad. 
* **/data**: Contiene el dataset del proyecto
    * **plane_crashes.csv**: Archivo csv que contiene los datos de los accidentes.
    
## Estructura del *dataset*
#
El dataset original Airplane Crash and Fatalities se puede obtener de la URL https://www.kaggle.com/datasets/aiaiaidavid/airplane-crash-fatalities-since-1908-dv-03032020. Originalmente tiene 17 columnas y cada fila contiene datos sobre un accidente de avión desde el año 1908. El dataset usado en este proyecto tiene 12 columnas, 10 se han tomado del dataset original, y dos, el tipo de accidente y los nombres de los fallecidos, se han generado de forma aleatoria. También se han generado de forma aleatoria datos en algunas filas donde faltaba información, como la hora. A continuación se describen las 12 columnas del dataset:

* **date**: de tipo fecha, representa la fecha en la que se produjo el accidente.
* **time**: de tipo hora, representa la hora en la que se produjo el accidente.
* **location**: de tipo cadena, representa la ubicación del accidente.
* **operator**: de tipo cadena, representa la aerolínea u operadora del avión.
* **flight_tag**: de tipo cadena, representa el número de vuelo asignado por la operadora.
* **aircraft_type**: de tipo cadena, representa el modelo del avión.
* **aircraft_reg**: de tipo cadena, representa el registro OACI de la aeronave.
* **cn_ln**: de tipo cadena, representa el número de serie del avión.
* **accident_type**: de tipo cadena, representa el accidente producido.
* **fatalities**: de tipo entero, representa el número de fallecidos por el accidente.
* **total_crew**: de tipo entero, representa el número total de tripulantes del avión.
* **dead_names**: de tipo lista, representa los nombres de los fallecidos.

## Tipos implementados
#
Describe aquí los tipos que usas en tu proyecto.

### Tipo Base - Accidente
Los tipos que se han implementado en el proyecto son los siguientes:

**Propiedades**:

- _date_, de tipo LocalDate, consultable. Fecha del accidente.
- _time_, de tipo LocalTime, consultable. Hora del accidente.
- _location_, de tipo String, consultable. Ubicación del accidente.
- _aircraft_, de tipo Avion, consultable. Información del avión.
- _accidentType_, de tipo TipoAccidente. Tipo de accidente.
- _fatalities_, de tipo Integer, consultable. Número de muertes.
- _totalCrew_, de tipo Integer, consultable. Número de tripulantes.
- _deadNames_, de tipo ArrayList\<String>, consultable. Nombres de fallecidos.
- _deadly_, de tipo boolean, propiedad derivada que indica si hay fallecidos.
- _deathPercentage_, de tipo Float, propiedad derivada que indica el porcentaje de la tripulación que ha fallecido.

**Constructores**: 

- C1: Tiene un parámetro por cada propiedad básica del tipo.
- C2: Sólo tiene los parámetros date, time, y location.

**Restricciones**:
 
- C1: No puede haber más muertes que miembros de la tripulación.
- C2: La cantidad de nombres de fallecidos debe ser igual o menor que el número de fallecidos.

**Criterio de ordenación**: Por fecha.

### Tipos auxiliares
- Avion, record. Contiene los datos del avión _flightTag_, _aircraftType_, _aircraftReg_ y _cnLn_.
- TipoAccidente, enumerado. Puede tomar los valores SHOT_DOWN (derribado), MALFUNCTION (defectuoso), HIJACKED (secuestrado), WEATHER (clima), OTHER (otro), UNKNOWN (sin datos/no se sabe).


