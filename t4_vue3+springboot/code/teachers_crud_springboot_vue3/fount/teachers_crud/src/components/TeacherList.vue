<template>
  <div id="teacher-list-section">
    <h2>教师列表</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>课程</th>
        <th>生日</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="teacher in paginatedTeachers" :key="teacher.id">
        <td>{{ teacher.id }}</td>
        <td>
          <input
              type="text"
              v-model="teacher.name"
              :readonly="!teacher.editing"
              :class="{ editable: teacher.editing }"
          />
        </td>
        <td>
          <input
              type="text"
              v-model="teacher.course"
              :readonly="!teacher.editing"
              :class="{ editable: teacher.editing }"
          />
        </td>
        <td>
          <input
              type="date"
              v-model="teacher.birthday"
              :readonly="!teacher.editing"
              :class="{ editable: teacher.editing }"
          />
        </td>
        <td>
          <button v-if="!teacher.editing" @click="editTeacher(teacher)">编辑</button>
          <button v-if="teacher.editing" @click="saveTeacher(teacher)">保存</button>
          <button @click="deleteTeacher(teacher.id)" class="delete">删除</button>
        </td>
      </tr>
      </tbody>
    </table>
    <!-- 分页控制 -->
    <div id="pagination">
      <button :disabled="currentPage === 1" @click="previousPage">上一页</button>
      <span>第 {{ currentPage }} 页 / 共 {{ totalPages }} 页</span>
      <button :disabled="currentPage === totalPages" @click="nextPage">下一页</button>
    </div>
    <div id="jump-to-page">
      <label for="jumpPage">跳转到页码:</label>
      <input
          id="jumpPage"
          type="number"
          v-model.number="jumpPageInput"
          placeholder="输入页码"
          min="1"
          :max="totalPages"
      />
      <button @click="jumpToPage">跳转</button>
    </div>
    <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
    <!-- 回到首页按钮 -->
    <div class="back-to-home">
      <router-link to="/">
        <button>回到首页</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TeacherList",
  data() {
    return {
      teachers: [], // 教师列表数据
      currentPage: 1, // 当前页码
      itemsPerPage: 15, // 每页显示的记录数
      jumpPageInput: "", // 输入的页码
      errorMessage: "", // 错误信息
    };
  },
  computed: {
    // 计算当前页的教师数据
    paginatedTeachers() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = this.currentPage * this.itemsPerPage;
      return this.teachers.slice(start, end);
    },
    // 总页数
    totalPages() {
      return Math.ceil(this.teachers.length / this.itemsPerPage);
    },
  },
  async mounted() {
    // 加载所有教师信息
    await this.fetchTeachers();
  },
  methods: {
    // 获取所有教师数据
    async fetchTeachers() {
      try {
        const response = await axios.get("http://localhost:8080/api/teachers");
        this.teachers = response.data.map((teacher) => ({
          ...teacher,
          editing: false, // 新增编辑状态
        }));
      } catch (error) {
        this.errorMessage = "无法加载教师数据，请稍后重试。";
        console.error("Error fetching teachers:", error);
      }
    },

    // 进入编辑模式
    editTeacher(teacher) {
      teacher.editing = true;
    },

    // 保存教师信息
    async saveTeacher(teacher) {
      try {
        await axios.put(
            `http://localhost:8080/api/teachers/${teacher.id}`,
            teacher
        );
        teacher.editing = false; // 退出编辑模式
        this.errorMessage = ""; // 清空错误信息
      } catch (error) {
        this.errorMessage = "更新教师信息失败，请稍后重试。";
        console.error("Error saving teacher:", error);
      }
    },

    // 删除教师
    async deleteTeacher(id) {
      const confirmDelete = confirm("确定要删除该教师的信息吗？");
      if (!confirmDelete) return;

      try {
        await axios.delete(`http://localhost:8080/api/teachers/${id}`);
        this.teachers = this.teachers.filter((teacher) => teacher.id !== id);
        this.errorMessage = ""; // 清空错误信息
      } catch (error) {
        this.errorMessage = "删除教师信息失败，请稍后重试。";
        console.error("Error deleting teacher:", error);
      }
    },

    // 上一页
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },

    // 下一页
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },

    // 跳转到指定页码
    jumpToPage() {
      if (
          this.jumpPageInput >= 1 &&
          this.jumpPageInput <= this.totalPages &&
          Number.isInteger(this.jumpPageInput)
      ) {
        this.currentPage = this.jumpPageInput;
        this.errorMessage = ""; // 清空错误信息
      } else {
        this.errorMessage = "请输入有效的页码！";
      }
    },
  },
};
</script>

<style scoped>
/* 容器样式 */
#teacher-list-section {
  margin: 20px auto;
  max-width: 800px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 标题样式 */
#teacher-list-section h2 {
  text-align: center;
  font-size: 1.8rem;
  margin-bottom: 20px;
  color: #333;
}

/* 表格样式 */
table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

table th,
table td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: center;
}

table th {
  background-color: #4caf50;
  color: white;
  font-weight: bold;
}

table tr:nth-child(even) {
  background-color: #f2f2f2;
}

table tr:hover {
  background-color: #ddd;
}

/* 按钮样式 */
button {
  padding: 5px 10px;
  font-size: 0.9rem;
  color: #1a1818;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  opacity: 0.8;
}

button.delete {
  background-color: #e74c3c;
}

button.delete:hover {
  background-color: #c0392b;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

/* 分页样式 */
#pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px 0;
}

#pagination button {
  background-color: #4caf50;
  margin: 0 10px;
}

#pagination span {
  font-size: 1rem;
  color: #333;
}

/* 跳转页码样式 */
#jump-to-page {
  text-align: center;
  margin-top: 10px;
}

#jump-to-page input {
  width: 50px;
  padding: 5px;
  text-align: center;
  margin-left: 5px;
  margin-right: 5px;
}

#jump-to-page button {
  background-color: #4caf50;
}

/* 返回首页按钮样式 */
.back-to-home {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.back-to-home button {
  background-color: #4caf50;
  padding: 10px 20px;
  font-size: 1rem;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.back-to-home button:hover {
  background-color: #45a049;
}

/* 错误信息样式 */
.error {
  color: red;
  font-weight: bold;
  text-align: center;
}
</style>
