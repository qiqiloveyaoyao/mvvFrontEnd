<template>
  <div id="app">
    <nav class="nav1">
      <router-link to="/t11" @click.native="hideHeader">t11</router-link>
      <router-link to="/t12" @click.native="hideHeader">t12</router-link>
      <router-link to="/t13" @click.native="hideHeader">t13</router-link>
    </nav>
    <router-view></router-view>

    <div id="clickMe" v-show="!isHidden">
    <h2>Welcome to page</h2></div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      isHidden: false
    };
  },
  methods: {
    hideHeader() {
      this.isHidden = true;
    }
  }
};
</script>

<style>
.hidden {
  display: none;
}
/* 导航栏容器样式 */
.nav1 {
  background-color: #333;
  overflow: hidden;
}

/* 导航栏链接样式 */
.nav1 > a {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 20px;
  text-decoration: none;
}

/* 导航栏链接悬停效果 */
.nav1 > a:hover {
  background-color: #ddd;
  color: black;
}

/* 活动状态的导航栏链接样式 */
.nav1 > a.router-link-active {
  background-color: #4CAF50;
  color: white;
}

/* 响应式设计：当屏幕宽度小于600px时，导航栏链接堆叠显示 */
@media screen and (max-width: 600px) {
  .nav1 > a {
    float: none;
    width: 100%;
  }
}
</style>