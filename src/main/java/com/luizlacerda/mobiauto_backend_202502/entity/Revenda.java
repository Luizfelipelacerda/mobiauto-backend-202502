package com.luizlacerda.mobiauto_backend_202502.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "revenda")
public class    Revenda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "revenda_id", columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;
    @Column(nullable = false, unique = true)
    private String nomeSocial;


}
