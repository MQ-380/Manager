import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Admin from '../components/Admin.vue'
import DepartManage from '../components/DepartManage.vue'
import Notice from '../components/CheckNetworkNotice.vue'
import StaffManage from '../components/StaffManage.vue'
import Add from '../components/AddInformation.vue'

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
          component: DepartManage,
          children: [
            {
              path: '/Notice',
              component: Notice
            },
            {
              path: '/Add',
              component: Add
            }
          ]
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
