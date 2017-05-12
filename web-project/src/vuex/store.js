/**
 * Created by mouizumi on 2017/5/12.
 */
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  isLogin: false,
  loginId: '',
  loginType: ''
}

const mutations = {
  GETLOGIN (state) {
    state.isLogin = localStorage.getItem('isLogin')
    state.loginId = localStorage.getItem('loginId')
    state.loginType = localStorage.getItem('loginType')
  },
  LOGIN (state, data) {
    state.isLogin = true
    state.loginId = data.id
    state.loginType = data.type
    localStorage.setItem('isLogin', state.isLogin)
    localStorage.setItem('loginId', state.loginId)
    localStorage.setItem('loginType', state.loginType)
  },
  LOGOUT (state) {
    state.isLogin = false
    state.loginId = -1
    state.loginType = -1
    localStorage.setItem('isLogin', false)
    localStorage.setItem('loginId', '')
    localStorage.setItem('loginType', '')
  }
}

export default new Vuex.Store({
  state,
  mutations
})
