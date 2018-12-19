/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.beans;

import java.io.Serializable;
import java.util.List;
import thanh.armor.ArmorDAO;
import thanh.armor.ArmorDTO;

/**
 *
 * @author T.Z.B
 */
public class ArmorBean implements Serializable {

    private String suitCode, suitName, material, weapon, imageURL;
    private boolean available;
    private ArmorDTO dto;
    private String txtSearch;
    private String username;
    private boolean status; //checking this suit is borrowed or not

    public ArmorBean() {
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

    public ArmorDTO getDto() {
        return dto;
    }

    public void setDto(ArmorDTO dto) {
        this.dto = dto;
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ArmorDTO> getAllSuit() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.getAllSuit();
    }

    public List<ArmorDTO> findArmorByLikeName() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.findArmorByLikeName(txtSearch);
    }

    public boolean removeArmorByCode() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.removeArmorByCode(suitCode);
    }

    public boolean removeSuitFromHall() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.removeSuitFromHall(suitCode);
    }

    public boolean insertNewArmor() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.insertNewArmor(dto);
    }

    public boolean insertSuitToHallOfArmor() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.insertSuitToHallOfArmor(suitCode, username, status);
    }

    public ArmorDTO findSuitBySuitCode() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.findSuitBySuitCode(suitCode);
    }

    public boolean updateSuitBySuitCode() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.updateSuitBySuitCode(dto);
    }

    public List<ArmorDTO> getAvailableArmor() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.getAvailableArmor();
    }

    public List<ArmorDTO> findAvailableArmor() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.findAvailableArmor(txtSearch);
    }

    public boolean getSuitStatus() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.getSuitStatus(suitCode);
    }

    public boolean updateSuitStatus() throws Exception {
        ArmorDAO dao = new ArmorDAO();
        return dao.updateSuitStatus(suitCode, status);
    }
}
