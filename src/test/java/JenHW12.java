import java.sql.*;

public class JenHW12 {
    private static final String USER_NAME = "PeUPHL9vz7";
    private static final String DATABASE_NAME = "PeUPHL9vz7";
    private static final String PASSWORD = "hLIsECBJ4L";
    private static final String PORT = "3306";
    private static final String SERVER = "remotemysql.com";

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://" + SERVER + ":" + PORT, USER_NAME, PASSWORD);

        //createTable(con);
//        insertDog(con, 111, "dog1");
//        insertDog(con, 222, "dog2");
//        insertDog(con, 333, "dog3");
        deleteDogByName(con, "dog3");
        updateDogName(con, 222, "dog222");
        getTableContent(con);
        con.close();
    }

    private static void createTable(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`dogs`(`id` INT NOT NULL,`name` VARCHAR(45) NOT NULL, PRIMARY KEY (`id`));";
        con.createStatement().execute(statementToExecute);
    }

    private static void insertDog(Connection con, int id, String name) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".dogs (`id`, `name`) VALUES ('" + id + "', '" + name + "');";
        con.createStatement().execute(statementToExecute);
    }

    private static void getTableContent(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".dogs;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while (rs.next()) {
            //Retrieve by column name
            int id = rs.getInt("id");
            String name = rs.getString("name");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Name: " + name);
        }
        rs.close();
    }

    private static void deleteDogByName(Connection con, String name) throws SQLException {
        String statementToExecute = "DELETE FROM `" + DATABASE_NAME + "`.`dogs` WHERE `name`='" + name + "';";
        con.createStatement().execute(statementToExecute);
    }

    private static void updateDogName(Connection con, int id, String name) throws SQLException {
        String statementToExecute = "UPDATE `" + DATABASE_NAME + "`.`dogs` SET `name`='" + name + "' WHERE `id`='" + id + "';";
        con.createStatement().executeUpdate(statementToExecute);
    }
}
