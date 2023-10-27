package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Luka
 */
public class SqlUserRepo implements Repository<User>{
    
    private static final String ID_USER = "IdUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String ADMIN_ROLE = "AdminRole";


    private static final String CREATE_USER = "{ CALL CreateUser (?,?,?) }";
    private static final String UPDATE_USER = "{ CALL UpdateUser (?,?,?,?) }";
    private static final String DELETE_USER = "{ CALL DeleteUser (?) }";
    private static final String SELECT_USER = "{ CALL SelectUser (?) }";
    private static final String SELECT_USERS = "{ CALL SelectUsers }";
    private static final String AUTHENTICATE_USER = "{ CALL AuthenticateUser (?,?,?) }";
    @Override
    public int createOne(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER);)
        {
            stmt.setString(USERNAME, user.getUsername());
            stmt.setString(PASSWORD, user.getPassword());
            stmt.registerOutParameter(ID_USER, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(ID_USER);
        }
    }

    @Override
    public void createMany(List<User> users) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER);)
        {
            for (User user : users) {
                stmt.setString(USERNAME, user.getUsername());
                stmt.setString(PASSWORD, user.getPassword());
                stmt.setString(ADMIN_ROLE, String.valueOf(user.getAdminRoleToBit()));
                stmt.registerOutParameter(ID_USER, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateOne(int id, User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_USER);)
        {
            stmt.setInt(ID_USER, id);
            stmt.setString(USERNAME, user.getUsername());
            stmt.setString(PASSWORD, user.getPassword());
            stmt.setString(ADMIN_ROLE, String.valueOf(user.getAdminRoleToBit()));
            
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_USER);) {

            stmt.setInt(ID_USER, id);

            stmt.executeUpdate();
        }}

    @Override
    public Optional<User> selectOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER);) {

            stmt.setInt(ID_USER, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(
                            new User(
                                    rs.getInt(ID_USER),
                                    rs.getString(USERNAME),
                                    rs.getString(PASSWORD),
                                    rs.getBoolean(ADMIN_ROLE)
                            )
                    );
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<User> selectMany() throws Exception {
        List<User> users = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(SELECT_USERS); 
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                users.add(
                        new User(
                                    rs.getInt(ID_USER),
                                    rs.getString(USERNAME),
                                    rs.getString(PASSWORD),
                                    rs.getBoolean(ADMIN_ROLE)
                        )
                );
            }
        }

        return users; 
    }
    
    public int authenticateUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(AUTHENTICATE_USER);)
        {
            stmt.setString(USERNAME, username);
            stmt.setString(PASSWORD, password);
            stmt.registerOutParameter("Result", Types.INTEGER);
            
            stmt.execute();
            return stmt.getInt("Result");
        }
    }
    
}
