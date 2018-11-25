package bingege.movie.model.model;

import bingege.movie.model.constant.Category;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * 电影
 */
@Table(name = "t_movie")
@Entity
@Builder
public class Movie extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类
     */
    @Enumerated(EnumType.ORDINAL)
    private Category type;

    /**
     * 中译
     */
    private String chinaName;

    /**
     * 英译
     */
    private String englishName;

    /**
     * 年代
     */
    private String age;

    /**
     * 产地
     */
    private String originPlace;

    /**
     * 语言
     */
    private String language;

    /**
     * 字幕
     */
    private String caption;

    /**
     * 上映时间
     */
    private LocalDate releaseDate;

    /**
     * 评分
     */
    @OneToMany(mappedBy = "movie")
    private Set<Grade> grades;

    /**
     * 片长
     */
    private Integer mins;

    /**
     * 关联人，包括导演/主演/编剧
     */
    @ManyToMany
    @JoinTable(name = "t_movie_person")
    private Set<Person> peoples;

    /**
     * 简介
     */
    @Column(columnDefinition = "text")
    private String introduction;

    /**
     * 封面或缩略图
     */
    @OneToMany(mappedBy = "movie")
    private Set<Image> images;

    /**
     * 下载链接
     */
    @OneToMany(mappedBy = "movie")
    private Set<Link> links;
}