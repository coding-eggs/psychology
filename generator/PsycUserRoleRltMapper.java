package generator;

import com.lby.psychology.model.pojo.PsycUserRoleRlt;

public interface PsycUserRoleRltMapper {
    int deleteByPrimaryKey(Integer userRoleRltId);

    int insert(PsycUserRoleRlt record);

    int insertSelective(PsycUserRoleRlt record);

    PsycUserRoleRlt selectByPrimaryKey(Integer userRoleRltId);

    int updateByPrimaryKeySelective(PsycUserRoleRlt record);

    int updateByPrimaryKey(PsycUserRoleRlt record);
}