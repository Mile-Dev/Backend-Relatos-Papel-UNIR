# back-end-eureka

Example implementation of a registration server using Spring Cloud Netflix Eureka with Dockerfile

[Official Documentation](https://cloud.spring.io/spring-cloud-netflix/reference/html/)

[More information](https://www.baeldung.com/spring-cloud-netflix-eureka)

To compile and build the project you can run the command ``mvn clean package``

You can deploy the project on Railway using the following button:

[![Deploy on Railway](https://railway.app/button.svg)](https://railway.app/template/HM8cFB?referralCode=jesus-unir)


If you want to deploy this project within an entire Spring microservices ecosystem, you can use the following button:

[![Deploy on Railway](https://railway.app/button.svg)](https://railway.app/template/f6CKpT?referralCode=jesus-unir)


## Cómo utilizar este repositorio AMBIENTE DEV con DOCKER

- Cómo hacer deploy del proyecto?

1. Construir la imagen
```

docker build -t dguachamin/relatos-ms-books:8089 .
docker push dguachamin/relatos-ms-books:8089

```

3. En el server debe estar instalado y configurado Docker
4. Bajar la imagen el server a deployar

```
sudo docker pull dguachamin/relatos-ms-books:8089
sudo docker run -d --network host --name relatos-ms-books-8089 --restart always dguachamin/relatos-ms-books:8089
sudo docker start relatos-ms-books:8089
```