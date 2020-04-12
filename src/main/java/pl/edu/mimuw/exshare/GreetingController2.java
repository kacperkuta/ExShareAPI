package pl.edu.mimuw.exshare;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController2 {

    @GetMapping("/test")
    public String testujemy() {
        return "Udane!\n";
    }

}
