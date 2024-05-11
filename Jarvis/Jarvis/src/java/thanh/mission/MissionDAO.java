/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.mission;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thanh.connection.MyConnection;

/**
 *
 * @author T.Z.B
 */
public class MissionDAO implements Serializable {

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

    public List<MissionDTO> getAllMission() throws Exception {
        List<MissionDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "select missionId, missionName, locate, status from Mission_Detail";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new MissionDTO(rs.getString("missionId"), rs.getString("missionName"), rs.getString("locate"), rs.getBoolean("status")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    //find mission where missionName like ?
    public List<MissionDTO> findMissionByLikeName(String txtSearchName) throws Exception {
        List<MissionDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "select missionId, missionName, locate, status from Mission_Detail where missionName LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + txtSearchName + "%");
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new MissionDTO(rs.getString("missionId"), rs.getString("missionName"), rs.getString("locate"), rs.getBoolean("status")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertNewMission(MissionDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "insert into Mission_Detail(missionId, missionName, date, locate, description, status) values(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getMissionId());
                stm.setString(2, dto.getMissionName());
                stm.setString(3, dto.getDate());
                stm.setString(4, dto.getLocate());
                stm.setString(5, dto.getDescription());
                stm.setBoolean(6, dto.isStatus());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateMissionById(MissionDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "update Mission_Detail set missionName = ?, date = ?, locate = ?, description = ?, status = ? where missionId = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getMissionName());
                stm.setString(2, dto.getDate());
                stm.setString(3, dto.getLocate());
                stm.setString(4, dto.getDescription());
                stm.setBoolean(5, dto.isStatus());
                stm.setString(6, dto.getMissionId());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean removeMissionById(String missionId) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Delete from Missions where missionId=? "
                        + "Delete from Mission_Detail where missionId=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, missionId);
                stm.setString(2, missionId);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public MissionDTO getMissionDetail(String missionId) throws Exception {
        MissionDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "select missionName, date, locate, description, status from Mission_Detail where missionId=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, missionId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    dto = new MissionDTO(missionId, rs.getString("missionName"), rs.getDate("date").toString(), rs.getString("locate"), rs.getString("description"), rs.getBoolean("status"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<String> getMemberOfAMission(String missionId) throws Exception {
        List<String> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Select username from Missions where missionId=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, missionId);
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

    public boolean inputMemberToMission(String[] username, String missionId) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                String sql = "insert into Missions(missionId, username) values(?,?)";
                stm = conn.prepareStatement(sql);
                for (String member : username) {
                    stm.setString(1, missionId);
                    stm.setString(2, member);
                    stm.executeUpdate();
                    conn.commit();
                    check = true;
                }
            }
        } catch (SQLException e) {
            check = false;
            conn.rollback();
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean removeAllMemberInMission(String missionId) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Delete from Missions where missionId=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, missionId);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<MissionDTO> getListMissionOfAUser(String username, boolean status) throws Exception {
        List<MissionDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "select Mission_Detail.missionID, Mission_Detail.missionName, Mission_Detail.date, Mission_Detail.locate, Mission_Detail.description, Mission_Detail.status "
                        + "From Mission_Detail, Missions "
                        + "Where Missions.username=? and Mission_Detail.status=? "
                        + "And Mission_Detail.missionID = Missions.missionID";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setBoolean(2, status);
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new MissionDTO(rs.getString("missionId"), rs.getString("missionName"), rs.getString("date"), rs.getString("locate"), rs.getString("description"), rs.getBoolean("status")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<MissionDTO> findMissionOfAUser(String txtSearch, String username, boolean status) throws Exception {
        List<MissionDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "select Mission_Detail.missionID, Mission_Detail.missionName, Mission_Detail.date, Mission_Detail.locate, Mission_Detail.description, Mission_Detail.status "
                        + "From Mission_Detail, Missions "
                        + "Where Missions.username=? "
                        + "And Mission_Detail.missionID = Missions.missionID "
                        + "And Mission_Detail.missionName LIKE ? "
                        + "And Mission_Detail.status=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, "%" + txtSearch + "%");
                stm.setBoolean(3, status);
                rs = stm.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new MissionDTO(rs.getString("missionId"), rs.getString("missionName"), rs.getString("date"), rs.getString("locate"), rs.getString("description"), rs.getBoolean("status")));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean submitMission(String missionId, boolean status) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "Update Mission_Detail set status=? where missionId=?";
                stm = conn.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, missionId);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
