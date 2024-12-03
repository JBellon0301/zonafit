package zona_fit.connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDataBase {
    public static Connection getConnection(){
        Connection conexion = null;
        var baseDatos = "fit_zone_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "Password1811!";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch (Exception e){
            System.out.println("Error al conectarnos a la BD: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = ConnectionDataBase.getConnection();
        if(conexion != null)
            System.out.println("Conexion exitosa: " + conexion);
        else
            System.out.println("Error al conectarse");
    }
}
