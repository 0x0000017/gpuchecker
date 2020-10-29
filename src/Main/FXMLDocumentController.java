
package Main;

import Connectivity.ConnectionClass;
import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class FXMLDocumentController implements Initializable {

    @FXML
    private Label dbStatusLabel;
    
    @FXML
    private Button deleteRecord;    
        
    @FXML
    private Button updateRecord;    

    @FXML
    private Button loginButton;
    
    @FXML
    private Button reloadButton;
    
    @FXML
    private Button add;    
    
    @FXML
    private Button searchButton;
    
    @FXML
    private Button helpButton;  
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnEdit;
    
    @FXML
    private Pane home_pane;
    
    @FXML
    private Pane item_pane;
    
    @FXML
    private Pane man_pane;
    
    @FXML
    private TextField searchField;
        
    @FXML
    private Label dbStatusText;

    @FXML
    private TextField textFieldOne;

    @FXML
    private TextField textFieldTwo;
        
    @FXML
    private TextField textFieldThree;       
    
    @FXML
    private TextField textFieldFour;

    @FXML
    private TextField textFieldFive;
    
    @FXML
    private TextField manTextField;
    
    @FXML
    private TableView<Items> tableView;
    
    @FXML
    private TableColumn<Items, Integer> colId; // First Column to
    
    @FXML
    private TableColumn<Items, String> colName; // Second
    
    @FXML
    private TableColumn<Items, Integer> colQuantity; // Third
      
    @FXML
    private TableColumn<Items, Double> colPrice; // +1
    
     @FXML
     private TableColumn<Items, Integer> colMemory;
    
    @FXML
     private TableColumn<Items, String> colVendor; // +1
    
    @FXML
     private TableColumn<Items, Integer> colVendorID;
    
   @FXML
    private TableView <Manufacturer> manTableView;
   
   @FXML
   private TableColumn<Manufacturer, Integer> manId;
   
   @FXML
   private TableColumn<Manufacturer, String> manName;
    
    @FXML 
    private javafx.scene.control.Button exitButton;
    
    ObservableList<Items> obList = FXCollections.observableArrayList();
    ObservableList<Manufacturer> manList = FXCollections.observableArrayList();
    ObservableList<Items> dataList;
    
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
    int index = -1;

     @FXML
    private void addRecord(ActionEvent event) throws SQLException {

        String name = textFieldOne.getText();
        String quantityText = textFieldTwo.getText();
        int quantity = Integer.parseInt(quantityText);
        String priceText = textFieldThree.getText();
        double price = Double.parseDouble(priceText);
        
        String memoryText = textFieldFour.getText();
        int memory = Integer.parseInt(memoryText);
        String vendorIDtext = textFieldFive.getText();
        int vendorID = Integer.parseInt(vendorIDtext);

        String sql1 = "INSERT INTO tbl_items (item_name, item_quantity, item_price, item_memory, vendor_ID) VALUES ( '"+name+"', '"+quantity+"', '"+price+"', '"+memory+"', '"+vendorID+"');";
        Statement exec= connection.createStatement();
        exec.executeUpdate(sql1);
        
        loadTable();

        dbStatusText.setText("Added a new entry for Manufacturer ID: =  '"+vendorID+"'.");
        
        // invoke lang din to para maclear yung mga text fields
        resetFields();
    }
    
    @FXML
    private void loadTable() {
        
        obList.clear();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT tbl_items.item_ID, tbl_items.item_name, tbl_items.item_quantity, tbl_items.item_price, tbl_items.item_memory, tbl_items.vendor_ID, tbl_misc.misc_ID, tbl_misc.misc_vendorName FROM tbl_misc JOIN tbl_items ON tbl_misc.misc_ID = tbl_items.vendor_ID;");
            while (rs.next()) {
                obList.add(new Items(rs.getInt("item_ID"),
                        rs.getString("item_name"),
                        rs.getInt("item_quantity"),
                        rs.getDouble("item_price"),
                        rs.getInt("item_memory"),
                        rs.getString("misc_vendorName"),
                        rs.getInt("misc_ID")
                        
                ));
                
            colId.setCellValueFactory(new PropertyValueFactory<>("item_ID"));
            colName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
            colQuantity.setCellValueFactory(new PropertyValueFactory<>("item_quantity"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("item_price"));
            colMemory.setCellValueFactory(new PropertyValueFactory<>("item_memory"));
            colVendor.setCellValueFactory(new PropertyValueFactory<>("misc_vendorName"));
            colVendorID.setCellValueFactory(new PropertyValueFactory<>("misc_ID"));
            
            
            //eto para magkaron na ng laman yung observable list galing tableview
            tableView.setItems(obList);
            dbStatusText.setText("Table Loaded.'");
            }
         
        } catch (SQLException ex) {
            // pwede ka maglagay ng pop ups dito
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    private void selectData(MouseEvent event) {
        index = tableView.getSelectionModel().getSelectedIndex();
        if ( index <= -1 ) {
            
            return;
        }
            textFieldOne.setText(colName.getCellData(index));
            textFieldTwo.setText(colQuantity.getCellData(index).toString());
            textFieldThree.setText(colPrice.getCellData(index).toString());
            textFieldFour.setText(colMemory.getCellData(index).toString());
            textFieldFive.setText(colVendorID.getCellData(index).toString());
    }
    
    @FXML
    private void updateData() throws SQLException {

        
        String name = textFieldOne.getText();
        String quantityText = textFieldTwo.getText();
        int quantity = Integer.parseInt(quantityText);
        String priceText = textFieldThree.getText();
        double price = Double.parseDouble(priceText);
        
        String memoryText = textFieldFour.getText();
        int memory = Integer.parseInt(memoryText);
        String vendorIDtext = textFieldFive.getText();
        int vendorID = Integer.parseInt(vendorIDtext);
        
        
        int id1;
        id1 = colId.getCellData(index);
        // palitan mo lang din tong sql query pag may iibahin ka sa table mo
        String sql1 = "UPDATE tbl_items SET item_name = '"+name+"', item_quantity = '"+quantity+"', item_price = '"+price+"', item_memory = '"+memory+"', vendor_ID = '"+vendorID+"' WHERE item_ID = "+id1+" ; ";
        Statement exec = connection.createStatement();
        exec.executeUpdate(sql1);
        
        loadTable();
        dbStatusText.setText("Updated a record for ID =  '"+id1+"'.");
        
        resetFields();
    }
    
    @FXML
    private void deleteRecord() throws SQLException {
        
        int ID;
        ID= colId.getCellData(index);

        String sql1 = "DELETE FROM tbl_items WHERE item_id = "+ID+ ";";
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        String s = "Confirm to delete Item ?";
        alert.setContentText(s);
           Optional<ButtonType> result = alert.showAndWait();
           if((result.isPresent()) && (result.get() == ButtonType.OK)) {
            Statement exec = connection.createStatement();
            exec.executeUpdate(sql1);
                Alert conf = new Alert(AlertType.INFORMATION);
                conf.setTitle("Information");
                conf.setHeaderText(null);
                conf.setContentText("Deleted");
                conf.showAndWait();
                loadTable();
                dbStatusText.setText("Deleted a record from ID =  '"+ID+"'.");
         }
        resetFields();
        
    }
    @FXML
    private void resetFields() {
         // reset

        textFieldOne.clear();
        textFieldTwo.clear();
        textFieldThree.clear();
        textFieldFour.clear();
        textFieldFive.clear();
        
    }
    @FXML
    private void searchRecords() {
        String find = searchField.getText();
        obList.clear();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT tbl_items.item_ID, tbl_items.item_name, tbl_items.item_quantity, tbl_items.item_price, tbl_items.item_memory, tbl_items.vendor_ID, tbl_misc.misc_ID, tbl_misc.misc_vendorName FROM tbl_misc JOIN tbl_items ON tbl_misc.misc_ID = tbl_items.vendor_ID WHERE item_name LIKE '%"+find+"%';");
           while (rs.next()) {
                obList.add(new Items(rs.getInt("item_ID"),
                        rs.getString("item_name"),
                        rs.getInt("item_quantity"),
                        rs.getDouble("item_price"),
                        rs.getInt("item_memory"),
                        rs.getString("misc_vendorName"),
                        rs.getInt("misc_ID")
                        
                ));
            colId.setCellValueFactory(new PropertyValueFactory<>("item_ID"));
            colName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
            colQuantity.setCellValueFactory(new PropertyValueFactory<>("item_quantity"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("item_price"));
            colMemory.setCellValueFactory(new PropertyValueFactory<>("item_memory"));
            colVendor.setCellValueFactory(new PropertyValueFactory<>("misc_vendorName"));
            colVendorID.setCellValueFactory(new PropertyValueFactory<>("misc_ID"));
            
            tableView.setItems(obList);
            dbStatusText.setText("Table Loaded.'");
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void showHome()  {
        home_pane.setVisible(true);
        item_pane.setVisible(false);
        man_pane.setVisible(false);
    }
    
       @FXML
    private void showItemTable(ActionEvent event) {
        home_pane.setVisible(false);
        item_pane.setVisible(true);
        man_pane.setVisible(false);
    }
    
       @FXML
    private void showManTable(ActionEvent event) {
        home_pane.setVisible(false);
        item_pane.setVisible(false);
        man_pane.setVisible(true);
    }
    
    @FXML
    private void loadManTable() {
        
    manList.clear();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM tbl_misc;");
            while (rs.next()) {
                manList.add(new Manufacturer(rs.getInt("misc_ID"),
                        rs.getString("misc_vendorName")
                ));
            manId.setCellValueFactory(new PropertyValueFactory<>("misc_ID"));
            manName.setCellValueFactory(new PropertyValueFactory<>("misc_vendorName"));

            manTableView.setItems(manList);
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void selectManTable(MouseEvent event) {
        index = manTableView.getSelectionModel().getSelectedIndex();
        if ( index <= -1 ) {
            
            return;
        }
            manTextField.setText(manName.getCellData(index));
        }
    
    @FXML
    private void addMan() throws SQLException {
        
        String mName = manTextField.getText();
        String sql1 = "INSERT INTO tbl_misc (misc_vendorName) VALUES ('"+mName+"');";
        Statement exec= connection.createStatement();
        exec.executeUpdate(sql1);
        loadManTable();
        
        manTextField.clear();
    }
    
    @FXML
    private void deleteMan() throws SQLException {
        int ID = manId.getCellData(index);
        
        String sql1 = "DELETE FROM tbl_misc WHERE misc_ID = "+ID+ ";";
         Alert alert = new Alert(AlertType.CONFIRMATION);
        String s = "Confirm to delete ?";
        alert.setContentText(s);
           Optional<ButtonType> result = alert.showAndWait();
           if((result.isPresent()) && (result.get() == ButtonType.OK)) {
               
            Statement exec = connection.createStatement();
            exec.executeUpdate(sql1);
            
                Alert conf = new Alert(AlertType.INFORMATION);
                conf.setTitle("Information");
                conf.setHeaderText(null);
                conf.setContentText("Deleted");
                conf.showAndWait();
                manTextField.clear();
             }
             loadManTable();
    }
    
    @FXML
    private void updateMan() throws SQLException {
        
        String mName = manTextField.getText();
        int id = manId.getCellData(index);
        String sql1 = "UPDATE tbl_misc SET misc_vendorName = '"+mName+"' WHERE misc_ID = "+id+"; ";
        Statement exec = connection.createStatement();
        exec.executeUpdate(sql1);
        loadManTable();
        manTextField.clear();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showHome();
    }
}
