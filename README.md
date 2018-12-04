

**GestApaMS 0.1 (Gestion Apartamentos Microservicios)**

_Memoria borrador(muchos de los datos son notas para mi.)_

�ndice

1. Informaci�n general.

2. Entrono Cloud (si se puede)

3. Bases de Datos y Modelos

4. Funcionamiento previsto (lo que quiero que haga de forma generalizada)

5. Funcionamiento (Hasta lo que he podido hacer)

6. Spring Security

7. Microservicios

8. Biograf�a y b�squedas

9. Herramientas utilizadas



















**Informaci�n general de la Aplicaci�n**

        Se trata de una aplicaci�n con la que poder gestionar apartamentos vacacionales. No se trata de una gesti�n de reservas (aunque se podr�a incluir si fuera necesario). Pensado para empresas que gestione una cantidad elevada de apartamentos o pisos.  Valido tambi�n para hoteles y hostales(con pocas modificaciones).

        A parte del apartamento, es posible gestionar otros servicios como Parkings, Servicio Limpieza y escalonar la aplicaci�n para cualquier otro servicio necesario.

        La aplicaci�n est� orientada a funcionar con Microservicios. Debido a limitaciones con el hardware, no se ha podido fraccionar la aplicaci�n en el proyecto, pero en la memoria se indican los distintos microservicios que habr�a.

**Entrono Cloud (si se puede)**

- --Spring Configure Server
- --Spring Eureka Server
- --Spring Zuul Server
- --Hystryx y Ribbon

**Bases de Datos y Modelos**

        Las bases de datos se han realizado en H2(in memory) para que se pueda probar mejor

1. a)UsuariosDB: Base de datos de los usuarios que tendr�n acceso y su nivel de acceso; Administrador, usuario, limpieza, etc.

1. b)ApartamentosDB: Base de datos de los apartamentos/pisos que dispone el cliente para su ocupaci�n.

1. c)ReservasDB: Base de datos de las reservas que tiene el cliente.

- --Cada microservicios con su base de datos si la necesita
- --No relacional (Feign)
- --H2 para mejor comprobaci�n del proyecto por parte del profesor
- --Generadores de Datos
- --Modelos utilizados (Usuario, Apartamento, PlazaParking, etc. )

**Funcionamiento previsto (lo que quiero que haga de forma generalizada)**

- --esquema funcionalidad (si da tiempo)
- --esquema de usuario (si da tiempo)

**Funcionamiento final (Hasta lo que he podido hacer)**

- --Cliente web: p�gina web donde accede el cliente. La parte Front de Usuarios y Apartamentos est� hecha, con ayuda de Thymeleaf para volcar datos y enviar los dtos

**Spring Security**

        Explicar el Microservicio para autentificarse en la web. Uso de Feign para consultar usuario y password. Definir recursos y terminar de definir el acceso a las paginas seg�n el perfil.

**Microservicios**

1. Usuarios(&quot;gesti�n-usuarios&quot;)

Microservicio encargado de la gesti�n de los usuarios y todos los datos relacionados. Comunica directamente con la base de datos, en este caso H2 en memoria para pruebas.

1. Apartamentos(&quot;gesti�n-apartamentos&quot;)

Microservicio encargado de la gesti�n de los apartamentos/pisos/habitaciones que pueda tener el cliente. Base de datos H2 en memoria para pruebas. Tambi�n se incluye la gesti�n de los propietarios. Esta funci�n podr�a separarse en otro Microservicio.

1. Reservas (&quot;gesti�n-reservas&quot;)

Microservicio encargado de gestionar las reservas. Base de datos propia en H2 en memoria. Tambi�n se encargar�a de procesar las reservas entrantes de otros sitios, he utilizado por ejemplo de Booking.com, utilizo un response de prueba y gracias a un conversor de XML a objeto puedo tratar y transformar esas reservas a las de la propia aplicaci�n.



**Biograf�a y b�squedas**

 Lo que he tenido que buscar y de donde lo he buscado para hacerlo.

- --Thymeleaf
- --SpringSecurity
- --JAXB API (xml to Object)

**Herramientas utilizadas**

- --Intellij

