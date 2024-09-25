import { createRouter, createWebHistory } from 'vue-router';
import T12 from '../components/t12.vue';
import T11 from '../components/t11.vue';
import T13 from '../components/t13.vue';

const routes = [
    { path: '/t12', component: T12 },
    { path: '/t11', component: T11 },
    { path: '/t13', component: T13 },
    // 可以添加更多的路由规则
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;