/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.beans;

import java.io.Serializable;
import java.util.List;
import thanh.mission.MissionDAO;
import thanh.mission.MissionDTO;

/**
 *
 * @author T.Z.B
 */
public class MissionBean implements Serializable {

    private String missionId, missionName, date, locate, description;
    private boolean status;
    private MissionDTO dto;
    private String txtSearch;
    private List<String> listMember;
    private String[] members;
    private String username;

    public MissionBean() {
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

    public MissionDTO getDto() {
        return dto;
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public void setDto(MissionDTO dto) {
        this.dto = dto;
    }

    public List<String> getListMember() {
        return listMember;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public void setListMember(List<String> listMember) {
        this.listMember = listMember;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<MissionDTO> getAllMission() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.getAllMission();
    }

    public List<MissionDTO> findMissionByLikeName() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.findMissionByLikeName(txtSearch);
    }

    public boolean removeMissionById() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.removeMissionById(missionId);
    }

    public boolean insertNewMission() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.insertNewMission(dto);
    }

    public MissionDTO getMissionDetail() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.getMissionDetail(missionId);
    }

    public List<String> getMemberOfAMission() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.getMemberOfAMission(missionId);
    }

    public boolean inputMemberToMission() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.inputMemberToMission(members, missionId);
    }

    public boolean updateMissionById() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.updateMissionById(dto);
    }

    public boolean removeAllMemberInMission() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.removeAllMemberInMission(missionId);
    }

    public List<MissionDTO> getListMissionOfAUser() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.getListMissionOfAUser(username, status);
    }

    public List<MissionDTO> findMissionOfAUser() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.findMissionOfAUser(txtSearch, username, status);
    }

    public boolean submitMission() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.submitMission(missionId, status);
    }
}
