package doerr.alex.fantasyworldapi.model;

public class Starship {
    private int id;
    private String name;
    private String modelType;

    public Starship(int id, String name, String modelType){
        setId(id);
        setName(name);
        setModelType(modelType);
    }

    public Starship(){

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
        if(name != null && !name.isEmpty()){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name must not be empty");
        }
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        if(modelType != null && !modelType.isEmpty()){
            this.modelType = modelType;
        } else {
            throw new IllegalArgumentException("Model Type must not be empty");
        }
    }
}
