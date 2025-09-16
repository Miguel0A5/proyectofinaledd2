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