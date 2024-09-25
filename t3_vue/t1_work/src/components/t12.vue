<template>
  <div>
    <h1>表格数据排序</h1>
    <table>
      <thead>
      <tr>
        <th>姓名</th>
        <th>年龄</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(person, index) in people" :key="index">
        <td>
          <span v-if="!person.isEditingName" @dblclick="startEditName(index)">{{ person.name }}</span>
          <input v-else type="text" v-model="person.tempName" @blur="endEditName(index)" @keyup.enter="endEditName(index)" />
        </td>
        <td>
          <span v-if="!person.isEditingAge" @dblclick="startEditAge(index)">{{ person.age }}</span>
          <input v-else type="number" v-model="person.tempAge" @blur="endEditAge(index)" @keyup.enter="endEditAge(index)" />
        </td>
        <td>
          <button @click="deletePerson(index)">删除</button>
        </td>
      </tr>
      </tbody>
    </table>
    <input type="text" v-model="newName" placeholder="姓名" />
    <input type="number" v-model="newAge" placeholder="年龄" />
    <button @click="addPerson" class="add-button">添加数据</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const people = ref([]);
const newName = ref('');
const newAge = ref(0);

// 添加人员
function addPerson() {
  if (newName.value && !isNaN(newAge.value) && newAge.value >= 0) {
    people.value.push({
      name: newName.value,
      age: newAge.value,
      isEditingName: false,
      isEditingAge: false,
      tempName: '',
      tempAge: 0
    });
    newName.value = '';
    newAge.value = 0;
    sortTableByAge();
  } else {
    alert('请输入有效的姓名和年龄');
  }
}

// 删除人员
function deletePerson(index) {
  people.value.splice(index, 1);
  sortTableByAge();
}

// 开始编辑姓名
function startEditName(index) {
  const person = people.value[index];
  person.isEditingName = true;
  person.tempName = person.name;
}

// 结束编辑姓名
function endEditName(index) {
  const person = people.value[index];
  person.isEditingName = false;
  person.name = person.tempName;
  sortTableByAge();
}

// 开始编辑年龄
function startEditAge(index) {
  const person = people.value[index];
  person.isEditingAge = true;
  person.tempAge = person.age;

}

// 结束编辑年龄
function endEditAge(index) {
  const person = people.value[index];
  person.isEditingAge = false;
  person.age = person.tempAge;
  sortTableByAge();
}

// 排序表格
function sortTableByAge() {
  people.value.sort((a, b) => a.age - b.age);
}
</script>

<style scoped>
table {
  width: 50%;
  margin-top: 20px;
  border: 1px solid #ddd;
}

th {
  background-color: #dfeefd;
}

th, td {
  padding: 8px;
  text-align: left;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

.add-button {
  margin-top: 20px;
}
</style>