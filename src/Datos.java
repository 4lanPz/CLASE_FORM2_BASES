import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Datos {
    String NOMBRE, APELLIDO;
    private JPanel pantalla;
    private JTextField TNOMBRE;
    private JTextField TAPELLIDO;
    private JTextField TEDAD;
    private JTextField TCIUDAD;
    private JTextField TCEDULA;
    private JButton ENVIARButton;
    private JLabel LTitulo;
    private JLabel LID;
    private JLabel LNOMBRE;
    private JLabel LAPELLIDO;
    private JLabel LEDAD;
    private JLabel LCIUDAD;
    private JLabel LCEDULA;
    private JLabel TID;

    public Datos() {
        ENVIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NOMBRE = TNOMBRE.getText();
                APELLIDO = TAPELLIDO.getText();
                System.out.println(NOMBRE);
                System.out.println(APELLIDO);
                login();
            }
        });
    }

    private void login() {
        int cont=0,contador;
        cont++;
        contador=cont;
        String DB_URL="jdbc:mysql://localhost/LOGINCHIDO";
        final String USER="root";
        final String PASS="root_bas3";
        final String QUERY="INSERT INTO DATOS_EJERCICIO(ID,NOMBRE,APELLIDO)" +
                "VALUES()";
        try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();)
        {
            PreparedStatement statement = conn.prepareStatement(QUERY);
            // Establecer valores para los parámetros de la sentencia SQL
            statement.setInt(1,contador);
            statement.setString(2, NOMBRE);
            statement.setString(3, APELLIDO);
            // Ejecutar sentencia SQL
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 1) {
                System.out.println("Datos insertados correctamente");
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Datos");
        frame.setContentPane(new Datos().pantalla);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
