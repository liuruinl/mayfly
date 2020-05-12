package mayfly.core.exception;

import mayfly.core.base.model.ResultCodeEnum;
import mayfly.core.util.enums.NameValueEnum;

/**
 * 业务逻辑异常
 *
 * @author meilin.huang
 * @version 1.0
 * @date 2018-12-07 3:10 PM
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 7448623878598565912L;

    /**
     * 异常码
     */
    private final Integer errorCode;

    /**
     * 默认错误code为 {@linkplain ResultCodeEnum#FAILURE}
     *
     * @param msg 错误消息
     */
    public BusinessException(String msg) {
        super(msg);
        this.errorCode = ResultCodeEnum.FAILURE.getValue();
    }

    /**
     * @param errorEnum 错误枚举值
     * @param <E>       异常枚举类型
     */
    public <E extends Enum<?> & NameValueEnum<Integer>> BusinessException(E errorEnum) {
        super(errorEnum.getName());
        this.errorCode =errorEnum.getValue();
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
