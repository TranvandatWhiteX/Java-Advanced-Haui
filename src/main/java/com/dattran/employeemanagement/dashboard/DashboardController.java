package com.dattran.employeemanagement.dashboard;

import com.dattran.employeemanagement.database.ProductSystem;
import com.dattran.employeemanagement.model.ProductSystemObject;
import com.dattran.employeemanagement.uitl.ExtractExcel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.lang.model.type.NullType;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private TableView<ProductSystemObject> tableView;
    @FXML
    private TableColumn<ProductSystemObject, Integer> createAuthorIDColumn;
    @FXML
    private TableColumn<ProductSystemObject, String> createdColumn;
    @FXML
    private TableColumn<ProductSystemObject, String> deletedColumn;
    @FXML
    private TableColumn<ProductSystemObject, Integer> enableColumn;
    @FXML
    private TableColumn<ProductSystemObject, Integer> idColumn;
    @FXML
    private TableColumn<ProductSystemObject, Integer> managerIDColumn;
    @FXML
    private TableColumn<ProductSystemObject, String> modifiedColumn;
    @FXML
    private TableColumn<ProductSystemObject, String> nameColumn;
    @FXML
    private TableColumn<ProductSystemObject, String> notesColumn;
    @FXML
    private TextField notesTextField;

    @FXML
    private TextField ceatedAuthorTextField;
    @FXML
    private DatePicker createdTextField;

    @FXML
    private TextField deletedAuthorTextField;
    @FXML
    private DatePicker deletedDateTextField;

    @FXML
    private TextField deletedTextField;
    @FXML
    private TextField enableTextField;
    @FXML
    private TextField idTextField;

    @FXML
    private TextField languageTextField;
    @FXML
    private TextField managerIDTextField;
    @FXML
    private DatePicker modifiedTextField;
    @FXML
    private TextField nameENTextField;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField tableTextField;
    ObservableList<ProductSystemObject> list;
    ProductSystem productSystem = new ProductSystem();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAllProducts();
    }
    public void showAllProducts() {
        list = productSystem.getAllProducts();
        idColumn.setCellValueFactory(new PropertyValueFactory<ProductSystemObject, Integer>("productSystem_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ProductSystemObject, String>("productSystem_name"));
        managerIDColumn.setCellValueFactory(new PropertyValueFactory<ProductSystemObject, Integer>("productSystem_manager_id"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<ProductSystemObject, String>("productSystem_notes"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<ProductSystemObject, String>("productSystem_created_date"));
        modifiedColumn.setCellValueFactory(new PropertyValueFactory<ProductSystemObject, String>("productSystem_modified_date"));
        deletedColumn.setCellValueFactory(new PropertyValueFactory<ProductSystemObject, String>("productSystem_deleted_date"));
        createAuthorIDColumn.setCellValueFactory(new PropertyValueFactory<ProductSystemObject, Integer>("productSystem_created_author_id"));
        enableColumn.setCellValueFactory(new PropertyValueFactory<ProductSystemObject, Integer>("productSystem_enable"));
        tableView.setItems(list);
    }
    public void clearEvent(ActionEvent actionEvent) {
        idTextField.setText("");
        nameTextField.setText("");
        managerIDTextField.setText("");
        ceatedAuthorTextField.setText("");
        deletedTextField.setText("");
        enableTextField.setText("");
        languageTextField.setText("");
        nameENTextField.setText("");
        notesTextField.setText("");
        tableTextField.setText("");
        deletedAuthorTextField.setText("");
        createdTextField.setValue(null);
        modifiedTextField.setValue(null);
        deletedDateTextField.setValue(null);
    }
    public void closeEvent(ActionEvent actionEvent) {
        System.exit(0);
    }
    public void deleteEvent(ActionEvent actionEvent) {
        ProductSystemObject product = tableView.getSelectionModel().getSelectedItem();
        if (product == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please choose a product!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cofirmation Message");
            alert.setContentText("Are you sure you want to DELETE Product with ID: " + product.getProductSystem_id() + "?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                productSystem.deleteProduct(product);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();
                showAllProducts();
            }
        }
    }
    public void extractFileEvent(ActionEvent actionEvent) {
        ExtractExcel extractExcel = new ExtractExcel();
        boolean condition = extractExcel.extractExcel();
        Alert alert;
        if (condition) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notifications");
            alert.setContentText("Extract file successful!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notifications");
            alert.setContentText("Extract file failure!");
            alert.showAndWait();
        }
    }
}
