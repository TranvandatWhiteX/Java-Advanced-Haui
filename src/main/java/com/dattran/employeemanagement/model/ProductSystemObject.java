package com.dattran.employeemanagement.model;

public class ProductSystemObject {
    private int productSystem_id;
    private String productSystem_name;
    private int productSystem_manager_id;
    private int productSystem_delete;
    private String productSystem_notes;
    private String productSystem_created_date;
    private String productSystem_deleted_date;
    private String productSystem_modified_date;
    private String productSystem_deleted_author;
    private String productSystem_table;
    private int productSystem_enable;
    private String productSystem_name_en;
    private int productSystem_created_author_id;
    private int productSystem_language;

    public ProductSystemObject() {
    }

    public ProductSystemObject(int productSystem_id, String productSystem_name, int productSystem_manager_id,
                               int productSystem_delete, String productSystem_notes, String productSystem_created_date,
                               String productSystem_deleted_date, String productSystem_modified_date, String productSystem_deleted_author,
                               String productSystem_table, int productSystem_enable, String productSystem_name_en,
                               int productSystem_created_author_id, int productSystem_language) {
        this.productSystem_id = productSystem_id;
        this.productSystem_name = productSystem_name;
        this.productSystem_manager_id = productSystem_manager_id;
        this.productSystem_delete = productSystem_delete;
        this.productSystem_notes = productSystem_notes;
        this.productSystem_created_date = productSystem_created_date;
        this.productSystem_deleted_date = productSystem_deleted_date;
        this.productSystem_modified_date = productSystem_modified_date;
        this.productSystem_deleted_author = productSystem_deleted_author;
        this.productSystem_table = productSystem_table;
        this.productSystem_enable = productSystem_enable;
        this.productSystem_name_en = productSystem_name_en;
        this.productSystem_created_author_id = productSystem_created_author_id;
        this.productSystem_language = productSystem_language;
    }

    public int getProductSystem_delete() {
        return productSystem_delete;
    }

    public void setProductSystem_delete(int productSystem_delete) {
        this.productSystem_delete = productSystem_delete;
    }

    public int getProductSystem_id() {
        return productSystem_id;
    }

    public void setProductSystem_id(int productSystem_id) {
        this.productSystem_id = productSystem_id;
    }

    public String getProductSystem_name() {
        return productSystem_name;
    }

    public void setProductSystem_name(String productSystem_name) {
        this.productSystem_name = productSystem_name;
    }

    public int getProductSystem_manager_id() {
        return productSystem_manager_id;
    }

    public void setProductSystem_manager_id(int productSystem_manager_id) {
        this.productSystem_manager_id = productSystem_manager_id;
    }

    public String getProductSystem_notes() {
        return productSystem_notes;
    }

    public void setProductSystem_notes(String productSystem_notes) {
        this.productSystem_notes = productSystem_notes;
    }

    public String getProductSystem_created_date() {
        return productSystem_created_date;
    }

    public void setProductSystem_created_date(String productSystem_created_date) {
        this.productSystem_created_date = productSystem_created_date;
    }

    public String getProductSystem_deleted_date() {
        return productSystem_deleted_date;
    }

    public void setProductSystem_deleted_date(String productSystem_deleted_date) {
        this.productSystem_deleted_date = productSystem_deleted_date;
    }

    public String getProductSystem_modified_date() {
        return productSystem_modified_date;
    }

    public void setProductSystem_modified_date(String productSystem_modified_date) {
        this.productSystem_modified_date = productSystem_modified_date;
    }

    public String getProductSystem_deleted_author() {
        return productSystem_deleted_author;
    }

    public void setProductSystem_deleted_author(String productSystem_deleted_author) {
        this.productSystem_deleted_author = productSystem_deleted_author;
    }

    public String getProductSystem_table() {
        return productSystem_table;
    }

    public void setProductSystem_table(String productSystem_table) {
        this.productSystem_table = productSystem_table;
    }

    public int getProductSystem_enable() {
        return productSystem_enable;
    }

    public void setProductSystem_enable(int productSystem_enable) {
        this.productSystem_enable = productSystem_enable;
    }

    public String getProductSystem_name_en() {
        return productSystem_name_en;
    }

    public void setProductSystem_name_en(String productSystem_name_en) {
        this.productSystem_name_en = productSystem_name_en;
    }

    public int getProductSystem_created_author_id() {
        return productSystem_created_author_id;
    }

    public void setProductSystem_created_author_id(int productSystem_created_author_id) {
        this.productSystem_created_author_id = productSystem_created_author_id;
    }

    public int getProductSystem_language() {
        return productSystem_language;
    }

    public void setProductSystem_language(int productSystem_language) {
        this.productSystem_language = productSystem_language;
    }
}
