package coms309;

import org.springframework.web.bind.annotation.*;

@RestController
class WelcomeController {

    @PostMapping("/")
    public String welcome() {
        return "Hello and welcome to COMS 309";
    }

    //the second one matters.
    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello and welcome to COMS 309: " + name;
    }
}