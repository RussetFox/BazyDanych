package com.example.backend.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "zamowienia")
public class Order {
    @Id
    @Column(name = "id_zamowienia")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_zamówienia")
    private LocalDateTime dateOfOrder;

    @Column(name = "lista_towarów")
    @ManyToMany (fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Stock> orderedItems;

    @OneToOne
    @JoinColumn(name = "id_zamawiajacego")
    @JsonManagedReference
    private User orderingUser;
}
