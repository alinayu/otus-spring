package ru.otus.domain;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;
    private String firstName;
    private String lastName;
}
