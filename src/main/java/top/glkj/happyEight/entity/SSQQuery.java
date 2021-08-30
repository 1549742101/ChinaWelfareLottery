package top.glkj.happyEight.entity;

import lombok.Data;
import top.glkj.utils.RequestParam;

import java.util.Date;

/**
 * @author : xgl
 * @version : 0.0.1
 * @date :2021/8/28 1:29
 * name: ssq
 * issueCount:
 * issueStart:
 * issueEnd:
 * dayStart: 2016-12-01
 * dayEnd: 2017-10-24
 * pageNo:
 */
@Data
public class SSQQuery {
    @RequestParam
    private String name;
    @RequestParam
    private Integer issueCount;
    @RequestParam
    private Integer issueStart;
    @RequestParam
    private Integer issueEnd;
    @RequestParam
    private Date dayStart;
    @RequestParam
    private Date dayEnd;
    @RequestParam
    private Integer pageNo;
}
