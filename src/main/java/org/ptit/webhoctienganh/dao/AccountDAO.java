package org.ptit.webhoctienganh.dao;

import org.ptit.webhoctienganh.model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends DAO {

    public Account getAccountByUsernameAndPassword(String username, String password) {
        Account account = null;
        String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                account = new Account(
                        rs.getString("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Role"),
                        rs.getInt("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
