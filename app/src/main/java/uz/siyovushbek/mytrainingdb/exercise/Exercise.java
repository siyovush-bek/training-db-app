package uz.siyovushbek.mytrainingdb.exercise;

public class Exercise {
    private int id;
    private String name;
    private String description;
    private String fileName;

    public Exercise(String name, String description, String fileName) {
        this.name = name;
        this.description = description;
        this.fileName = fileName;
    }

    public Exercise(int id, String name, String description, String fileName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
