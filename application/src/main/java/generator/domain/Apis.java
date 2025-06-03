package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName apis
 */
@TableName(value ="apis")
@Data
public class Apis {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Long categoryId;

    /**
     * 
     */
    private String version;

    /**
     * 
     */
    private String basePath;

    /**
     * 
     */
    private String documentationUrl;

    /**
     * 0-收费, 1-免费
     */
    private Integer isFree;

    /**
     * 0-draft, 1-published, 2-deprecated
     */
    private Integer status;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;
}