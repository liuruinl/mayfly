package mayfly.sys.module.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import mayfly.core.base.mapper.annotation.NoColumn;
import mayfly.core.base.mapper.annotation.Table;
import mayfly.core.model.BaseDO;

/**
 *  权限
 *
 * @author  hml
 * @date 2018/6/27 下午2:35
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table("tb_account_role")
@NoColumn(fields = {BaseDO.UPDATE_TIME, BaseDO.MODIFIER, BaseDO.MODIFIER_ID})
public class AccountRoleDO extends BaseDO {
    private Long accountId;

    private Long roleId;
}
