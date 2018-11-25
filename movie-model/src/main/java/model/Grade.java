package model;

import constant.GradeType;
import lombok.Builder;

import javax.persistence.*;

@Table(name = "t_grade")
@Entity
@Builder
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 得分
     */
    private Double value;

    /**
     * 总分
     */
    private Double total;

    /**
     * 评分类型：imdb/豆瓣
     */
    @Enumerated(EnumType.ORDINAL)
    private GradeType type;
}
