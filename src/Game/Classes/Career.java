package Game.Classes;

public enum Career {
    ARCHER("战士"),
    MAGIC("法师"),
    WARRIOR("弓箭手");

    String careerName;

    Career(String name){
        this.careerName=name;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }
}
