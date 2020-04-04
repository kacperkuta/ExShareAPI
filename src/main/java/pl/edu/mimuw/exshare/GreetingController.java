package pl.edu.mimuw.exshare;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/gevorg")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new String("Witaj, Gevorg! :)");
    }

    @GetMapping("/request")
    public Greeting greeting2(@RequestParam(value = "name", defaultValue = "Świecie") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/request2")
    public Greeting greeting3(@RequestParam(value = "name", defaultValue = "Świecie") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}