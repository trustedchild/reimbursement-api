package com.guwor.reimbursementapi.daos;

import com.guwor.reimbursementapi.utils.custom_exceptions.InvalidSQLException;
import com.guwor.reimbursementapi.models.User;
import com.guwor.reimbursementapi.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User>{
        @Override
        public void save(User obj) {
            try (Connection con = ConnectionFactory.getInstance().getConnection()) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO users (user_id, username, email, password, given_name, surname, is_active, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, obj.getUser_id());
                ps.setString(2, obj.getUsername());
                ps.setString(3, obj.getEmail());
                ps.setString(4, obj.getPassword());
                ps.setString(5, obj.getGiven_name());
                ps.setString(6, obj.getSurname());
                ps.setString(7, obj.getIs_active());
                ps.setString(8, obj.getRole_id());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new InvalidSQLException("An error occurred while trying to save to the database.");
            }
        }


        @Override
        public void update(User obj) {

        }

        @Override
        public void delete(String id) {

        }

        @Override
        public User getById(String id) {
            try (Connection con = ConnectionFactory.getInstance().getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
                }

            } catch (SQLException e) {
                throw new InvalidSQLException("An error occurred when tyring to save to the database.");
            }

            return null;
        }

        public User getUserByUsername(String username) {
            try (Connection con = ConnectionFactory.getInstance().getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("role_id"));
                }

            } catch (SQLException e) {
                throw new InvalidSQLException("An error occurred when tyring to save to the database.");
            }

            return null;
        }


        @Override
        public List<User> getAll() {
            List<User> userList = new ArrayList<>();

            try (Connection con = ConnectionFactory.getInstance().getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    User user = new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("role_id"));
                    userList.add(user);
                }

            } catch (SQLException e) {
                throw new InvalidSQLException("An error occurred when tyring to save to the database.");
            }

            return userList;
        }

        public String getUsername(String username) {
            try (Connection con = ConnectionFactory.getInstance().getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT (username) FROM users WHERE username = ?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) return rs.getString("username");

            } catch (SQLException e) {
                throw new InvalidSQLException("An error occurred when retrieving username from the database.");
            }
            return null;
        }

        public User getUserByUsernameAndPassword(String username, String password) {
            try (Connection con = ConnectionFactory.getInstance().getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                if (rs.next())
                    return new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("role_id"));
            } catch (SQLException e) {
                throw new InvalidSQLException("An error occurred in getUserByUsernameAndPassword method in userDAO.");
            }

            return null;
        }
    }