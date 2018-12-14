package mayfly.sys.web.form;

import lombok.Data;
import mayfly.common.validation.annotation.NotBlank;
import mayfly.common.validation.annotation.NotNull;
import mayfly.common.validation.annotation.ValueIn;

/**
 * @author meilin.huang
 * @version 1.0
 * @date 2018-11-26 9:14 AM
 */
@Data
public class ApiForm {

    private Integer groupId;

    @NotBlank
    private String uriPattern;

    @NotBlank
    private String code;

    @NotNull
    private Byte method;

    @NotBlank
    private String description;

    @ValueIn(values = {"0", "1"})
    private Byte status;
}