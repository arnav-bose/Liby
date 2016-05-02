package com.example.arnavbose.liby;

/**
 * Created by Arnav on 29/04/2016.
 */
public class DataSetCurrentIssues {

    String currentIssuesTitle;
    String currentIssuesAuthor;
    String currentIssuesDueDate;

    public DataSetCurrentIssues(String currentIssuesTitle, String currentIssuesAuthor, String currentIssuesDueDate){
        this.currentIssuesTitle = currentIssuesTitle;
        this.currentIssuesAuthor = currentIssuesAuthor;
        this.currentIssuesDueDate = currentIssuesDueDate;
    }

    public String getCurrentIssuesTitle() {
        return currentIssuesTitle;
    }

    public void setCurrentIssuesTitle(String currentIssuesTitle) {
        this.currentIssuesTitle = currentIssuesTitle;
    }

    public String getCurrentIssuesAuthor() {
        return currentIssuesAuthor;
    }

    public void setCurrentIssuesAuthor(String currentIssuesAuthor) {
        this.currentIssuesAuthor = currentIssuesAuthor;
    }

    public String getCurrentIssuesDueDate() {
        return currentIssuesDueDate;
    }

    public void setCurrentIssuesDueDate(String currentIssuesDueDate) {
        this.currentIssuesDueDate = currentIssuesDueDate;
    }
}
