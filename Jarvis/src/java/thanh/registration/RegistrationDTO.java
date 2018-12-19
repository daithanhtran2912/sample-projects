/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.registration;

import java.io.Serializable;

/**
 *
 * @author T.Z.B
 */
public class RegistrationDTO implements Serializable {

    private String username, password, firstname, lastname, email, role, imageURL;
    private boolean permission;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username) {
        this.username = username;
    }

    //For insert new member
    public RegistrationDTO(String username, String password, String firstname, String lastname, String email, String role, String imageURL, boolean permission) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.imageURL = imageURL;
        this.permission = permission;
    }

    //For showing list of members
    public RegistrationDTO(String username, String firstname, String lastname, String email, String role) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
    }

    //For view and update (don't allow admin to update username of member)
    public RegistrationDTO(String username, String password, String firstname, String lastname, String email, String role, String imageURL) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.imageURL = imageURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getimageURL() {
        return imageURL;
    }

    public void setimageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
