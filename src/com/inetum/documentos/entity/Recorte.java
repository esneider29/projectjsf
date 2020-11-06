/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inetum.documentos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author esneider
 */
@Entity
@Table(name = "recorte")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recorte.findAll", query = "SELECT r FROM Recorte r"),
    @NamedQuery(name = "Recorte.findById", query = "SELECT r FROM Recorte r WHERE r.id = :id"),
    @NamedQuery(name = "Recorte.findByRuta", query = "SELECT r FROM Recorte r WHERE r.ruta = :ruta"),
    @NamedQuery(name = "Recorte.findByFechahoracreacion", query = "SELECT r FROM Recorte r WHERE r.fechahoracreacion = :fechahoracreacion"),
    @NamedQuery(name = "Recorte.findByFechahoramodificacion", query = "SELECT r FROM Recorte r WHERE r.fechahoramodificacion = :fechahoramodificacion")})
public class Recorte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 2147483647)
    @Column(name = "ruta", nullable = false)
    @Getter
    @Setter
    private String ruta;
    
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fechahoracreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date fechahoracreacion;
    
    @Column(name = "fechahoramodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date fechahoramodificacion;
    
    @JoinColumn(name = "documento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Documento documento;

    public Recorte() {
    }

    public Recorte(Integer id) {
        this.id = id;
    }

    public Recorte(Integer id, String ruta, Date fechahoracreacion) {
        this.id = id;
        this.ruta = ruta;
        this.fechahoracreacion = fechahoracreacion;
    }   
}
