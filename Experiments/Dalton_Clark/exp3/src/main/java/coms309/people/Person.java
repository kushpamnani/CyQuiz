package coms309.people;


import coms309.dog.Dog;

/**
 * Provides the Definition/Structure for the people row
 *
 * @author Vivek Bengre
 */

public class Person {

    private String firstName;

    private String lastName;

    private String address;

    private String telephone;

    protected Dog dog;

    public Person(){
        
    }

    public Person(String firstName, String lastName, String address, String telephone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }

    public Person(String firstName, String lastName, String address, String telephone, String dogName, String dogBreed, String dogFavTreat){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        dog = new Dog(dogName, firstName, dogBreed, dogFavTreat);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getDogName() {
        return dog.getName();
    }

    public void setDogName(String dogName) { dog.setName(dogName); }

    public String getBreed() {
        return dog.getBreed();
    }

    public void setBreed(String dogBreed) { dog.setBreed(dogBreed); }

    public String getFavTreat() {
        return dog.getFavTreat();
    }

    public void setFavTreat(String dogFavTreat) { dog.setFavTreat(dogFavTreat); }

    @Override
    public String toString() {
        return firstName + " "
                + lastName + " "
                + address + " "
                + telephone + " "
                + dog.getName() + " "
                + dog.getBreed() + " "
                + dog.getFavTreat();
    }
}
