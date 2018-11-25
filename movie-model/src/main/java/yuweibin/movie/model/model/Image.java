package yuweibin.movie.model.model;

import yuweibin.movie.model.constant.ImageType;
import lombok.Builder;

import javax.persistence.*;

/**
 * 图片，包括封面和缩略图
 */
@Table(name = "t_image")
@Entity
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 图片链接
     */
    @Column(columnDefinition = "text")
    private String url;

    /**
     * 图片类型
     */
    private ImageType type;

    @ManyToOne()
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
