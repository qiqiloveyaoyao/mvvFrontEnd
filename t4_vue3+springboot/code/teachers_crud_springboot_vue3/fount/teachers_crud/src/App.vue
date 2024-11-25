<template>
  <div id="app">
    <header>
      <h1>教师信息管理</h1>
      <nav>
        <ul>
          <li><router-link to="/">首页</router-link></li>
          <li><router-link to="/search">查询教师信息</router-link></li>
          <li><router-link to="/list">教师列表</router-link></li>
          <li><router-link to="/add">添加教师</router-link></li>
        </ul>
      </nav>
    </header>
    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style scoped>
#app {
  font-family: Arial, sans-serif;
  text-align: center;
  margin: 0;
  padding: 0;
}

header {
  background-color: #4caf50;
  color: white;
  padding: 10px 0;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

header h1 {
  margin: 0;
  font-size: 2rem;
}

nav ul {
  list-style: none;
  padding: 0;
  display: flex;
  justify-content: center;
  margin: 10px 0 0;
}

nav li {
  margin: 0 15px;
}

nav a {
  color: white;
  text-decoration: none;
  font-size: 1.2rem;
}

nav a:hover {
  text-decoration: underline;
}

main {
  padding: 20px;
  background-color: #f9f9f9;
  min-height: calc(100vh - 100px);
}
</style>
