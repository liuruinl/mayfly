package mayfly.sys.web.machine.form;

import lombok.Data;
import mayfly.core.validation.annotation.NotBlank;

/**
 * @author meilin.huang
 * @version 1.0
 * @date 2019-11-05 4:39 下午
 */
@Data
public class MachineConfContentForm {
    /**
     * 文件路径
     */
    @NotBlank
    private String path;

    @NotBlank
    private String content;
}
