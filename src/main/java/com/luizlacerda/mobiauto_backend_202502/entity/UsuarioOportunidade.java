package com.luizlacerda.mobiauto_backend_202502.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuario_oportunidade")
@NoArgsConstructor
@Data
public class    UsuarioOportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name = "oportunidades_em_andamento")
    private int oportunidadesEmAndamento;

    @Column(name = "oportunidades_concluidas")
    private int oportunidadesConcluidas;
    @Column(name = "ultima_oportunidade")
    private LocalDateTime ultimaOportunidade;

    @OneToOne
    @JoinColumn(name = "assistente")
    private User assistente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "revenda")
    private Revenda revenda;


    public UsuarioOportunidade(int oportunidadesEmAndamento, int oportunidadesConcluidas, User assistente) {
        this.oportunidadesEmAndamento = oportunidadesEmAndamento;
        this.oportunidadesConcluidas = oportunidadesConcluidas;
        this.assistente = assistente;
    }
}
