package com.example.backend.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "kategoria_towaru")
public class StockCategory {
    @Id
    @Column(name = "id_kategorii")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nazwa_kategorii", length = 64, unique = true)
    private String categoryname;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Stock> stockInCategory;

}
