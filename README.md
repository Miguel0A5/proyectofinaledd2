**Módulo 1.- Organizacíon secuencial Miguel Gómez**

Introducción
Este proyecto en Java implementa una aplicación de consola que demuestra la organización secuencial de archivos, una técnica clásica de almacenamiento donde los registros se guardan uno tras otro en el orden en que se insertan.

El objetivo es crear y administrar un archivo de alumnos que permita:
- Registrar alumnos con su nombre y promedio.
- Mostrar la lista completa en el orden de registro.
- Buscar alumnos por nombre.
- Ordenar la lista por nombre (alfabéticamente) o por promedio.
- Calcular el promedio general del grupo.
- Exportar un reporte en texto plano.

El código está escrito de forma modular y orientada a objetos, con un menú principal general y un submenú especializado en el módulo de organización secuencial.

Estructura de menús
Menú Principal (MenuPrincipal)
Es la puerta de entrada de la aplicación. Presenta las opciones principales del proyecto general y permite enlazar distintos módulos.
En este caso, la opción relevante es:
1.- Organización secuencial: abre el submenú de manejo de alumnos.

Cuando el usuario elige la opción 1, se crea una instancia de MiguelA5Gomez (el submódulo de organización secuencial) y se llama a su método ejecutar().

Submenú Organización Secuencial (MiguelA5Gomez)
Es el módulo funcional que gestiona el registro de alumnos con archivos secuenciales. Opciones:
1.- Agregar alumno – Solicita nombre y promedio, y los guarda en el archivo alumnos.txt.
2.- Mostrar todos – Muestra todos los registros en el orden en que fueron guardados.
3.- Buscar alumno por nombre – Busca un alumno de forma secuencial (leyendo uno por uno).
4.- Mostrar alumnos por orden alfabético – Ordena en memoria los registros por nombre y los muestra.
5.- Mostrar alumnos por promedio (mayor a menor) – Ordena en memoria los registros de acuerdo a su promedio.
6.- Calcular promedio general – Calcula y muestra el promedio grupal.
7.- Exportar reporte – Genera un archivo reporte.txt con todos los datos.
8.- Salir – Cierra el módulo.

Todas las entradas se validan para evitar errores de formato y mantener una experiencia de usuario robusta.

Clases principales
- Proyectofinal: punto de arranque de todo el proyecto. Llama al menú principal.
- MenuPrincipal: menú raíz. Invoca al módulo de organización secuencial (o a otros módulos en el futuro).
- MiguelA5Gomez: submenú específico que maneja la lógica de interacción con el usuario para este módulo.
- Alumno: modelo de datos. Representa cada alumno con nombre, promedio y método para saber si está aprobado.
- ArchivoSecuencial: gestiona la lectura y escritura del archivo alumnos.txt, guardando los registros uno tras otro en el orden en que se insertan.
- GestorAlumnos: la lógica de negocio. Usa ArchivoSecuencial para agregar, buscar, ordenar, calcular promedios y exportar reportes.

Detalle del funcionamiento
Organización secuencial
Cada vez que se agrega un alumno, el programa abre el archivo en modo “append” para añadir el registro al final, sin borrar lo anterior.
Para leer o buscar, recorre el archivo de principio a fin, cargando los registros en memoria para mostrarlos u ordenarlos.

Búsquedas y ordenamientos
- Búsqueda por nombre: se leen todos los registros y se compara cada uno.
- Orden alfabético y orden por promedio: se ordena una lista en memoria usando Comparator, sin alterar el archivo físico.

Cálculo de promedio general
Suma los promedios de todos los registros y divide entre la cantidad de alumnos.

Exportación de reportes
Crea un archivo de texto reporte.txt con un resumen legible, útil para respaldos o impresión.

Validaciones y experiencia de usuario
Se atrapan excepciones como NumberFormatException para evitar fallos si el usuario escribe algo que no es número.
Los menús confirman la finalización o informan cuando no se encuentra un alumno.

Integración y estilo de código
Cada módulo tiene su propia clase y se comunica a través de objetos.
Se sigue una convención clara para Scanner:
- entrada en el menú principal.
- sc en los submenús internos.
Esto permite identificar de un vistazo el origen de las entradas de usuario.

Progreso alcanzado
Con este desarrollo, el proyecto cuenta con:
- Un sistema de registro de alumnos totalmente funcional que demuestra la organización secuencial de archivos.
- Búsqueda, ordenamientos y reportes listos para ser usados en un contexto académico.
- Código modular, limpio y bien comentado que facilita futuras ampliaciones.

Resumen de dudas resueltas
Durante el desarrollo se abordaron y resolvieron los siguientes puntos:
- Comprensión de organización secuencial: se explicó que los registros se guardan uno tras otro y se leen en orden.
- Diseño modular: se dividió el código en clases independientes (Alumno, ArchivoSecuencial, GestorAlumnos, etc.) para mantener claridad y escalabilidad.
- Búsquedas y ordenamientos: se implementaron de forma que no rompan la lógica de archivo secuencial (el archivo original permanece intacto).

Con este módulo, la práctica de Estructura de Datos 2 queda resuelta: demuestra cómo almacenar y procesar información en archivos secuenciales de forma ordenada, segura y extensible.

# Módulo 2 – Búsqueda Secuencial
*Autor:* Miguel Tress y Christian Axel 

