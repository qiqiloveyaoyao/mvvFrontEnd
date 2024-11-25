<template>
  <div id="add-teacher-section">
    <h2>添加教师信息</h2>
    <form @submit.prevent="addTeacher">
      <div class="form-group">
        <label for="id">教师 ID（可选）</label>
        <input
            type="number"
            id="id"
            v-model="teacher.id"
            placeholder="输入教师 ID，留空则自动生成"
        />
      </div>
      <div class="form-group">
        <label for="name">姓名</label>
        <input
            type="text"
            id="name"
            v-model="teacher.name"
            placeholder="请输入教师姓名"
            required
        />
      </div>
      <div class="form-group">
        <label for="course">课程</label>
        <input
            type="text"
            id="course"
            v-model="teacher.course"
            placeholder="请输入教授课程"
            required
        />
      </div>
      <div class="form-group">
        <label for="birthday">生日</label>
        <input
            type="date"
            id="birthday"
            v-model="teacher.birthday"
            required
        />
      </div>
      <button type="submit">添加教师</button>
    </form>
    <div v-if="successMessage" class="success">{{ successMessage }}</div>
    <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TeacherAdd",
  data() {
    return {
      teacher: {
        id: null,
        name: "",
        course: "",
        birthday: "",
      },
      successMessage: "", // 成功提示信息
      errorMessage: "", // 错误提示信息
    };
  },
  methods: {
    async addTeacher() {
      try {
        await axios.post("http://localhost:8080/api/teachers", this.teacher);
        this.successMessage = "教师信息添加成功！";
        this.errorMessage = "";

        // 清空表单
        this.teacher = {
          id: null,
          name: "",
          course: "",
          birthday: "",
        };
      } catch (error) {
        this.successMessage = "";
        this.errorMessage = "添加教师信息失败，请检查输入或稍后重试。";
        console.error("Error adding teacher:", error);
      }
    },
  },
};
</script>

<style scoped>
/* 美化样式 */
#add-teacher-section {
  margin: 20px auto;
  max-width: 500px;
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

#add-teacher-section h2 {
  font-size: 1.8rem;
  color: #333;
  margin-bottom: 20px;
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
  color: #555;
}

.form-group input {
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px 15px;
  font-size: 1rem;
  color: white;
  background-color: #4caf50;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background-color: #45a049;
}

.success {
  color: #2e7d32;
  font-weight: bold;
  margin-top: 15px;
}

.error {
  color: #c62828;
  font-weight: bold;
  margin-top: 15px;
}
</style>
