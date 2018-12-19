/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.armor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import thanh.connection.MyConnection;

/**
 *
 * @author T.Z.B
 */
public class ArmorDAO implements Serializable {

    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<ArmorDTO> getAllSuit() throws Exception {
        List<ArmorDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select suitCode, suitName, available from Suit_Detail";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new ArmorDTO(rs.getString("suitCode"), rs.getString("suitName"), rs.getBoolean("available")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArmorDTO> findArmorByLikeName(String txtSearch) throws Exception {
        List<ArmorDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select suitCode, suitName, available from Suit_Detail where suitName LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + txtSearch + "%");
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new ArmorDTO(rs.getString("suitCode"), rs.getString("suitName"), rs.getBoolean("available")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean removeArmorByCode(String armorCode) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Delete from Suit_Detail where suitCode=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, armorCode);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean removeSuitFromHall(String suitCode) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Delete from Suit where suitCode = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, suitCode);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertNewArmor(ArmorDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Insert into Suit_Detail(suitCode, suitName, material, weapon, imageURL, available) values(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getSuitCode());
                stm.setString(2, dto.getSuitName());
                stm.setString(3, dto.getMaterial());
                stm.setString(4, dto.getWeapon());
                stm.setString(5, dto.getImageURL());
                stm.setBoolean(6, dto.isAvailable());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertSuitToHallOfArmor(String suitCode, String username, boolean status) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "insert into Suit(suitCode, username, status) values(?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, suitCode);
                stm.setString(2, username);
                stm.setBoolean(3, status);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public ArmorDTO findSuitBySuitCode(String suitCode) throws Exception {
        ArmorDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select suitName, material, weapon, imageURL, available from Suit_Detail where suitCode=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, suitCode);
                rs = stm.executeQuery();
                if (rs.next()) {
                    dto = new ArmorDTO(suitCode, rs.getString("suitName"), rs.getString("material"), rs.getString("weapon"), rs.getString("imageURL"), rs.getBoolean("available"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean updateSuitBySuitCode(ArmorDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Update Suit_Detail set suitName=?, material=?, weapon=?, imageURL=?, available=? where suitCode=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getSuitName());
                stm.setString(2, dto.getMaterial());
                stm.setString(3, dto.getWeapon());
                stm.setString(4, dto.getImageURL());
                stm.setBoolean(5, dto.isAvailable());
                stm.setString(6, dto.getSuitCode());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<ArmorDTO> getAvailableArmor() throws Exception {
        List<ArmorDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "select suitCode, suitName, material, available from Suit_Detail";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new ArmorDTO(rs.getString("suitCode"), rs.getString("suitName"), rs.getString("material"), rs.getBoolean("available")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArmorDTO> findAvailableArmor(String txtSearch) throws Exception {
        List<ArmorDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "select suitCode, suitName, material, available from Suit_Detail where suitName LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + txtSearch + "%");
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new ArmorDTO(rs.getString("suitCode"), rs.getString("suitName"), rs.getString("material"), rs.getBoolean("available")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean getSuitStatus(String suitCode) throws Exception {
        boolean status = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select status from Suit where suitCode = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, suitCode);
                rs = stm.executeQuery();
                if (rs.next()) {
                    status = rs.getBoolean("status");
                }
            }
        } finally {
            closeConnection();
        }
        return status;
    }

    public boolean updateSuitStatus(String suitCode, boolean status) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "update Suit set status=? where suitCode=?";
                stm = conn.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, suitCode);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
