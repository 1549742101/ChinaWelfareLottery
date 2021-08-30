package top.glkj.happyEight.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : xgl
 * @version : 0.0.1
 * @date :2021/8/28 1:42
 * type: 1
 * typemoney: "7793201"
 * typenum: "6"
 */
@Entity
@Data
@Table(name = "SSQPrizegrades")
public class SSQPrizegrades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer type;
    private Long typemoney;
    private Integer typenum;
}
