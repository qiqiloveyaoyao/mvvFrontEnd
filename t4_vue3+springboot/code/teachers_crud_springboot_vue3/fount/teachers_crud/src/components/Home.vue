<template>
  <div id="home">
    <h1>欢迎进行教师信息管理</h1>
    <p>请继续选择操作</p>
  </div>
</template>

<script>
export default {
  name: "Home",
};
</script>

<style scoped>
#home {
  text-align: center;
  margin: 50px;
  padding: 20px;
  background-image: url('../assets/pictures/img.png'); /* 背景图片路径 */
  background-size: contain; /* 图片按比例缩放以适应容器 */
  background-position: center; /* 图片居中 */
  background-repeat: no-repeat; /* 防止图片重复显示 */
  color: #f8efef; /* 文本颜色设置为白色，确保在深色背景上清晰可见 */
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  height: 100vh; /* 设置高度为视口高度 */
  width: 100%; /* 设置宽度为 100% */
  display: flex;
  flex-direction: column;
  justify-content: center; /* 垂直居中内容 */
  align-items: center; /* 水平居中内容 */
  overflow: hidden; /* 防止容器内可能出现的滚动条 */
}
</style>
