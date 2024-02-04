package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello and welcome to COMS 309";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello and welcome to COMS 309: " + name;
    }

    @GetMapping("/introduction")
    public String introduction() {
        return "Tell me about yourself";
    }

    @GetMapping("/introduction/{introduction}")
    public String introduction(@PathVariable String introduction) { return "Wow! I also relate to \"" + introduction + "\" as well!"; }
}
