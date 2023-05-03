package com.shanghaishark.recipebuddy.models;

public class SearchModel {
    String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public SearchModel(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public SearchModel() {
        searchTerm = "";
    }

    @Override
    public String toString() {
        return "SearchModel [searchTerm=" + searchTerm + "]";
    }
    
    
}
