package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Random;

@RestController
class EightballController {

    @GetMapping("/magic8ball")
    public String magic8ball() {
        return "Ask any y/n question you like and I'll answer it!";
    }

    @GetMapping("/magic8ball/{question}")
    public String magic8ball(@PathVariable String question) {
        String[] responseList = {"It is certain","It is decidedly so","Try again","Don't count on it","Without a doubt","Yes, definitely","Ask again later","My reply is no","You may believe it so","As I see it, yes","Better not tell you now","My sources say no","Most likely","I think so","Cannot predict now","Outlook not so good","Yes","Signs point to yes","Concentrate and ask again","Very doubtful"};
        Random rand = new Random();
        String answer = responseList[rand.nextInt(0,20)];
        return "My answer to the question: \"" + question + "?\" is...\n\n" + answer;
    }
}
