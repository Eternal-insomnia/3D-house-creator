import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/components/Login.vue';
import Dashboard from '@/components/dashboard/Dashboard.vue';
import UserList from '@/components/UserList.vue';

const routes = [
  { path: '/', component: Login },
  { path: '/dashboard', component: Dashboard },
  { path: '/userlist', component: UserList },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;