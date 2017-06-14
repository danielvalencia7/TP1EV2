tp1_Ev2

maven's project

Lo primero que he hecho, ha sido hacer un gitclone a un proyecto anterior con fallos, tras indangar y descubrir los errores, me he puesto a corregirlos.
 Abrí la aplicación eclipse y construí un proyecto maven, con sus correspondientes target, src..
Después de eso, comprobamos que el pom.xml estaba bien construido en formato maven, que dio problemas con los acentos ya que teníamos una versión de maven antigua, nos daba el problema de UTF-8.

Tras descargar la última versión de maven, nos funcionó bien y pudimos comprobar que estaba puesto en formato maven.

## Para compilar ##

    $ mvn compile    

Tras esto, hacemos un 

     $ mvn package 
y nos creará la carpeta target si todo ha salido bien. Después de haber hecho esto con el comando 

    ** $java -cp target/daniel-0.0.1-SNAPSHOT.jar empresa.Empresa**
 nos arranca el programa y vemos que funciona correctamente.


Con el programa bien, subimos el proyecto a Github

Este programa lo que hace es gestionar los distintos departamentos y empleados de una empresa.

Tiene distintas clases con las que con cada una puedes gestionar una parte de la empresa.

Es muy sencillo de utlizar, solo hay que seguir las instrucciones del menú.
Para usarlo, hay que pulsar cualquiera de estos números y hará lo que pone.

	        	 1. añadir empleado
			2. mostrar un empleado
			3. modificar empleado
			4. borrar empleado
			5. listar empleados
			0. salir
			
