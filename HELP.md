# Consul demo

### Install Consul

To install Consul go to [this page](https://developer.hashicorp.com/consul/install?product_intent=consul).
For Windows machines, you can start Consul with this command from the package with `consul.exe`
```console
consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=127.0.0.1
```

`consul-data` in the command is a package name where consul related files will be stored.

After this, you should see Consul web UI by `localhost:8500`

### Use Spring Boot with Consul

1. Create 2 microservices, give them names with `spring.application.name` property, define ports with `server.port` property
2. Import consul with Maven in both microservices
    ```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-consul-all</artifactId>
        <version>4.0.3</version>
    </dependency>
    ```
    By default, Spring will search Consul by `localhost:8500` path. You can change this with the property
    ```
    spring
      cloud:
        consul:
          host: localhost
          port: 8500
    ```
3. After these steps you will see your services in Consul web UI. You will probably need to include `spring-actuator` 
dependency to help Consul make a proper healthcheck
4. Example of using Spring Boot with Consul you can find in this repository
