package mbrs;

import Connectivity.ConnectClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Login {
    
     ConnectClass conclass = new ConnectClass();
        Connection connection = conclass.connectDb();

    @FXML
    private TextField uNameField;
    
    @FXML
    private PasswordField uPassField;
    
    @FXML
    private Label label;

    @FXML
    private Label label1;

    @FXML
    private Button gotoAdd;
    
    public static String thePass; 
    public static String theUser; 


    

    @FXML
    void gotoAddBalance(ActionEvent event) throws Exception {
        String user = uNameField.getText();
        String pass = uPassField.getText();
        try{
        
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users");

        
            ResultSet results;
            results = statement.executeQuery();

            while (results.next()) {
            String uname = results.getString("username");
            String upass = results.getString("password");
           // System.out.println(uname + " " + upass);
            
        if (uname.equals(user)) {
            if (upass.equals(pass)) {
                thePass = upass;
                theUser = uname;
                Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Home");
                stage.setScene(scene);
                stage.show();
            } else {
                PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
                visiblePause.setOnFinished(event1 -> label.setText(""));
                visiblePause.play();

                label.setText("Invalid Password");
            }
        } else {
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
            visiblePause.setOnFinished(event1 -> label1.setText(""));
            visiblePause.play();
            label1.setText("Invalid Username");
        }
        }
        } catch (SQLException sql)
        {
            System.out.println(sql);
        }
        

    }

}
