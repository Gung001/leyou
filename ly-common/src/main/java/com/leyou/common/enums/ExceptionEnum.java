package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Gryant
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

    PRICE_CANNOT_BE_NULL(400,"价格不能为空！"),
    SAVE_BRAND_ERROR(500,"新增品牌失败"),
    UPLOAD_IMAGE_ERROR(500,"上传图片失败"),
    INVALID_FILE_TYPE(400,"文件类型异常"),
    ;

    private int code;
    private String msg;
}
