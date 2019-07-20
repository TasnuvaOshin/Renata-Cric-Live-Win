package cricketworldcup.icccricketworldcup.Model;

public class fixture_model {
    String longdes;
    String shortdes;
    String teamone;
    String teamtwo;
    String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public fixture_model() {
    }

    public String getLongdes() {
        return longdes;
    }

    public void setLongdes(String longdes) {
        this.longdes = longdes;
    }

    public String getShortdes() {
        return shortdes;
    }

    public void setShortdes(String shortdes) {
        this.shortdes = shortdes;
    }

    public String getTeamone() {
        return teamone;
    }

    public void setTeamone(String teamone) {
        this.teamone = teamone;
    }

    public String getTeamtwo() {
        return teamtwo;
    }

    public void setTeamtwo(String teamtwo) {
        this.teamtwo = teamtwo;
    }

    public fixture_model(String longdes, String shortdes, String teamone, String teamtwo, String img) {
        this.longdes = longdes;
        this.shortdes = shortdes;
        this.teamone = teamone;
        this.teamtwo = teamtwo;
        this.img = img;
    }
}
