package com.dattran.employeemanagement.dashboard;

import com.dattran.employeemanagement.database.ProductSystem;
import com.dattran.employeemanagement.model.ProductSystemObject;
import com.dattran.employeemanagement.uitl.ExtractExcel;
import javafx.collections.FXCollections;
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
    private TextField createdAuthorTextField;
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
    @FXML
    private TextField searchTextField;
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
        createdAuthorTextField.setText("");
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
    public void addEvent(ActionEvent actionEvent) {
        int enable, managerId, createdAuthorId, language, psDelete;
        Alert alert;
        try {
            enable = Integer.parseInt(enableTextField.getText());
            managerId = Integer.parseInt(managerIDTextField.getText());
            createdAuthorId = Integer.parseInt(createdAuthorTextField.getText());
            language = Integer.parseInt(languageTextField.getText());
            psDelete = Integer.parseInt(deletedTextField.getText());
            ProductSystemObject product = new ProductSystemObject(
                    nameTextField.getText(), managerId, psDelete,
                    notesTextField.getText(), createdTextField.getValue().toString(), deletedDateTextField.getValue().toString(),
                    modifiedTextField.getValue().toString(), deletedAuthorTextField.getText(), tableTextField.getText(), enable,
                    nameENTextField.getText(), createdAuthorId, language
            );
            boolean condition = productSystem.addProduct(product);
            if (condition) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notifications");
                alert.setContentText("Add product successful!");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notifications");
                alert.setContentText("Add product failure!");
                alert.showAndWait();
            }
            showAllProducts();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Notifications");
            alert.setContentText("Please check your input, there are some fields that must be positive integer!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateEvent(ActionEvent actionEvent) {
        Alert alert;
        int enable, managerId, createdAuthorId, language, psDelete;
        try {
            int id = Integer.parseInt(idTextField.getText());
            ProductSystemObject product = productSystem.getProductById(id);
            if (product == null) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Notifications");
                alert.setContentText("Product not found!");
                alert.showAndWait();
            } else {
                if (!enableTextField.getText().isEmpty()) {
                    enable = Integer.parseInt(enableTextField.getText());
                    product.setProductSystem_enable(enable);
                }
                if (!managerIDTextField.getText().isEmpty()) {
                    managerId = Integer.parseInt(managerIDTextField.getText());
                    product.setProductSystem_manager_id(managerId);
                }
                if (!createdAuthorTextField.getText().isEmpty()) {
                    createdAuthorId = Integer.parseInt(createdAuthorTextField.getText());
                    product.setProductSystem_created_author_id(createdAuthorId);
                }
                if (!languageTextField.getText().isEmpty()) {
                    language = Integer.parseInt(languageTextField.getText());
                    product.setProductSystem_language(language);
                }
                if (!deletedTextField.getText().isEmpty()) {
                    psDelete = Integer.parseInt(deletedTextField.getText());
                    product.setProductSystem_delete(psDelete);
                }
                if (!nameTextField.getText().isEmpty()) {
                    product.setProductSystem_name(nameTextField.getText());
                }
                if (!notesTextField.getText().isEmpty()) {
                    product.setProductSystem_notes(notesTextField.getText());
                }
                if (createdTextField.getValue() != null) {
                    product.setProductSystem_created_date(createdTextField.getValue().toString());
                }
                if (deletedDateTextField.getValue() != null) {
                    product.setProductSystem_deleted_date(deletedDateTextField.getValue().toString());
                }
                if (modifiedTextField.getValue() != null) {
                    product.setProductSystem_modified_date(modifiedTextField.getValue().toString());
                }
                if (!tableTextField.getText().isEmpty()) {
                    product.setProductSystem_table(tableTextField.getText());
                }
                if (!deletedAuthorTextField.getText().isEmpty()) {
                    product.setProductSystem_deleted_author(deletedAuthorTextField.getText());
                }
                if (!nameENTextField.getText().isEmpty()) {
                    product.setProductSystem_name_en(nameENTextField.getText());
                }
                productSystem.updateProduct(product);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notifications");
                alert.setContentText("Update product success!");
                alert.showAndWait();
                showAllProducts();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Notifications");
            alert.setContentText("Please check your input, there are some fields that must be positive integer!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchEvent(ActionEvent actionEvent) {
        ObservableList<ProductSystemObject> newList = FXCollections.observableArrayList();
        String textSearch = searchTextField.getText().trim();
        list = productSystem.getAllProducts();
        for (ProductSystemObject p : list) {
            if (p.getProductSystem_name().contains(textSearch) || p.getProductSystem_name().equals(textSearch)) {
                newList.add(p);
            }
        };
        tableView.setItems(newList);
    }
    public void showAllListEvent(ActionEvent actionEvent) {
        showAllProducts();
    }
}
