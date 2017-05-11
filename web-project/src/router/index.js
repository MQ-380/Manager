import Vue from 'vue'
import Router from 'vue-router'
import vueresource from 'vue-resource'
import Login from '../components/Login.vue'
import Admin from '../components/Admin.vue'
import iView from 'iview'
import 'iview/dist/styles/iview.css'

Vue.use(Router)
Vue.use(iView)
Vue.use(vueresource)

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
      component: Admin
    }
  ]
})
