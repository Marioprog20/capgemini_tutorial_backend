package com.ccsw.tutorial.prestamo.model;

import java.time.LocalDate;

public class PrestamoDto {

    private Long id;
    private String gameName;
    private String clientName;
    private LocalDate iniDate;
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getIniDate() {
        return iniDate;
    }

    public void setIniDate(LocalDate iniDate) {
        this.iniDate = iniDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
