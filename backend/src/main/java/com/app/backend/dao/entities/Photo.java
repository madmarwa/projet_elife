package com.app.backend.dao.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Photo {
    @Id
    private String  id;
    private byte[] file;
    private String  type;
    private boolean photoProfil;
    private Date shareDate;
    @DBRef
    private User user;

}
