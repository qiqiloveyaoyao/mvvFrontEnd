package org.sadhu.sp_teacher_curd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sadhu.sp_teacher_curd.entity.Teacher;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    // 自定义查询方法可以在这里添加
}
