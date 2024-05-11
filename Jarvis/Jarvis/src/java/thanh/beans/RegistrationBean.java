/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.beans;

import java.io.Serializable;
import java.util.List;
import thanh.registration.RegistrationDAO;
import thanh.registration.RegistrationDTO;

/**
 *
 * @author T.Z.B
 */
public class RegistrationBean implements Serializable {

    private String username, password, fullname, firstname, lastname, role, email, imageURL;
    boolean permission;
    RegistrationDTO dto;
    private String txtSearch;

    public RegistrationBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isPermission() {
        return permission;
    }

    public RegistrationDTO getDto() {
        return dto;
    }

    public void setDto(RegistrationDTO dto) {
        this.dto = dto;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String checkLogin(String username, String password) throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        return dao.checkLogin(username, password);
    }

    public List<RegistrationDTO> getAllMemberToTable() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        return dao.getAllMemberToTable();
    }

    public List<RegistrationDTO> findMemberByLikeName() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        return dao.findMemberByLikeName(txtSearch);
    }

    public boolean removeMemberByUsername() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        return dao.removeMemberByUsername(username);
    }

    public RegistrationDTO findMemberByUsername() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        return dao.findMemberByUsername(username);
    }

    public boolean updateMemberDetail() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        return dao.updateMemberDetail(dto);
    }

    public boolean createNewMember() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        return dao.createNewMember(dto);
    }

    public List<String> getAllUsername() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        return dao.getAllUsername();
    }

}
