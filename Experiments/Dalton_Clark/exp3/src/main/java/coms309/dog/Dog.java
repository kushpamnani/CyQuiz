package coms309.dog;

public class Dog {

    private String name;

    protected String owner;

    private String breed;

    private String favTreat;

    public Dog(){

    }

    public Dog(String name, String owner, String breed, String favTreat){
        this.name = name;
        this.owner = owner;
        this.breed = breed;
        this.favTreat = favTreat;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getFavTreat() {
        return this.favTreat;
    }

    public void setFavTreat(String favTreat) {
        this.favTreat = favTreat;
    }

    @Override
    public String toString() {
        return name + " "
                + owner + " "
                + breed + " "
                + favTreat;
    }
}

