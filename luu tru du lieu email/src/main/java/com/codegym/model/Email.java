package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="email")
public class Email {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="language")
    @Size(min=1,message="is required")
    private String language;

    @Column(name="page_size")
    @NotNull(message="is required")
    @Min(value=1,message = "greater than 1")
    @Max(value=10,message="less than 10")
    private int pageSize;

    @Column(name="filter")
    private boolean filter;

    @Column(name="signature")
    private String signature;

    public Email(){

    }

    public Email(String language, int pageSize, boolean filter, String signature) {
        this.language = language;
        this.pageSize = pageSize;
        this.filter = filter;
        this.signature = signature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean getFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", filter='" + filter + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
