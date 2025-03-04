package com.ccsw.tutorial.prestamo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "game_name", nullable = false)
    private String gameName;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "ini_date", nullable = false)
    private LocalDate iniDate;

    @Column(name = "end_date", nullable = false)
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