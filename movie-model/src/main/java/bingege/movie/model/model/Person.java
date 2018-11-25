package bingege.movie.model.model;

import bingege.movie.model.constant.PersonType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * 参与人，包括：导演/编剧/主演
 */
@Table(name = "t_person")
@Entity
@Data
@Builder
public class Person extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String englishName;

    private String chineseName;

    @Enumerated(EnumType.ORDINAL)
    private PersonType type;

    @ManyToMany
    private Set<Movie> movies;
}
