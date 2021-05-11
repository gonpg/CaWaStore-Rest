# CaWaStore-Rest

Este es el servicio interno de la aplicación web CaWaStore.

Este servicio recibe peticiones de la aplicación web principal para generar facturas de los pedidos y enviar correos electrónicos de bienvenida a los nuevos usuarios y a los compradores.

Se divide en tres paquetes: el controlador de la web que realiza la gestión de peticiones, el paquete con las entidades de la web para poder manejar los datos que se reciben y el paquete repositorio para realizar las busquedas en la base de datos que se requieran.

La aplicación se conecta a la misma base de datos que la aplciación web. Toda esta infomación se encuentra en un contenedor docker-compose junto a la base de datos, los balanceadores de carga y el servicio redis para cachear la aplicación web
