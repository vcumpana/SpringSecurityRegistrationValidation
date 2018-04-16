//package com.springapp.mvc.model;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "persistent_logins")
//public class PersistentLogin {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String series;
//
//    @Column(nullable = false)
//    private String username;
//
//
//    @Column(nullable = false)
//    private String token;
//
//    @Column(nullable = false)
//    private Timestamp last_used;
//}
