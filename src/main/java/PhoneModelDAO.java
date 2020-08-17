import alexey.com.jdbcTutorial2DAO.model.PhoneModel;
import alexey.com.jdbcTutorial2DAO.model.User;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneModelDAO implements DAO<PhoneModel, String> {
    @NonNull
    private final Connection connection;

    public PhoneModelDAO(@NonNull final Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean delete(@NonNull final PhoneModel model) {
        if (!isExist(model.getName())) return false;
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(ModelPhoneSQL.DELETE.QUERY)) {
            statement.setString(1, model.getName());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public PhoneModel read(@NonNull final String name) {
        final PhoneModel result = new PhoneModel();
        result.setName(name);

        try (PreparedStatement statement = connection.prepareStatement(ModelPhoneSQL.GET.QUERY)) {
            statement.setString(1, name);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.setId(Integer.parseInt(rs.getString("id")));
            } else {
                result.setName("entity is not exist in phone_models");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(@NonNull final PhoneModel model) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(ModelPhoneSQL.UPDATE.QUERY)) {
            statement.setString(1, model.getName());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean create(@NonNull final PhoneModel model) {
        if (isExist(model.getName())) return false;
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(ModelPhoneSQL.ADD.QUERY)) {
            statement.setString(1, model.getName());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    private boolean isExist(@NonNull final String name) {

        return read(name).getId() != -1;
    }

    enum ModelPhoneSQL {
        GET("SELECT id FROM phone_models WHERE name = (?)"),
        DELETE("DELETE FROM phone_models WHERE name = (?) RETURNING id"),
        ADD("INSERT INTO phone_models (id, name) VALUES (DEFAULT, (?)) RETURNING id;"),
        UPDATE("UPDATE phone_models SET name = (?) WHERE id = (?) RETURNING id");
        String QUERY;

        ModelPhoneSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}

