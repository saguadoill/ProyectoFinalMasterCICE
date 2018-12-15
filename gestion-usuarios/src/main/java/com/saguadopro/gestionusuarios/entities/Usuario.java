package com.saguadopro.gestionusuarios.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Long idUsuario;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "passwd")
    private String passwd;

//    @Column(name = "perfil")
//    private String perfil;

    @OneToOne
    @JoinColumn(name = "perfil")
    private Perfil perfil;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "cambio_passwd")
    private Boolean cambioPasswd;

    @Column(name = "foto_url")
    private String foto_url;
}
//TODO: Hacer otro Entity de Perfiles(igual que Apartamentos con tipos)