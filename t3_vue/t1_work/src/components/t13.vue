<template>
  <div>
    <h1>动态列表管理</h1>
    <ul>
      <li v-for="(item, index) in items" :key="index">
        {{ item.text }}
        <button @click="editItem(index)">编辑</button>
        <button @click="deleteItem(index)">删除</button>
      </li>
    </ul>
    <div class="input-content">
      <input type="text" v-model="newItemText" placeholder="新项" />
      <button @click="addItem">添加项</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const items = ref([]);
const newItemText = ref('');

function addItem() {
  if (newItemText.value.trim()) {
    items.value.push({ text: newItemText.value.trim() });
    newItemText.value = '';
  } else {
    alert('请输入列表项内容');
  }
}

function deleteItem(index) {
  items.value.splice(index, 1);
}

function editItem(index) {
  const currentItem = items.value[index];
  const editText = prompt('编辑内容', currentItem.text);
  if (editText !== null) {
    items.value[index].text = editText;
  }
}
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 20px;
}

h1 {
  text-align: center;
  color: #333;
}

ul {
  list-style-type: none;
  padding: 0;
  max-width: 600px;
  margin: 20px auto;
  background: white;
  border-radius: 5px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

li {
  padding: 15px;
  border-bottom: 1px solid #ddd;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

li:last-child {
  border-bottom: none;
}

button {
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 8px 12px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}

input[type="text"] {
  padding: 10px;
  width: 500px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-right: 10px;
}

.input-content {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>