package com.app.backend.dao.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;


@Document(collection = "users")
@TypeAlias("admin")
@ToString
public class Admin extends User {

}
