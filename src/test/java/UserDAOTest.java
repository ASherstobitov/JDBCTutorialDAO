import alexey.com.jdbcTutorial2DAO.model.User;
import lombok.NonNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.*;

public class UserDAOTest {
    @NonNull
    private DAO<User, String> dao;
    @NonNull
    private Connection connection;


    @Before
    public void before() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getResourceAsStream("db.properties");
            properties.load(inputStream);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            dao = new UserDAO(connection);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenUserGetExistThenReturnUser() {
        final User user = dao.read("admin");
        final User expected = new User();
        expected.setId(1);
        expected.setLogin("admin");
        expected.setPassword("123");
        expected.setRole(new User.Role(1, "admin"));
        assertEquals(expected, user);
    }

    @Test
    public void whenUserGetNotExistThenReturnEmptyUserObjec() {
        final User user = dao.read("111");
        assertEquals(-1, user.getId());
    }

    @Test
    public void whenAddUserWhichNotExistThenReturnUser() {
        final User user = new User(0, "test", "test", new User.Role(1, "admin"));
        final boolean result = dao.create(user);
        assertTrue(result);
        dao.delete(dao.read("test"));
    }

    @Test
    public void whenUserGetExistReturnFalse() {
        assertFalse(dao.delete(new User(0, "test", "test", new User.Role(1, "admin"))));
    }

    @Test
    public void whenUserWhichDeletedThenReturnTrue() {
        final User user = new User(1, "test", "test", new User.Role(1, "admin"));
        dao.create(user);
        final User state = dao.read("test");
        boolean before = state.getId() != -1;

        final boolean after = dao.delete(user);
        assertTrue(before);
        assertTrue(after);

    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create() {
    }

    @Test
    public void read() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}