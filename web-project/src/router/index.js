import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Admin from '../components/Admin.vue'
import DepartManage from '../components/DepartmentManage.vue'
import StaffManage from '../components/StaffManage.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Admin',
      name: 'Admin',
      children: [
        {
          path: '/Depart',
          component: DepartManage
        },
        {
          path: '/Staff',
          component: StaffManage
        }
      ],
      component: Admin
    }
  ]
})
