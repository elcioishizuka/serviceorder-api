package com.github.elcioishizuka.serviceorderapi.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // This annotation removes the null field from the response message.
public class Issue {

    private Integer status;
    private LocalDateTime dateTime;
    private String title;
    private List<ProblematicField> problematicFields;

    public static class ProblematicField {
        private String name;
        private String message;

        public ProblematicField(String name, String message) {
            super();
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProblematicField> getProblematicFields() {
        return problematicFields;
    }

    public void setProblematicFields(List<ProblematicField> problematicFields) {
        this.problematicFields = problematicFields;
    }
}
