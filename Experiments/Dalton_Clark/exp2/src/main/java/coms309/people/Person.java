package coms309.people;


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

    private String dogName;

    private String dogBreed;

    private String dogFavTreat;

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
        this.dogName = dogName;
        this.dogBreed = dogBreed;
        this.dogFavTreat = dogFavTreat;
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
        return this.dogName;
    }

    public void setDogName(String dogName) { this.dogName = dogName; }

    public String getDogBreed() {
        return this.dogBreed;
    }

    public void setDogBreed(String dogBreed) { this.dogBreed = dogBreed; }

    public String getDogFavTreat() {
        return this.dogFavTreat;
    }

    public void setDogFavTreat(String dogFavTreat) { this.dogFavTreat = dogFavTreat; }

    @Override
    public String toString() {
        return firstName + " "
                + lastName + " "
                + address + " "
                + telephone + " "
                + dogName + " "
                + dogBreed + " "
                + dogFavTreat;
    }
}
