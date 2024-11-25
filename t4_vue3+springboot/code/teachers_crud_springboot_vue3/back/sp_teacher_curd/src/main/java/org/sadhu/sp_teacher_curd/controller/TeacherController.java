package org.sadhu.sp_teacher_curd.controller;

import org.sadhu.sp_teacher_curd.entity.Teacher;
import org.sadhu.sp_teacher_curd.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin // 如果需要跨域支持
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // 查询所有教师
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.list();
    }

    // 根据ID查询教师
    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Integer id) {
        return teacherService.getById(id);
    }

    // 创建新教师
    @PostMapping
    public boolean createTeacher(@RequestBody Teacher teacher) {
        return teacherService.save(teacher);
    }

    // 更新教师信息
    @PutMapping("/{id}")
    public boolean updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        return teacherService.updateById(teacher);
    }

    // 删除教师信息
    @DeleteMapping("/{id}")
    public boolean deleteTeacher(@PathVariable Integer id) {
        return teacherService.removeById(id);
    }
}
