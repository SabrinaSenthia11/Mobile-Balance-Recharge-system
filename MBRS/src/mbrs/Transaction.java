package mbrs;

import Connectivity.ConnectClass;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Transaction implements Initializable {

    ConnectClass conclass = new ConnectClass();
    Connection connection = conclass.connectDb();

    @FXML
    private Button btBack;

    @FXML
    private TableView<Table> tblview;

    @FXML
    private TableColumn<Table, String> tblCol1;

    @FXML
    private TableColumn<Table, String> tblCol2;

    @FXML
    private TableColumn<Table, String> tblCol3;

    @FXML
    private TableColumn<Table, String> tblCol4;

    @FXML
    private TableColumn<Table, String> tblCol5;

    @FXML
    private Label lblShowHistory;

    @FXML
    private ChoiceBox<String> chcAddRecharge;
    String choice[] = {"Recharge", "Add Balance"};

    @FXML
    private Label lblTransaction;

    @FXML
    private ImageView backImage;
    
    ObservableList<Table> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chcAddRecharge.getItems().addAll(choice);
        chcAddRecharge.setOnAction(this::setTable);
        
        tblCol1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        tblCol2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        tblCol3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        tblCol4.setCellValueFactory(new PropertyValueFactory<>("col4"));
        tblCol5.setCellValueFactory(new PropertyValueFactory<>("col5"));
        
    }

    @FXML
    void gotoHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void setTable(ActionEvent event) {
        if (chcAddRecharge.getValue().equals("Add Balance")) {
            
            tblCol1.setText("Account Number");
            tblCol2.setText("Amount");
            tblCol3.setText("Date");
            tblCol4.setText("Method");
            tblCol5.setText("");

           for ( int i = 0; i<tblview.getItems().size(); i++) {
                tblview.getItems().clear();
            }
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM AddBal");
                ResultSet results;
                results = statement.executeQuery();
                
                
                while (results.next()) {
                    String acc = results.getString("AccNo");
                    String amnt = results.getString("Amount");
                    String dt = results.getString("Date");
                    String meth = results.getString("Method");
                    String some = " ";
                    
//                    System.out.println(acc);
//                    System.out.println(amnt);
//                    System.out.println(dt);
//                    System.out.println(meth);

                   list.add(new Table(acc,amnt,dt,meth,some));
                }
               tblview.setItems(list);
                
                    
                
            } catch (SQLException sql) {
                System.out.println(sql);
            }

        }
        else if(chcAddRecharge.getValue().equals("Recharge"))
        {
            tblCol1.setText("Contact Number");
            tblCol2.setText("Amount");
            tblCol3.setText("Sim Type");
            tblCol4.setText("Sim Operator");
            tblCol5.setText("Date");
           for ( int i = 0; i<tblview.getItems().size(); i++) {
                tblview.getItems().clear();
            }
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Recharge");
                ResultSet results;
                results = statement.executeQuery();
                
                
                while (results.next()) {
                    String acc = results.getString("Cnum");
                    String amnt = results.getString("Amount");
                    String dt = results.getString("Type");
                    String meth = results.getString("Sim");
                    String some = results.getString("Date");

                   list.add(new Table(acc,amnt,dt,meth,some));
                }
               tblview.setItems(list);
                
                    
                
            } catch (SQLException sql) {
                System.out.println(sql);
            }
        }
    }
}
