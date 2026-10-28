package tn.esprit.autoloc.autolocapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutReservation statut;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    @JsonIgnoreProperties({"reservations", "maintenances", "equipements", "agence"})
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("reservations")
    private Client client;

    @OneToOne
    @JoinColumn(name = "contrat_id")
    @JsonIgnoreProperties({"reservation", "paiements"})
    private Contrat contrat;
}
