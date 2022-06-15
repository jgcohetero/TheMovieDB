# The movie DB
* Esta carpeta contiene el código fuente para THe Movie DB

# Introduction

* La aplicación The Movie DB tiene la finalidad de consumir la API https://www.themoviedb.org/ y mostrar las películas de acuerdo a la opción seleccionada.
A su vez puede visualizar a detalle el contenido de cada película, incluyendo los tráilers que le corresponden. Esto a tráves de la API de Youtube para poder reproducir su contenido

# Pre-requisitos
* Manejo de endpoints
* Manejo de MVP
* Cosumo de API REST
* Navegación entre pantallas
* RecyclerView

# Empezar
1. Instalar Android Studio
2. Descarga en .zip
3. Descomprimir el archivo .zip
4. Importamos el proyecto a Android Studio
5. Generamos y ejecutamos la app

# TODO The Movie DB Offline
* La forma en que la aplicación pueda ser offline sería de acuerdo a como marca la documentación de Android Developer, la cual hace referencia a la persistencia de datos.
Es decir utilizar las dependencias de ROOM y FLOW:

   build.gradle - nivel de proyecto:
   ext {
      kotlin_version = "1.3.72"
      nav_version = "2.3.1"
      room_version = '2.3.0'
   }

   build.gradle - nivel de app
   implementation "androidx.room:room-runtime:$room_version"
   kapt "androidx.room:room-compiler:$room_version"

   // optional - Kotlin Extensions and Coroutines support for Room
   implementation "androidx.room:room-ktx:$room_version"

* Una vez que inyectemos la dependencia ya podremos hacer uso de su funcionalidad, lo cual consiste en crear Entidades o Modelos para los datos a almacenar, ejemplo:

   @Entity
   data class TheMovieOffline(
   @PrimaryKey val id: int
   @NonNull @ColumnInfo(name = "original_title") val originalTitle: String
   ...
   )

   Cabe destacar que la dependecia de ROOM maneja SQL como base de datos, es por ello que saber sobre base de datos relacionales.

* Una vez creadas las entidades debemos de realizar las consultas a través de una interfaz kotlin, para ello ROOM cuenta con decorador llamado @DAO, donde DAO significa "Data Access Object"

   @DAO
   interface TheMovieDao {
     @Query("SELECT * FROM themovieoffline ORDER BY name ASC")
   fun getAll(): List<Movie>
     ...
   }

* Una ve< llegada a esta parte se puede implementar una serie de clases para procesar la información una vez que la app se encuentre en modo offline.
