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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "documento")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findById", query = "SELECT d FROM Documento d WHERE d.id = :id"),
    @NamedQuery(name = "Documento.findByRuta", query = "SELECT d FROM Documento d WHERE d.ruta = :ruta"),
    @NamedQuery(name = "Documento.findByFechahoracreacion", query = "SELECT d FROM Documento d WHERE d.fechahoracreacion = :fechahoracreacion"),
    @NamedQuery(name = "Documento.findByFechahoramodificacion", query = "SELECT d FROM Documento d WHERE d.fechahoramodificacion = :fechahoramodificacion")})
@ToString
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 2147483647)
    @Column(name = "ruta", nullable = false)
    @Getter
    @Setter
    private String ruta;
    
    @Basic(optional = false)
//    @NotNull
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documento")
    @Getter
    @Setter
    private List<Recorte> recorteList;
    
    @JoinColumn(name = "formato", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Formato formato;

    public Documento() {
    }

    public Documento(Integer id) {
        this.id = id;
    }

    public Documento(Integer id, String ruta, Date fechahoracreacion) {
        this.id = id;
        this.ruta = ruta;
        this.fechahoracreacion = fechahoracreacion;
    }




    
    
}