## Introducción
Este proyecto en Java implementa un módulo de *búsqueda secuencial de alumnos* mediante una aplicación de consola. La técnica de búsqueda secuencial consiste en recorrer un conjunto de registros uno por uno hasta encontrar el que coincide con un criterio de búsqueda.

El objetivo principal de este módulo es permitir la gestión de un grupo de alumnos, incluyendo su registro, búsqueda, visualización y eliminación, de manera sencilla y con interacción directa con el usuario a través de un menú.

El código está desarrollado siguiendo principios de *programación orientada a objetos*, con métodos claramente definidos para cada operación y un flujo de ejecución controlado mediante un menú principal.

---

## Funcionalidades del Módulo

El módulo permite:
1. *Registrar alumnos*: Solicita matrícula, nombre y promedio, validando la entrada para asegurar formato correcto.
2. *Buscar alumnos por matrícula*: Implementa búsqueda secuencial con opción de ver el proceso paso a paso.
3. *Mostrar todos los alumnos*: Lista los registros en el orden en que fueron agregados.
4. *Dar de baja a un alumno*: Permite eliminar un registro y reorganizar el arreglo para no dejar huecos.
5. *Ejemplo gráfico de búsqueda*: Muestra visualmente cómo se realiza la búsqueda secuencial.
6. *Explicación conceptual*: Proporciona información sobre la técnica de búsqueda secuencial, su complejidad y ventajas.

Todas las entradas del usuario se validan para evitar errores y asegurar una experiencia consistente y robusta.

---

## Estructura del Código

### Clase BusquedaAlumnos
Es la clase central del módulo. Contiene:
- *Atributos de clase:*
  - AlumnosSec[] alumnos – Arreglo que almacena hasta 100 alumnos.
  - int total – Contador de alumnos registrados actualmente.
- *Método ejecutar()*: Controla el flujo principal del módulo, mostrando el menú, leyendo la opción del usuario y redirigiendo a los métodos correspondientes.
- *Métodos auxiliares:*
  - registrarAlumno(Scanner sc) – Valida y agrega un alumno al arreglo.
  - buscarAlumno(Scanner sc) – Busca un alumno por matrícula y permite ver el proceso de comparación.
  - mostrarAlumnos() – Imprime todos los alumnos registrados.
  - darDeBajaAlumno(Scanner sc) – Elimina un alumno y reorganiza el arreglo.
  - ejemploBusquedaSecuencial() – Demuestra visualmente la técnica de búsqueda secuencial con datos fijos.
  - explicarBusquedaSecuencial() – Explica la teoría detrás de la búsqueda secuencial, su complejidad temporal y espacial.

### Clase AlumnosSec
Define la *estructura de datos de cada alumno*.
- *Atributos:*
  - String matricula
  - String nombre
  - double promedio
- *Métodos:*
  - getMatricula(), getNombre(), getPromedio() – Devuelven los valores de los atributos.
  - toString() – Devuelve una representación en texto legible para mostrar en listas o reportes.

---

## Detalle del Funcionamiento

### Registro de Alumnos
- Se solicita matrícula, nombre y promedio.
- La matrícula debe tener *3 letras y 4 números* (Ej: ABC1234).
- El nombre solo acepta *letras y espacios*.
- El promedio debe ser un valor numérico entre 0 y 10.
- Cada registro se almacena en el arreglo alumnos y se incrementa el contador total.

### Búsqueda Secuencial
- El usuario ingresa la matrícula a buscar.
- El método recorre el arreglo desde el inicio hasta el final, comparando cada matrícula con la buscada.
- Opcionalmente, el usuario puede ver *cada comparación paso a paso*.
- La búsqueda devuelve el alumno encontrado o indica que no existe.

### Mostrar y Eliminar Alumnos
- mostrarAlumnos() lista los alumnos en el orden de registro.
- darDeBajaAlumno() busca la matrícula, elimina el alumno del arreglo y desplaza los elementos siguientes para no dejar huecos.

### Ejemplo Gráfico
- Se usa un arreglo de matrículas predefinidas para mostrar cómo la búsqueda secuencial compara cada elemento hasta encontrar el objetivo.
- Permite visualizar el concepto de “comparaciones” de manera clara.

### Explicación Conceptual
- La búsqueda secuencial tiene *complejidad O(n)* en el peor caso.
- Es simple de implementar y no requiere estructuras auxiliares.
- Es útil para arreglos pequeños o cuando el orden de los datos no importa.

---

## Validaciones y Experiencia de Usuario
- Se capturan excepciones como InputMismatchException para evitar que el programa falle si se ingresa un valor inválido.
- Los menús muestran mensajes claros sobre el estado de las operaciones (registro exitoso, alumno no encontrado, etc.).
- Se asegura que el usuario no pueda agregar alumnos con matrículas duplicadas.

---

## Integración y Estilo de Código
- Cada método tiene responsabilidades claras: registrar, buscar, mostrar o eliminar.
- Se sigue una convención consistente de nombres y se usa un Scanner compartido en los submenús para entradas del usuario.
- El código es *modular y fácil de mantener*, lo que permite futuras mejoras, como agregar persistencia en archivo o reportes automáticos.

---

## Progreso Alcanzado
Con este desarrollo, el proyecto ofrece:
- Un módulo de gestión de alumnos *totalmente funcional* usando búsqueda secuencial.
- Visualización paso a paso de las comparaciones.
- Eliminación de registros sin romper la estructura de datos.
- Explicación clara de la técnica de búsqueda secuencial y su aplicación práctica.