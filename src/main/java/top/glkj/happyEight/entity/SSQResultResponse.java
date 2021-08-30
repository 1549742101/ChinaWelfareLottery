package top.glkj.happyEight.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author : xgl
 * @version : 0.0.1
 * @date :2021/8/28 1:35
 * Tflag: 2
 * countNum: 138
 * message: "查询成功"
 * pageCount: 2
 */
@Entity
@Data
@Table(name = "SSQResultResponse")
public class SSQResultResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer Tflag;
    private Integer countNum;
    private String message;
    private Integer pageCount;
    @OneToMany(targetEntity = SSQResult.class,fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    private List<SSQResult> result;
}
