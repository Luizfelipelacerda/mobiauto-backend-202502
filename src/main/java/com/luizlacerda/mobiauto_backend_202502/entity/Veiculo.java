package com.luizlacerda.mobiauto_backend_202502.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "veiculo_id", columnDefinition = "CHAR(36)")
    private UUID veiculoId;
    private String marca;
    private String modelo;
    private String versao;
    private String ano;

    public Veiculo(String marca, String modelo, String versao, String ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.versao = versao;
        this.ano = ano;
    }
}
