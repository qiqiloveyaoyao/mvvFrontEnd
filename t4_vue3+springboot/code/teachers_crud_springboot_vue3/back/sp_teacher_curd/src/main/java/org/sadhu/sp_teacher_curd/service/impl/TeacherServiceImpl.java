package org.sadhu.sp_teacher_curd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sadhu.sp_teacher_curd.entity.Teacher;
import org.sadhu.sp_teacher_curd.mapper.TeacherMapper;
import org.sadhu.sp_teacher_curd.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    // 可在这里扩展自定义的业务逻辑
}
