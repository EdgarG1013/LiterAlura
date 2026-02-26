# 📚 LiterAlura - Catálogo de Libros en Java POO

## 📌 Descripción

**LiterAlura** es una aplicación desarrollada en Java que implementa un catálogo de libros con interfaz de consola. Permite buscar, registrar y consultar libros y autores desde la API **Gutendex**, una base de datos de libros de dominio público.

El flujo de uso es simple:

1. Seleccionar una opción del menú principal.
2. Buscar libros por título desde la API.
3. Ver los libros y autores registrados en la base de datos.
4. Consultar autores vivos en un año específico.
5. Filtrar libros por idioma.

## 📂 Archivos Principales

- **Principal.java**: Menú interactivo y flujo principal de la aplicación.
- **Libro.java**: Modelo de datos para representar un libro.
- **Autor.java**: Modelo de datos para representar un autor.
- **RecordLibro.java**: Record para mapear respuestas de la API (libros).
- **RecordAutor.java**: Record para mapear respuestas de la API (autores).
- **RecordRespuesta.java**: Record para mapear la respuesta general de la API.
- **LibroRepository.java**: Interfaz de acceso a datos para libros.
- **AutorRepository.java**: Interfaz de acceso a datos para autores.
- **ConsumoAPI.java**: Servicio para consumir la API externa de Gutendex.
- **ConvierteDatos.java**: Servicio para deserializar JSON a objetos Java.
- **IConvierteDatos.java**: Interfaz para la conversión de datos.

## 🛠️ Tecnologías Utilizadas

- **Java 11+** - Lenguaje de programación.
- **Programación Orientada a Objetos (POO)** - Arquitectura del proyecto.
- **Spring Data JPA** - Acceso a datos y ORM.
- **Gutendex API** - API externa para obtener información de libros.
- **Gson** - Librería para deserializar JSON a objetos Java.
- **HttpClient** - Cliente HTTP para consumir la API.
- **Scanner** - Entrada de datos desde consola.
- **Base de datos relacional** - Almacenamiento persistente (PostgreSQL, MySQL, etc.).

## 🎯 Funcionalidades Principales

### 1. Buscar Libro por Título
Permite buscar un libro en la API Gutendex por su título:

- Ingresa el nombre o parte del título del libro.
- La aplicación consulta la API y muestra los resultados.
- Verifica si el libro ya existe en la BD.
- Si no existe, lo registra automáticamente junto a sus autores.

**Ejemplo:**
```
Ingrese el titulo del libro que desea buscar
Don Quixote

Libro guardado exitosamente:
---- LIBRO ----
Título: Don Quixote
Idioma: en
Número descargas: 54234
Resumen: Don Quixote is a Spanish novel by Miguel de Cervantes...
Autores: [Cervantes, Miguel de]
```

### 2. Listar Libros Registrados
Muestra todos los libros que hay en la base de datos:

- Consulta la BD local.
- Muestra información completa de cada libro (título, idioma, autores, descargas).
- Si no hay libros, muestra un mensaje informativo.

### 3. Listar Autores Registrados
Muestra todos los autores registrados en la BD:

- Lista los nombres de autores almacenados.
- Incluye años de nacimiento y fallecimiento.
- Muestra los libros asociados a cada autor.

### 4. Listar Autores Vivos en un Año Específico
Permite buscar autores que estaban vivos en un año determinado:

- Ingresa un año (ej. 1605).
- La aplicación calcula si el autor estaba vivo (nació antes o en ese año y falleció después o aún vive).
- Filtra y muestra solo los autores que cumplan la condición.

**Ejemplo:**
```
Ingrese el año para listar autores vivos
1605

Autores vivos en el año 1605:
---- AUTOR ----
Nombre: Cervantes, Miguel de
Nacimiento: 1547
Fallecimiento: 1616
Libros: [Don Quixote, La Galatea]
```

### 5. Listar Libros por Idioma
Permite filtrar libros según su idioma:

- Ingresa el código del idioma (en, es, fr, de, pt, etc.).
- La aplicación busca todos los libros en ese idioma.
- Muestra información completa de cada libro encontrado.

**Ejemplo:**
```
Ingrese el código del idioma
es

Libros en es:
---- LIBRO ----
Título: Don Quixote
Idioma: es
Número descargas: 45123
Autores: [Cervantes, Miguel de]
```

## ⚙️ Arquitectura del Proyecto

### Estructura de Paquetes
```
com.edgarg1013.LiterAlura
├── Principal/
│   └── Principal.java          (punto de entrada - menú interactivo)
├── Model/
│   ├── Libro.java              (entidad de libro)
│   ├── Autor.java              (entidad de autor)
│   ├── RecordLibro.java        (mapeo de respuesta API - libros)
│   ├── RecordAutor.java        (mapeo de respuesta API - autores)
│   └── RecordRespuesta.java    (mapeo de respuesta general API)
├── Repository/
│   ├── LibroRepository.java    (acceso a datos - libros)
│   └── AutorRepository.java    (acceso a datos - autores)
└── service/
    ├── ConsumoAPI.java         (consumo de API externa)
    ├── ConvierteDatos.java     (deserialización JSON)
    └── IConvierteDatos.java    (interfaz de conversión)
```

