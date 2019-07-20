package cricketworldcup.icccricketworldcup.API;

public class localteam {
    String code;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public localteam() {
    }

    public localteam(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
