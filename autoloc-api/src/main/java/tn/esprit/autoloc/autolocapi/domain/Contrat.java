package tn.esprit.autoloc.autolocapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    @Column(nullable = false)
    private LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(nullable = false)
    private Boolean valide;

    @OneToMany(mappedBy = "contrat", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("contrat")
    private List<Paiement> paiements = new ArrayList<>();

    @OneToOne(mappedBy = "contrat")
    @JsonIgnoreProperties({"contrat", "vehicule", "client"})
    private Reservation reservation;
}
