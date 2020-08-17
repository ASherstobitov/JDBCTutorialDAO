package alexey.com.jdbcTutorial2DAO.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int id;
    private String login;
    private String password;
    private Role role;

    @Data
    @AllArgsConstructor
    public static class Role {
        private int id;
        private String name;
    }
}
