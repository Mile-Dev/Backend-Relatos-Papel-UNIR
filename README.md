# Backend-Relatos-Papel-UNIR
Backend Spring boot - Mysql
Seccion Back end de la aplicacion para relatos de papel contiene 2 directorios, payments en el cual estara el api de pagos y catalogue que contendra el api para realizar la gestion de los libros y busquedas pertinentes




## Cómo utilizar este repositorio AMBIENTE DEV con DOCKER

- Cómo hacer deploy del proyecto?

1. Construir la imagen
```

docker build -t dguachamin/relatos-ms-payments:8083 .
docker push dguachamin/relatos-ms-payments:8083

```

3. En el server debe estar instalado y configurado Docker
4. Bajar la imagen el server a deployar

```
sudo docker pull dguachamin/relatos-ms-payments:8083
sudo docker run -d --network host --name relatos-ms-payments-8083 --restart always dguachamin/relatos-ms-payments:8083
sudo docker start relatos-ms-payments-8083
```