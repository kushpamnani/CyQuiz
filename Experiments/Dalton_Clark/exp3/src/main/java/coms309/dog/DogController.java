package coms309.dog;

import coms309.people.Person;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.HashMap;

@RestController
public class DogController {

    public HashMap<String, Dog> dogList = new  HashMap<>();

    @GetMapping("/dog")
    public  HashMap<String,Dog> getAllPersons() {
        return dogList;
    }


    @PostMapping("/dog")
    public String createDog(@RequestBody Dog dog) {
        System.out.println(dog);
        dogList.put(dog.getName(), dog);
        return "New dog "+ dog.getName() + " Saved";
    }

    public String createDogViaPerson(Dog dog) {
        System.out.println(dog);
        dogList.put(dog.getName(), dog);
        return "New dog "+ dog.getName() + " Saved";
    }


    @GetMapping("/dog/{name}")
    public Dog getDog(@PathVariable String name) {
        Dog dog = dogList.get(name);
        return dog;
    }


    @PutMapping("/dog/{name}")
    public Dog updateDog(@PathVariable String name,@RequestBody Dog dog) {
        dogList.replace(name, dog);
        return dogList.get(name);
    }



    @DeleteMapping("/dog/{name}")
    public HashMap<String, Dog> deleteDog(@PathVariable String name) {
        dogList.remove(name);
        return dogList;
    }
}

