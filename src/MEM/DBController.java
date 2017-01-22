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

            statement.executeUpdate(command);

            ml.onMessage("Database command: "+command);
        }

        catch (Exception e)
        {
            ml.onMessage("Database error: "+e.getMessage().toString());
        }

    }


    public void updateEntry(String keyword, String cat) // neu, c1
    {
        try
        {
            Statement statement = connection.createStatement();
            String command = "UPDATE mytable SET " + cat + " = " + cat + " + 1 WHERE keyword = '" + keyword + "';";
            ml.onMessage("Database command:"+command);
            int a =statement.executeUpdate(command);

            if (a>0)
            {
                ml.onMessage("Value updated");
            }

            else
            {

                command="INSERT INTO mytable VALUES (";
                command+="'"+keyword+"', ";
                for (int i=0; i<categories.length-1; i++)
                {
                    command+="0, ";
                }
                command+="0);";

                ml.onMessage(command);
                statement.executeUpdate(command);
                ml.onMessage("Entry created");

                statement.executeUpdate("UPDATE mytable SET " + cat + " = " + cat + " + 1 WHERE keyword = '" + keyword + "';");
                ml.onMessage("Updated new entry");
            }
        }

        catch (SQLException e)
        {
            ml.onMessage("Database error: "+e.getMessage().toString());
        }

    }

    public ResultSet getData()
    {
        ResultSet result=null;
        try
        {
            Statement statement = connection.createStatement();
            result = statement.executeQuery("SELECT * from mytable");
        }

        catch (SQLException e)
        {
            ml.onMessage("Database error: "+e.getMessage().toString());
        }

        return result;
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