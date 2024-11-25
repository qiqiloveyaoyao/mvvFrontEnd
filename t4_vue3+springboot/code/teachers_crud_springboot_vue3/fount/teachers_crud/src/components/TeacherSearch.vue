<template>
  <div id="search-section">
    <h2>通过 ID 查询教师信息</h2>
    <input
        type="number"
        v-model="searchId"
        placeholder="输入教师 ID"
    />
    <button @click="fetchTeacherById">查询</button>

    <div v-if="teacher">
      <h3>教师信息</h3>
      <ul>
        <li><strong>ID:</strong> {{ teacher.id }}</li>
        <li>
          <strong>姓名:</strong>
          <input
              type="text"
              v-model="teacher.name"
              placeholder="修改姓名"
          />
        </li>
        <li>
          <strong>课程:</strong>
          <input
              type="text"
              v-model="teacher.course"
              placeholder="修改课程"
          />
        </li>
        <li>
          <strong>生日:</strong>
          <input
              type="date"
              v-model="teacher.birthday"
          />
        </li>
      </ul>
      <button @click="updateTeacher">保存修改</button>
      <button @click="deleteTeacher" class="delete">删除教师</button>
    </div>

    <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TeacherSearch",
  data() {
    return {
      searchId: "", // 输入的教师 ID
      teacher: null, // 查询结果
      errorMessage: "", // 错误信息
    };
  },
  methods: {
    // 查询教师信息
    async fetchTeacherById() {
      if (!this.searchId) {
        this.errorMessage = "请输入教师 ID";
        this.teacher = null;
        return;
      }

      try {
        const response = await axios.get(
            `http://localhost:8080/api/teachers/${this.searchId}`
        );
        this.teacher = response.data;
        this.errorMessage = ""; // 清空错误信息
      } catch (error) {
        this.teacher = null;
        this.errorMessage = "未找到对应的教师信息或请求出错";
        console.error("Error fetching teacher by ID:", error);
      }
    },

    // 修改教师信息
    async updateTeacher() {
      if (!this.teacher) {
        this.errorMessage = "没有可修改的教师信息";
        return;
      }

      try {
        await axios.put(
            `http://localhost:8080/api/teachers/${this.teacher.id}`,
            this.teacher
        );
        this.errorMessage = "教师信息已成功修改";
      } catch (error) {
        this.errorMessage = "修改教师信息失败";
        console.error("Error updating teacher:", error);
      }
    },

    // 删除教师信息
    async deleteTeacher() {
      if (!this.teacher) {
        this.errorMessage = "没有可删除的教师信息";
        return;
      }

      const confirmDelete = confirm(
          `确定要删除教师 ${this.teacher.name} 的信息吗？`
      );

      if (confirmDelete) {
        try {
          await axios.delete(
              `http://localhost:8080/api/teachers/${this.teacher.id}`
          );
          this.errorMessage = "教师信息已删除";
          this.teacher = null;
        } catch (error) {
          this.errorMessage = "删除教师信息失败";
          console.error("Error deleting teacher:", error);
        }
      }
    },
  },
};
</script>

<style scoped>
/* 样式与之前类似，新增删除按钮的样式 */
#search-section {
  margin: 20px auto;
  max-width: 500px;
  text-align: left;
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

#search-section h2 {
  font-size: 1.5rem;
  color: #333;
}

#search-section input {
  width: calc(100% - 100px);
  padding: 10px;
  margin-right: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

#search-section button {
  padding: 10px 15px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 5px;
}

#search-section button:hover {
  background-color: #45a049;
}

#search-section button.delete {
  background-color: #e74c3c;
}

#search-section button.delete:hover {
  background-color: #c0392b;
}

.error {
  color: red;
  font-weight: bold;
  margin-top: 10px;
}
</style>
