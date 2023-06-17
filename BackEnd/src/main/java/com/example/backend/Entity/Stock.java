package com.example.backend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "towar")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_towaru")
    private Integer id;

    @Column(name = "nazwa_towaru", length = 128)
    private String stockName;

    @Column(name = "ilosc_towaru")
    private Integer stockAvailable;

    @Column(name = "opis_towaru", length = 512)
    private String stockDescription;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private StockCategory category;
}
