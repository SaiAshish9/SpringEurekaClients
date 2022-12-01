package client2.client2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client2 {

    @GetMapping("/callClientTwo")
    public ResponseEntity<String> callSecondClient() throws Exception {
        String client1ResponseString = "Hey :: Response from client2";
        return new ResponseEntity<String>(client1ResponseString, HttpStatus.OK);
    }
}
