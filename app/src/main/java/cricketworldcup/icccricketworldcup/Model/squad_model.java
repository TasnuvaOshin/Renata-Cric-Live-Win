package cricketworldcup.icccricketworldcup.Model;

public class squad_model {
    String img;
    String  player_name;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public squad_model() {
    }

    public squad_model(String img, String player_name) {
        this.img = img;
        this.player_name = player_name;
    }
}
