package doerr.alex.fantasyworldapi.model;

public class Planet {
    private String name;
    private String climate;
    private int id;

    public Planet(int id, String name, String climate){
        setId(id);
        setName(name);
        setClimate(climate);
    }

    public Planet(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isEmpty()){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name must not be empty");
        }
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        if(climate != null && !climate.isEmpty()){
            this.climate = climate;
        } else {
            throw new IllegalArgumentException("Climate must not be empty");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
    }
}
