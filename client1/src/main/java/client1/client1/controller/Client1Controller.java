package client1.client1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client1Controller {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private RestTemplate restTemplate= new RestTemplate();

    @GetMapping("/callClientOne")
    public ResponseEntity<String> callFirstClient() throws Exception {
        String client1ResponseString = "Response from client1";

        return new ResponseEntity<String>(client1ResponseString, HttpStatus.OK);
    }

    @GetMapping("/callClientTwoThroughClientOne")
    public ResponseEntity<String> callSecondClient() throws Exception {
        String client1ResponseString = "Response from client1";
        try {
            return new ResponseEntity<String>(restTemplate.getForObject(getBaseUrl() + "/callClientTwo", String.class ), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(restTemplate.getForObject(getBaseUrl() + "/callClientTwo", String.class ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getBaseUrl(){
        ServiceInstance instance = loadBalancerClient.choose("CLIENT2");
        return instance.getUri().toString();
    }


}