### Flujo de Datos

1. **Usuario ingresa datos** → Principal.java recibe la entrada desde consola.
2. **Validación y procesamiento** → Principal.java valida opciones del menú.
3. **Búsqueda en BD o API** → Se consulta LibroRepository/AutorRepository o ConsumoAPI según corresponda.
4. **Consumo de API** → ConsumoAPI realiza peticiones HTTP a Gutendex.
5. **Deserialización** → ConvierteDatos convierte JSON a objetos Java usando Gson.
6. **Persistencia** → Los datos nuevos se guardan en la BD usando JPA.
7. **Presentación** → Los resultados se muestran al usuario en consola.

### Relaciones entre Entidades

- **Libro - Autor**: Relación **ManyToMany**
  - Un libro puede tener múltiples autores.
  - Un autor puede escribir múltiples libros.
  - Se gestiona mediante tabla intermedia `libro_autor`.

## ▶️ Cómo Ejecutar

### Requisitos Previos

- Java 11 o superior instalado en tu sistema.
- Maven instalado para gestionar dependencias.
- Conexión a Internet para acceder a la API Gutendex.
- Base de datos configurada (PostgreSQL, MySQL u otra compatible).

### Pasos para Ejecutar

1. **Clonar o descargar** este repositorio.

2. **Navegar al directorio del proyecto:**
   ```bash
   cd LiterAlura
   ```

3. **Configurar la base de datos** en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Compilar y ejecutar** con Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 📝 Ejemplo de Uso

```
------------------------------------------------
    Bienvenido a LiterAlura 
------------------------------------------------
Eliga una de las opciones para continuar
------------------------------------------------
1 - Buscar Libro por titulo
2 - Listar Libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar Libros por idioma
0 - Salir
------------------------------------------------
1

------------------------------------------------
Ingrese el titulo del libro que desea buscar
------------------------------------------------
Don Quixote

Libro guardado exitosamente:
---- LIBRO ----
Título: Don Quixote
Idioma: en
Número descargas: 54234
Resumen: Don Quixote is a Spanish novel...
Autores: [Cervantes, Miguel de]

```

## 📂 Referencias a Código

### Principal.java
- **Punto de entrada:** Método `main(String[] args)`
- **Estructura:** Menú principal con bucle do-while
- **Función:** Gestiona opciones del usuario y delega a métodos específicos
- **Métodos clave:**
  - `muestraElMenu()` → Muestra el menú y controla el flujo
  - `buscarLibroPorTitulo()` → Búsqueda en API y registro en BD
  - `listarLibrosRegistrados()` → Consulta BD de libros
  - `listarAutoresRegistrados()` → Consulta BD de autores
  - `listarAutoresEntreAnio()` → Filtra autores vivos en un año
  - `listarLibrosPorIdioma()` → Filtra libros por idioma

### ConsumoAPI.java
- **Método principal:** `obtenerDatos(String url)` → Realiza petition HTTP GET a Gutendex
- **Retorna:** JSON como String que contiene información de libros

### ConvierteDatos.java
- **Método principal:** `obtenerDatos(String json, Class<T> clase)` → Deserializa JSON a objetos Java
- **Utiliza:** Gson para la conversión
- **Genérico:** Funciona con cualquier clase de destino

### Repository
- **LibroRepository:**
  - `findAll()` → Obtiene todos los libros
  - `findByTituloIgnoreCase(String)` → Busca por título
  - `findByIdioma(String)` → Filtra por idioma
  - `save(Libro)` → Guarda un libro

- **AutorRepository:**
  - `findAll()` → Obtiene todos los autores
  - `findByNombre(String)` → Busca por nombre
  - `findAutoresVivosPorAnio(Integer)` → Query personalizada para filtrar autores vivos en un año

## 🚀 Mejoras Futuras

- ✅ **Búsqueda avanzada:** Por autor, año de publicación, número de descargas mínimo.
- ✅ **Estadísticas:** Mostrar libro más descargado, autor con más obras, etc.
- ✅ **Interfaz gráfica:** Migrar a Swing o JavaFX para mejor experiencia.
- ✅ **Exportación de datos:** Generación de reportes en PDF o Excel.
- ✅ **Sistema de ratings:** Permitir que usuarios califiquen libros.
- ✅ **Recomendaciones:** Sugerir libros basados en búsquedas anteriores.
- ✅ **Multi-idioma en la aplicación:** Soporte para múltiples lenguajes en la interfaz.

## 📄 Notas Importantes

- Los libros se buscan en la **API de Gutendex** que es de acceso gratuito.
- Solo se pueden registrar **libros de dominio público** através de esta API.
- La **BD local** almacena copias de los datos para consultas rápidas sin necesidad de llamadas a API.
- Las **relaciones ManyToMany** aseguran que no haya duplicación de autores en la base de datos.

## 📚 Licencia

Este proyecto es de práctica educativa para el **Oracle Next Education Challenge**.

## 👤 Autor

Desarrollado por **Edgar Stiven Garcia** como parte del Challenge de LiterAlura

---

