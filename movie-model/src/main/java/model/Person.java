package model;

import constant.PersonType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * 参与人，包括：导演/编剧/主演
 */
@Table(name = "t_person")
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
}
