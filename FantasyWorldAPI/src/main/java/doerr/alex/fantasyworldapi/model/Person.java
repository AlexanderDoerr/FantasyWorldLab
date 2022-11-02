package doerr.alex.fantasyworldapi.model;

public class Person {
    private int id;
    private String name;
    private String rank;

    public Person(int id, String name, String rank){
        setId(id);
        setName(name);
        setRank(rank);
    }

    public Person(){

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name must not be empty");
        }
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        if (rank != null && !rank.isEmpty()) {
            this.rank = rank;
        } else {
            throw new IllegalArgumentException("Rank must not be empty");
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
