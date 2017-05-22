/**
 * Created by mouizumi on 2017/5/12.
 */
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  LoginState: {
    isLogin: false,
    loginId: '',
    loginType: ''
  },
  departState: {
    departData: []
  },
  employeeState: {
    staffInfo: {}
  }
}

const mutations = {
  GETLOGIN (state) {
    state.LoginState.isLogin = sessionStorage.getItem('isLogin')
    state.LoginState.loginId = sessionStorage.getItem('loginId')
    state.LoginState.loginType = sessionStorage.getItem('loginType')
  },
  LOGIN (state, data) {
    state.LoginState.isLogin = true
    state.LoginState.loginId = data.id
    state.LoginState.loginType = data.type
    sessionStorage.setItem('isLogin', state.LoginState.isLogin)
    sessionStorage.setItem('loginId', state.LoginState.loginId)
    sessionStorage.setItem('loginType', state.LoginState.loginType)
  },
  LOGOUT (state) {
    state.LoginState.isLogin = false
    state.LoginState.loginId = -1
    state.LoginState.loginType = -1
    sessionStorage.setItem('isLogin', false)
    sessionStorage.setItem('loginId', '')
    sessionStorage.setItem('loginType', '')
  },
  SETDEPARTDATA (state, data) {
    state.departState.departData = data
    sessionStorage.setItem('depart', state.departState.departData)
  },
  GETDEPARTDATA (state) {
    state.departState.departData = sessionStorage.getItem('depart')
  },
  SETSTAFFINFO (state, data) {
    state.employeeState.staffInfo = data
    sessionStorage.setItem('staff', state.employeeState.staffInfo)
  },
  GETSTAFFINFO (state, data) {
    state.employeeState.staffInfo = sessionStorage.getItem('staff')
  }
}

export default new Vuex.Store({
  state,
  mutations
})
