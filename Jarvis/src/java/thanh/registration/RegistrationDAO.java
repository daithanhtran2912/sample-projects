/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.registration;

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
public class RegistrationDAO implements Serializable {

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

    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select role from Registration where username=? and password=? and permission = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, "true");
                rs = stm.executeQuery();
                if (rs.next()) {
                    role = rs.getString("role");
                }
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    //select username, firstname, lastname, email, role where permission=true
    //This method for admin-get list of members into table
    public List<RegistrationDTO> getAllMemberToTable() throws Exception {
        List<RegistrationDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select username, firstname, lastname, email, role from Registration where permission = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "true");
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new RegistrationDTO(rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("role")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    //search member like firstname or lastname
    //this method is for search member action
    public List<RegistrationDTO> findMemberByLikeName(String searchName) throws Exception {
        List<RegistrationDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select username, firstname, lastname, email, role "
                        + "from Registration "
                        + "where (firstname LIKE ? or lastname LIKE ?) and permission = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchName + "%");
                stm.setString(2, "%" + searchName + "%");
                stm.setString(3, "true");
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new RegistrationDTO(rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("role")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    //remove member by username (set permission = false)
    //this method is actually set the permission = false
    public boolean removeMemberByUsername(String username) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Update Registration set permission = ? where username = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "false");
                stm.setString(2, username);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    //this is for view action
    public RegistrationDTO findMemberByUsername(String username) throws Exception {
        RegistrationDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select password, firstname, lastname, email, role, imageURL from Registration where username = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    dto = new RegistrationDTO(username, rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("role"), rs.getString("imageURL"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean updateMemberDetail(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Update Registration set password=?, firstname=?, lastname=?, email=?, role=?, imageURL=? where username=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getPassword());
                stm.setString(2, dto.getFirstname());
                stm.setString(3, dto.getLastname());
                stm.setString(4, dto.getEmail());
                stm.setString(5, dto.getRole());
                stm.setString(6, dto.getimageURL());
                stm.setString(7, dto.getUsername());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean createNewMember(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Insert into Registration(username, password, firstname, lastname, email, role, imageURL, permission) values(?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFirstname());
                stm.setString(4, dto.getLastname());
                stm.setString(5, dto.getEmail());
                stm.setString(6, dto.getRole());
                stm.setString(7, dto.getimageURL());
                stm.setBoolean(8, dto.isPermission());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<String> getAllUsername() throws Exception {
        List<String> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select username from Registration where permission = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "true");
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(rs.getString("username"));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
