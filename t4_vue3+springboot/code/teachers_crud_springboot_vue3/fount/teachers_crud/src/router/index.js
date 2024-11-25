import { createRouter, createWebHistory } from "vue-router";
import Home from "../components/Home.vue";
import TeacherSearch from "../components/TeacherSearch.vue";
import TeacherList from "../components/TeacherList.vue";
import TeacherAdd from "../components/TeacherAdd.vue";


const routes = [
    {
        path: "/",
        name: "Home",
        component: Home, // 首页
    },
    {
        path: "/search",
        name: "TeacherSearch",
        component: TeacherSearch, // 教师查询
    },
    {
        path: "/list",
        name: "TeacherList",
        component: TeacherList,
    },
    {
        path: "/add",
        name: "TeacherAdd",
        component: TeacherAdd,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
