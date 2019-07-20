package cricketworldcup.icccricketworldcup.API;

import java.util.List;

public class dataInfo {
    String live;
    String status;
    String note;
    localteam localteam;
    visitorteam visitorteam;
    List<runInfo> runs;

    public localteam getLocalteam() {
        return localteam;
    }

    public void setLocalteam(localteam localteam) {
        this.localteam = localteam;
    }

    public visitorteam getVisitorteam() {
        return visitorteam;
    }

    public void setVisitorteam(visitorteam visitorteam) {
        this.visitorteam = visitorteam;
    }

    public List<runInfo> getRuns() {
        return runs;
    }

    public void setRuns(List<runInfo> runs) {
        this.runs = runs;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public dataInfo() {
    }

    public dataInfo(String live, String status, String note) {
        this.live = live;
        this.status = status;
        this.note = note;
    }
}
