import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Admin from '../components/Admin.vue'
import staffManage from '../components/StaffManage.vue'

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
          path: '/Staff',
          component: staffManage
        }
      ],
      component: Admin
    }
  ]
})
