import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Datos {
    int cont=0;
    //datos para ingresar a la base
    static String DB_URL="jdbc:mysql://localhost/LOGINCHIDO";
    static String USER="root";
    static String PASS="root_bas3";
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
    private JButton DELETEButton;
    private JButton SELECTButton;
    private JTextField TIDDELETE;
    private JLabel LDELETE;
    private JTextField IDUP;
    private JButton UPDATEButton;
    private JTextField TNUEVON;
    private JLabel LNUEVON;
    private JLabel LABEL1;

    public Datos() {
        SELECTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String nombre, apellido, edad,ciudad,id,cedula;*/
                String QUERY="SELECT * FROM EJERCICIO1";
                try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
                    Statement stmt = conn.createStatement();
                    ResultSet rs= stmt.executeQuery(QUERY);)
                {
                    while(rs.next()){
                        System.out.println("Id: "+rs.getInt("ID"));
                        System.out.println("Nombre: "+rs.getString("NOMBRE"));
                        System.out.println("Apellido: "+rs.getString("APELLIDO"));
                        System.out.println("Edad: "+rs.getInt("EDAD"));
                        System.out.println("Ciudad: "+rs.getString("CIUDAD"));
                        System.out.println("Cedula: "+rs.getInt("CEDULA"));
                        /*id=String.valueOf(rs.getInt("id"));
                        nombre=String.valueOf(rs.getInt("id"));
                        edad=rs.getString("NOMBRE");
                        ciudad=rs.getString("CIUDAD");
                        cedula= String.valueOf(rs.getInt("CEDULA"));*/
                        System.out.println("***********************");
                    }
                    System.out.println("-------------------------------------------");
                }
                catch (SQLException eX){
                    throw new RuntimeException(eX);
                }
            }
        });
        ENVIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int contador,ID;
                cont++;
                contador=cont;
                String NOMBRE, APELLIDO,EDAD,CIUDAD,CEDULA;

                NOMBRE = TNOMBRE.getText();
                APELLIDO = TAPELLIDO.getText();
                EDAD = TEDAD.getText();
                CIUDAD = TCIUDAD.getText();
                CEDULA = TCEDULA.getText();
                TID.setText(String.valueOf(contador));
                ID= Integer.parseInt(TID.getText());
                System.out.println(NOMBRE);
                System.out.println(APELLIDO);
                String QUERY="INSERT INTO EJERCICIO1(ID,NOMBRE,APELLIDO,EDAD,CIUDAD,CEDULA)" +
                        "VALUES(?,?,?,?,?,?)";
                try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS))
                {
                    PreparedStatement statement = conn.prepareStatement(QUERY);
                    // Establecer valores para los parámetros de la sentencia SQL
                    statement.setInt(1,ID);
                    statement.setString(2, NOMBRE);
                    statement.setString(3, APELLIDO);
                    statement.setString(4, EDAD);
                    statement.setString(5, CIUDAD);
                    statement.setString(6, CEDULA);
                    // Ejecutar sentencia SQL
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        LABEL1.setText("Datos insertados correctamente");
                        System.out.println("Datos insertados correctamente");
                    }
                }
                catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idToDelete;
                idToDelete= Integer.parseInt(TIDDELETE.getText());
                String DELETE_QUERY = "DELETE FROM EJERCICIO1 WHERE ID = ?";
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                    PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
                    // Establecer valor para el parámetro del ID que deseas eliminar
                    statement.setInt(1, idToDelete);

                    // Ejecutar sentencia DELETE
                    int rowsDeleted = statement.executeUpdate();

                    if (rowsDeleted > 0) {
                        LABEL1.setText("Se eliminó la fila con ID " + idToDelete + " correctamente.");
                    } else {
                        LABEL1.setText("No se encontró ninguna fila con el ID " + idToDelete + ".");
                    }
                } catch (SQLException eX) {
                    eX.printStackTrace();
                }
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idToUpdate; // Reemplaza 1 con el ID de la fila que deseas actualizar
                String nuevoNombre; //
                idToUpdate= Integer.parseInt(IDUP.getText());
                nuevoNombre=TNUEVON.getText();
                String UPDATE_QUERY = "UPDATE EJERCICIO1 SET NOMBRE = ? WHERE ID = ?";

                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                    PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
                    // Establecer nuevos valores para los campos NOMBRE y APELLIDO
                    statement.setString(1,nuevoNombre);
                    // Establecer valor para el parámetro del ID de la fila que deseas actualizar
                    statement.setInt(2, idToUpdate);

                    // Ejecutar sentencia UPDATE
                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {
                        LABEL1.setText("Se actualizó la fila con ID " + idToUpdate + " correctamente.");
                        System.out.println("Se actualizó la fila con ID " + idToUpdate + " correctamente.");
                    } else {
                        LABEL1.setText("No se encontró ninguna fila con el ID " + idToUpdate + ".");
                        System.out.println("No se encontró ninguna fila con el ID " + idToUpdate + ".");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Datos");
        frame.setContentPane(new Datos().pantalla);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
