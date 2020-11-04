import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.sql.*;
import java.util.Iterator;
import java.util.Scanner;

public class hometask2 {


    private static Connection conn;
    private static Statement stmt;
    private static PreparedStatement pst;


    public static void main(String[] args) {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//       try {
//            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
//            ResultSetMetaData rsmd = rs.getMetaData();
//
//
//            for (int i = 1; i <= rsmd.getColumnCount() ; i++) {
//                System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnTypeName(i));
//            }
//
//            while(rs.next()){
//                System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getString("score"));
//            }
//            int res = stmt.executeUpdate("CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score TEXT )");
//            System.out.println(res);
//        long l = System.currentTimeMillis();
//        conn.setAutoCommit(false);
//        for (int i = 0; i <1000 ; i++) {
//               stmt.executeUpdate("INSERT INTO students(name, score) VALUES ('unknown', 100);");
//        }
//        conn.commit();
//            System.out.println(System.currentTimeMillis()-l);
//            pst = conn.prepareStatement("INSERT INTO students(name, score) VALUES (?, ?);");
//            conn.setAutoCommit(false);
//            for (int i = 0; i < 100; i++) {
//                pst.setString(1,"Bob "+ i);
//                pst.setInt(2,20 + i*10);
//                pst.addBatch();
//            }
//            pst.executeBatch();
//            conn.commit();
//
//           Savepoint spl = conn.setSavepoint();
//           stmt.executeUpdate("INSERT INTO students(name, score) VALUES ('bob1', 10);");
//           stmt.executeUpdate("INSERT INTO students(name, score) VALUES ('bob2', 20);");
//           stmt.executeUpdate("INSERT INTO students(name, score) VALUES ('bob3', 30);");
//       } catch (SQLException throwables) {
//         throwables.printStackTrace();
//       }
//       disconnect();
//-------------------------------------------------------------
        //1. Создать CRUD операции,
        // 1 метод создани таблицы
        try {
            stmt.executeUpdate("CREATE TABLE workers (id INTEGER PRIMARY KEY AUTOINCREMENT, worker_name TEXT, salary INT )");
            System.out.println("Created");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //2 метод для добавления записи
        try {
            stmt.executeUpdate("INSERT INTO workers (worker_name, salary) VALUES ('worker1', 10000);");
            stmt.executeUpdate("INSERT INTO workers (worker_name, salary) VALUES ('worker2', 20000);");
            stmt.executeUpdate("INSERT INTO workers (worker_name, salary) VALUES ('worker3', 30000);");
            stmt.executeUpdate("INSERT INTO workers (worker_name, salary) VALUES ('worker4', 40000);");
            System.out.println("Inserted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //2.1 метод для модификации записи
        try {
            stmt.executeUpdate("UPDATE workers SET salary = 1000000 WHERE worker_name = 'worker3';");
            System.out.println("Updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //3 метод для получения записи
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM workers;");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //4 метод для удаления записи
        try {
            stmt.executeUpdate("DELETE FROM workers WHERE worker_name = 'worker4';");
            System.out.println("Deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //5 удаление таблицы
        try {
            stmt.executeUpdate("DROP TABLE IF EXISTS workers;");
            System.out.println("Dropped");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //2. Обновить данные в БД из файла (файл приложен test.txt)


        String filename = "test.txt";
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            PreparedStatement pst1;

            pst1 = conn.prepareStatement("UPDATE students SET score = ? WHERE name = ?");
            conn.setAutoCommit(false);

            while(scanner.hasNext()){
                //System.out.println("line is: " + scanner.nextLine());
                String[] arr = scanner.nextLine().split(" ");
                System.out.println(arr[2] + " " + arr[1]);
                pst1.setInt(1, Integer.parseInt(arr[2]));
                pst1.setString(2, arr[1]);
                pst1.addBatch();
            }
            int[] res = pst1.executeBatch();
            conn.commit();
            System.out.println("rows updated: " + res[0]); //возвращает 1. Хотя реально модифицировано 3 строки
            System.out.println("SUCCESS_NO_INFO: " + res[1]); //тоже возвращает 1
            System.out.println("EXECUTE_FAILED: " + res[2]); //Тоже возвращает 1. Хотя апдейт удался. Все 3 записи были модифицированы.
            scanner.close();

        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        disconnect();

    }

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = conn.createStatement();
    }
    private static void disconnect(){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
