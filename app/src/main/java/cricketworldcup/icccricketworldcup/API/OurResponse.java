package cricketworldcup.icccricketworldcup.API;

import java.util.List;

public class OurResponse {
    List<dataInfo> data;

    public List<dataInfo> getData() {
        return data;
    }

    public void setData(List<dataInfo> data) {
        this.data = data;
    }

    public OurResponse() {
    }

    public OurResponse(List<dataInfo> data) {
        this.data = data;
    }
}
