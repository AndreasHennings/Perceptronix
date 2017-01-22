package MEM;

import BUS.MessageListener;

import java.sql.*;
import java.util.ArrayList;


public class DBController
{

    MessageListener ml;
    String[]categories;

    private static final DBController dbcontroller = new DBController();
    private static Connection connection;

    static // load database driver
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e)
        {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    }

    private DBController()
    {
    }

    public static DBController getInstance()
    {
        return dbcontroller;
    }

    public static void main(MessageListener ml, String[] categories)
    {
        DBController dbc = DBController.getInstance();
        dbc.setListener(ml, categories);
        dbc.initDBConnection();
        dbc.createTable();
    }

    public void setListener(MessageListener ml, String[] categories)
    {
        this.ml=ml;
        this.categories=categories;
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
                System.out.println("Connection to database successfully established");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
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
            //statement.executeUpdate("DROP TABLE IF EXISTS mytable");
            String command = "CREATE TABLE IF NOT EXISTS mytable (keyword, ";
            for (int i =0; i<categories.length-1; i++)
            {
                command+=categories[i];
                command+=", ";
            }
            command+=categories[categories.length-1];
            command+=");";
            /*
                    "correlation, " +
                    "c1, " +
                    "c2, " +
                    "c3, " +
                    "c4, " +
                    "c5, " +
                    "c6, " +
                    "c7);");
                    */
            statement.executeUpdate(command);
            System.out.println("Database table created"+ command);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public void updateEntry(String keyword, String cat) // neu, c1
    {
        try
        {
            Statement statement = connection.createStatement();

            String s = "UPDATE mytable SET " + cat + " = " + cat + " + 1 WHERE keyword = '" + keyword + "';";

            int a = statement.executeUpdate(s);

            if (a > 0)
            {
                System.out.println("value updated ");
            }

            else
            {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO mytable VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");

                ps.setString(1, keyword);
                //System.out.println(keyword);
                ps.setDouble(2, 0.0);
                for (int i = 3; i < 10; i++)
                {
                    ps.setInt(i, 0);
                }

                ps.execute();
                System.out.println("entry made");

                statement.executeUpdate("UPDATE mytable SET " + cat + " = " + cat + " + 1 WHERE keyword = '" + keyword + "';");
                System.out.println("value updated2");
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public ResultSet getData()
    {



        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from mytable");

            String entry ="";
            while (rs.next())
            {

                String keyword = rs.getString(1);
                entry+=keyword+" ";


                double c = rs.getDouble(2);
                entry+=" "+c;
                int[] nums = new int[7];
                for (int i = 0; i < nums.length; i++)
                {
                    entry+= " "+rs.getInt(i + 3);
                }
            }
            System.out.println(entry);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
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