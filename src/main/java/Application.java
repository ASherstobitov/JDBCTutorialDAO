import alexey.com.jdbcTutorial2DAO.model.PhoneModel;
import alexey.com.jdbcTutorial2DAO.model.User;

import java.sql.Connection;

public class Application {
    static void mult(int val) {
        val = val * 2;

    }
    public static void main(String[] args){
        int a = 10;


        mult(a);

        System.out.println();




//            Connection connection = null;
//        try {
//            Properties properties = new Properties();
//            InputStream inputStream = Application.class.getResourceAsStream("db.properties");
//            properties.load(inputStream);
//            inputStream.close();
//            String url = properties.getProperty("url");
//            String username = properties.getProperty("username");
//            String password = properties.getProperty("password");
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }




//        Connection connection = ConnectionManager.getInstance().getConnection();
//        UserDAO userDAO = new UserDAO(connection);
//
//        User user = new User();
//        user.setLogin("July");
//        user.setPassword("2407");
//        User.Role role = new User.Role(2,"user");
//        user.setRole(role);
//        userDAO.create(user);

//        PhoneModelDAO phoneModelDAO = new PhoneModelDAO(connection);
//        PhoneModel phoneModel = new PhoneModel();
//        phoneModel.setName("xaomi");
//        phoneModelDAO.delete(phoneModel);


    }


}

