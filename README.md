# Microservices with Vaadin demo

This demo shows a Microservices Architecture implemented with [Spring Cloud Netflix](http://cloud.spring.io/spring-cloud-netflix/) and [Vaadin](https://vaadin.com).

# Architecture view



## Preprequisit

This steps should be done:

**1) Install Docker and docker-composite.**

## Building the demo

Run the following from the command line:
```
git clone https://github.com/micsigen/vaadin-microservices-demo.git
cd vaadin-microservices-demo
```

## Build the build Docker image

**1) Create a new docker image with the next command:**

```
docker build -t micsigen/vaadin-microservice-build:1.0-SNAPSHOT . 
```

This step create build docker image that contains all necessary library to the maven builds. In the further steps, maven 
build process will be used the build image. If you found `micsigen/vaadin-microservice-build` image on your registered docker 
images then the process run successfully.

Print docker images:
```
docker images
```

**2) Start the `discovery-server` and the `config-server` applications:**
```
docker-compose -f docker-compose-infra.yml up
```

This step in first time compile the `discovery-server` and `config-server` applications and wrap into docker images. 
After that create a new container from those images and start the servers on a common docker bridge.

You can check the running Eureka Discovery on page:
```
http://localhost:8761
```

**2) Open a new terminal window and start the `biz-application`, `admin-application`, `news-application`, `website-application` and `proxy-server` microservices:**
```
docker-compose -f docker-compose-runtime.yml up
```

This step start java processes on docker containers and connect to `discovery-server` and `config-server`.
This step start _Zuul_ reverse proxy and open 8080 port to the host machine.

Check the connected reverse proxy and website on the running Eureka Discovery on page:
```
http://localhost:8761
```

Open the application to run next URL on your favorite browser:
```
http://localhost:8080
```

**3) Open a new terminal window and start the `monitor-application` microservice:**
```
docker-compose -f docker-compose-monitor.yml up
```

This step start the monitor application with Hystrix technology. Open the 8083 port to the host machine.

Check the connected monitor application on the running Eureka Discovery on page:
```
http://localhost:8761
```

Open the application to run next URL on your favorite browser:
```
http://localhost:8083/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fturbine.stream&title=default

```

## Using the demo

**1) Point your browser to <http://localhost:8080>.**

You'll see the `website-application` embedding the `admin-application` and the `news-application` microservices.

This is the "edge service" implemented with Netflix Zuul. It acts as a reverse proxy, redirecting requests to the `website-application`, `news-application`, and `admin-application` instances using a load balancer provided by Netflix Ribbon with a _round robin_ strategy.

If you get a "Server not available" message, please wait until all the services are registered with the `discovery-server` (implemented with Netflix Eureka).

**2) Add, update, or delete data.**

Latest tweets from the companies you enter on the left (the `admin-application`) will be rendered on the right (the `news-application`).

The `admin-application`, and `news-application` instances (implemented with Vaadin) delegate CRUD operations to the `biz-application` (implemented with Spring Data Rest) using a load balancer (provided by Netflix Ribbon) with a _round robin_ strategy.