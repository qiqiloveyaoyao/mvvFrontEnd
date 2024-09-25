<template>
  <div>
    <h1>{{ title }}</h1>
    <button @click="changeTitle">改变标题</button>
    <div>当前时间: {{ currentTime }}</div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const title = ref('初始标题');
const currentTime = ref('');

function changeTitle() {
  title.value = '欢迎来到我的网站';
  currentTime.value = new Date().toLocaleString(); // 获取当前时间
}
</script>

<style scoped>
/* 你可以在这里添加一些CSS样式 */
</style>