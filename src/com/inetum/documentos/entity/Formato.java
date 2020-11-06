/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inetum.documentos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author esneider
 */
@Entity
@Table(name = "formato")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formato.findAll", query = "SELECT f FROM Formato f"),
    @NamedQuery(name = "Formato.findById", query = "SELECT f FROM Formato f WHERE f.id = :id"),
    @NamedQuery(name = "Formato.findByNombre", query = "SELECT f FROM Formato f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Formato.findByDescripcion", query = "SELECT f FROM Formato f WHERE f.descripcion = :descripcion"),
    @NamedQuery(name = "Formato.findByFechahoracreacion", query = "SELECT f FROM Formato f WHERE f.fechahoracreacion = :fechahoracreacion"),
    @NamedQuery(name = "Formato.findByFechahoramodificacion", query = "SELECT f FROM Formato f WHERE f.fechahoramodificacion = :fechahoramodificacion")})
@ToString
public class Formato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Getter
    private Integer id;
    
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 2147483647)
    @Column(name = "nombre", nullable = false)
    @Getter
    @Setter
    private String nombre;
    
    //@Size(max = 2147483647)
    @Column(name = "descripcion")
    @Getter
    @Setter
    private String descripcion;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formato")
    @Getter
    @Setter
    private List<Documento> documentoList;

    public Formato() {
    }

    public Formato(Integer id) {
        this.id = id;
    }

    public Formato(Integer id, String nombre, Date fechahoracreacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechahoracreacion = fechahoracreacion;
    }
    
}
