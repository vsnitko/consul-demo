package com.example.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AccessController {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @GetMapping("/access")
    public String invokeHelloService() {
        final URI uri = discoveryClient
            .getInstances("Hello-service")
            .stream()
            .map(ServiceInstance::getUri)
            .findFirst()
            .map(s -> s.resolve("/hello"))
            .orElseThrow();
        return restTemplate.getForObject(uri, String.class);
    }

}
