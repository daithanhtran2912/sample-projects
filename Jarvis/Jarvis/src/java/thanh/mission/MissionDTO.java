/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.mission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author T.Z.B
 */
public class MissionDTO implements Serializable {

    private String missionId, missionName, date, locate, description;
    private boolean status;
    private List<String> listMember;

    public MissionDTO() {
        this.listMember = new ArrayList<>();
    }

    //for insert
    public MissionDTO(String missionId, String missionName, String date, String locate, String description, boolean status) {
        this.listMember = new ArrayList<>();
        this.missionId = missionId;
        this.missionName = missionName;
        this.date = date;
        this.locate = locate;
        this.description = description;
        this.status = status;
    }

    //for showing list mission
    public MissionDTO(String missionId, String missionName, String locate, boolean status) {
        this.listMember = new ArrayList<>();
        this.missionId = missionId;
        this.missionName = missionName;
        this.locate = locate;
        this.status = status;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getListMember() {
        return listMember;
    }

    public void setListMember(List<String> listMember) {
        this.listMember = listMember;
    }

}
