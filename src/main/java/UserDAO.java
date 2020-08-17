import alexey.com.jdbcTutorial2DAO.model.User;
import lombok.NonNull;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements DAO<User, String> {

    private final Connection connection;

    public UserDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(@NonNull final User model) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLUser.INSERT.QUERY)) {
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getPassword());
            statement.setInt(3, model.getRole().getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User read(@NonNull final String login) {
        final User result = new User();
        result.setId(-1);

        try (PreparedStatement statement = connection.prepareStatement(SQLUser.GET.QUERY)) {
            statement.setString(1, login);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.setId(Integer.parseInt(rs.getString("id")));
                result.setLogin(login);
                result.setPassword(rs.getString("password"));
                result.setRole(new User.Role(rs.getInt("rol_id"), rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(@NonNull final User model) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLUser.UPDATE.QUERY)) {
            statement.setString(1, model.getLogin());
            statement.setInt(2, model.getRole().getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(@NonNull final User model) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLUser.DELETE.QUERY)) {
            statement.setInt(1, model.getId());
            statement.setString(2, model.getLogin());
            statement.setString(3, model.getPassword());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    enum SQLUser {
        GET("SELECT u.id, u.login, u.password, r.id AS rol_id, r.role FROM users AS u LEFT JOIN roles AS r ON u.role = r.id WHERE u.login = (?)"),
        INSERT("INSERT INTO users (id, login, password, role) VALUES (DEFAULT, (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM users WHERE id=(?) AND login= (?) AND password =(?) RETURNING id"),
        UPDATE("UPDATE users SET password = (?) WHERE id = (?) RETURNING id");
        String QUERY;
        SQLUser(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
