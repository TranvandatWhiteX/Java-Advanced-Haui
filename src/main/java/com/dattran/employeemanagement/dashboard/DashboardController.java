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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully Deleted!");
                showAllProducts();
            }
        }
    }
    public void extractFileEvent(ActionEvent actionEvent) {
        ExtractExcel extractExcel = new ExtractExcel();
        boolean condition = extractExcel.extractExcel();
        if (condition) {
            showAlert(Alert.AlertType.INFORMATION, "Notifications", "Extract file successful!");
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Notifications", "Extract file failure!");
        }
    }
    public void showAlert(Alert.AlertType alertType, String title, String contenText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contenText);
        alert.showAndWait();
    }
    public void addEvent(ActionEvent actionEvent) {
        // Add function must have name, other field if not has default value
        // All field cannot be null because of database's design
        int enable, managerId, createdAuthorId, language, psDelete;
        try {
            // If text field is null, it has default value = 0
            if (!enableTextField.getText().isEmpty()) {
                enable = Integer.parseInt(enableTextField.getText());
            } else {
                enable = 0;
            }
            if (!managerIDTextField.getText().isEmpty()) {
                managerId = Integer.parseInt(managerIDTextField.getText());
            } else {
                managerId = 0;
            }
            if (!createdAuthorTextField.getText().isEmpty()) {
                createdAuthorId = Integer.parseInt(createdAuthorTextField.getText());
            } else {
                createdAuthorId = 0;
            }
            if (!languageTextField.getText().isEmpty()) {
                language = Integer.parseInt(languageTextField.getText());
            } else {
                language = 0;
            }
            if (!deletedTextField.getText().isEmpty()) {
                psDelete = Integer.parseInt(deletedTextField.getText());
            } else {
                psDelete = 0;
            }
            if (nameTextField.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning", "You must enter name of product at least!");
            } else {
                String createdDate, modifiedDate, deletedDate;
                if (createdTextField.getValue() != null) {
                    createdDate = createdTextField.getValue().toString();
                } else {
                    createdDate = LocalDate.now().toString();
                }
                if (modifiedTextField.getValue() != null) {
                    modifiedDate = modifiedTextField.getValue().toString();
                } else {
                    modifiedDate = LocalDate.now().toString();
                }
                if (deletedDateTextField.getValue() != null) {
                    deletedDate = deletedDateTextField.getValue().toString();
                } else {
                    deletedDate = LocalDate.now().toString();
                }
                ProductSystemObject product = new ProductSystemObject(
                        nameTextField.getText(), managerId, psDelete,
                        notesTextField.getText(), createdDate, deletedDate,
                        modifiedDate, deletedAuthorTextField.getText(), tableTextField.getText(), enable,
                        nameENTextField.getText(), createdAuthorId, language
                );
                boolean condition = productSystem.addProduct(product);
                if (condition) {
                    showAlert(Alert.AlertType.INFORMATION, "Notifications", "Add product successful!");
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Notifications", "Add product failure!");
                }
                showAllProducts();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.WARNING, "Notifications", "Please check your input, there are some fields that must be positive integer!");
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
                showAlert(Alert.AlertType.WARNING, "Notifications", "Product not found!");
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
                showAlert(Alert.AlertType.INFORMATION, "Notifications", "Update product success!");
                showAllProducts();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.WARNING, "Notifications", "Please check your input, there are some fields that must be positive integer!");
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
