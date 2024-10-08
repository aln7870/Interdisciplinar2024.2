package com.ti.interdisciplinar242.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
@Entity
@Table(name = "Faturamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codFaturamento;
    @Column(nullable = false)
    private BigDecimal valor;
    //chave estrangeira da tabelo Atendimento
    @Column
    private Integer fk_codAtendimento;
    //chave estrangeira da tabla ProMed
    @Column
    private Integer fk_codProMed;
}