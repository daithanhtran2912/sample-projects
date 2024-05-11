/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.armor;

import java.io.Serializable;

/**
 *
 * @author T.Z.B
 */
public class ArmorDTO implements Serializable {

    private String suitCode, suitName, material, weapon, imageURL;
    private boolean available;

    public ArmorDTO() {
    }

    //for insert
    public ArmorDTO(String suitCode, String suitName, String material, String weapon, String imageURL, boolean available) {
        this.suitCode = suitCode;
        this.suitName = suitName;
        this.material = material;
        this.weapon = weapon;
        this.imageURL = imageURL;
        this.available = available;
    }

    //for showing list armor
    public ArmorDTO(String suitCode, String suitName, boolean available) {
        this.suitCode = suitCode;
        this.suitName = suitName;
        this.available = available;
    }

    public ArmorDTO(String suitCode, String suitName, String material, boolean available) {
        this.suitCode = suitCode;
        this.suitName = suitName;
        this.material = material;
        this.available = available;
    }

    public String getSuitCode() {
        return suitCode;
    }

    public void setSuitCode(String suitCode) {
        this.suitCode = suitCode;
    }

    public String getSuitName() {
        return suitName;
    }

    public void setSuitName(String suitName) {
        this.suitName = suitName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
