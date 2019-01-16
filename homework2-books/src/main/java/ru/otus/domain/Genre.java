package ru.otus.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;
    private String name;
}
