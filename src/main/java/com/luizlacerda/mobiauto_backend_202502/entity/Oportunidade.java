package com.luizlacerda.mobiauto_backend_202502.entity;

import com.luizlacerda.mobiauto_backend_202502.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "oportunidades")
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "oportunidade_id", columnDefinition = "CHAR(36)")
    private UUID oportunidadeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Column(name = "motivo_conclusao")
    private String motivoConclusao;

    @OneToOne
    @JoinColumn(name = "revenda_id")
    private Revenda revenda;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User usuarioAssociado;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    @Column(name = "data_insersao")
    private LocalDateTime dataInsersao;
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    public Oportunidade(Status status, Revenda revenda, User usuarioAssociado, Cliente cliente, Veiculo veiculo) {
        this.status = status;
        this.revenda = revenda;
        this.usuarioAssociado = usuarioAssociado;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }



}
