package model;

import constant.LinkType;
import lombok.Builder;

import javax.persistence.*;

/**
 * 下载链接实体
 */
@Table(name = "t_link")
@Entity
@Builder
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 下载链接
     */
    private String url;

    /**
     * 链接类型
     */
    private LinkType type;

    /**
     * 如果是百度云，提取码
     */
    private String code;

    @ManyToOne()
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
