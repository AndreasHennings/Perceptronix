package model;

import java.sql.*;


public class DBController
{

    private static final DBController dbcontroller = new DBController();
    private static Connection connection;
    private String tablename = "table";
    //private static final String DB_PATH = System.getProperty("user.home") + "/" + "testdb.db";

    static // load database driver
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
        }

        catch (ClassNotFoundException e)
        {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    }

    private DBController() {}

    public static DBController getInstance()
    {
        return dbcontroller;
    }

    public static void main()
    {
        DBController dbc = DBController.getInstance();
        dbc.initDBConnection();
        dbc.createTable();
    }

    private void initDBConnection()
    {
        try
        {
            if (connection != null)
            {
                return;
            }
            System.out.println("Creating Connection to Database...");
            String path = System.getProperty("user.home") + "/" + "testdb.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            if (!connection.isClosed())
            {
                System.out.println("...Connection established");
            }
        }

        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                try
                {
                    if (!connection.isClosed() && connection != null)
                    {
                        connection.close();
                        if (connection.isClosed())
                            System.out.println("Connection to Database closed");
                    }
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createTable()
    {

        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS mytable");
            statement.executeUpdate("CREATE TABLE mytable (" +
                    "keyword, " +
                    "correlation, " +
                    "c1, " +
                    "c2, " +
                    "c3, " +
                    "c4, " +
                    "c5, " +
                    "c6, " +
                    "c7);");
            System.out.println("table created");
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void updateEntry(String keyword, String cat)
    {
        try
        {
            Statement statement = connection.createStatement();
            try
            {
                statement.executeUpdate("UPDATE mytable SET "+cat+" = "+cat+" + 1 WHERE keyword = "+keyword);
            }

            catch (Exception e)
            {
                // statement.executeUpdate("INSERT INTO mytable (keyword, correlation, c1,c2,c3,c4,c5,c6,c7) VALUES (keyword, 0.0, 0, 0, 0, 0, 0, 0, 0);");


                PreparedStatement ps = connection.prepareStatement("INSERT INTO mytable VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");

                ps.setString(1, keyword);
                ps.setDouble(2, 0.0);
                for (int i = 3; i < 10; i++)
                {
                    ps.setInt(i, 0);
                }

                ps.execute();
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void getData()
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from mytable");
            while (rs.next())
            {
                String keyword = rs.getString("keyword");
                System.out.println(keyword);

            }

        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void handleDB()
    {
        try
        {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS books;");
            stmt.executeUpdate("CREATE TABLE books (author, title, publication, pages, price);");
            //stmt.execute("INSERT INTO books (author, title, publication, pages, price) VALUES ('Paulchen Paule', 'Paul der Penner', '2001-05-06', '1234', '5.67')");
            /*
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?);");

            ps.setString(1, "Willi Winzig");
            ps.setString(2, "Willi's Wille");
            ps.setDate(3, Date.valueOf("2011-05-16"));
            ps.setInt(4, 432);
            ps.setDouble(5, 32.95);
            ps.addBatch();

            ps.setString(1, "Anton Antonius");
            ps.setString(2, "Anton's Alarm");
            ps.setDate(3, Date.valueOf("2009-10-01"));
            ps.setInt(4, 123);
            ps.setDouble(5, 98.76);
            ps.addBatch();

            connection.setAutoCommit(false);
            ps.executeBatch();
            connection.setAutoCommit(true);

            ResultSet rs = stmt.executeQuery("SELECT * FROM books;");
            while (rs.next())
            {
                System.out.println("Autor = " + rs.getString("author"));
                System.out.println("Titel = " + rs.getString("title"));
                //System.out.println("Erscheinungsdatum = "+ rs.getDate("publication"));
                System.out.println("Seiten = " + rs.getInt("pages"));
                System.out.println("Preis = " + rs.getDouble("price"));
            }
            */
            //rs.close();
            connection.close();
        } catch (SQLException e)
        {
            System.err.println("Couldn't handle DB-Query");
            e.printStackTrace();
        }
    }


}