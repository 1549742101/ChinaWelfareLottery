package top.glkj.happyEight.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author : xgl
 * @version : 0.0.1
 * @date :2021/8/28 1:34
addmoney: ""
addmoney2: ""
blue: "03"
blue2: ""
code: "2017125"
content: "上海1注,安徽1注,山东2注,广东1注,新疆1注,共6注。"
date: "2017-10-24(二)"
detailsLink: "/c/2017-10-24/414468.shtml"
m2add: ""
msg: ""
name: "双色球"
poolmoney: "598302894"
prizegrades：
red: "01,14,23,25,29,30"
sales: "324821104"
videoLink: ""
week: "二"
z2add: ""
 */
@Entity
@Data
@Table(name = "SSQResult")
public class SSQResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addmoney;
    private String addmoney2;
    private String blue;
    private String blue2;
    private String code;
    private String content;
    private String date;
    private String detailsLink;
    private String m2add;
    private String mag;
    private String name;
    @OneToMany(targetEntity = SSQPrizegrades.class,fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    private List<SSQPrizegrades> prizegrades;
    private Long poolmoney;
    private String red;
    private String sales;
    private String videoLink;
    private String week;
    private String z2add;
}
