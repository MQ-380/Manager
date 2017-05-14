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
  noticeState: {
    infoName: '',
    notice: false
  }
}

const mutations = {
  GETLOGIN (state) {
    state.LoginState.isLogin = localStorage.getItem('isLogin')
    state.LoginState.loginId = localStorage.getItem('loginId')
    state.LoginState.loginType = localStorage.getItem('loginType')
  },
  LOGIN (state, data) {
    state.LoginState.isLogin = true
    state.LoginState.loginId = data.id
    state.LoginState.loginType = data.type
    localStorage.setItem('isLogin', state.LoginState.isLogin)
    localStorage.setItem('loginId', state.LoginState.loginId)
    localStorage.setItem('loginType', state.LoginState.loginType)
  },
  LOGOUT (state) {
    state.LoginState.isLogin = false
    state.LoginState.loginId = -1
    state.LoginState.loginType = -1
    localStorage.setItem('isLogin', false)
    localStorage.setItem('loginId', '')
    localStorage.setItem('loginType', '')
  },
  SETDEPARTDATA (state, data) {
    state.departState.departData = data
    localStorage.setItem('depart', state.departState.departData)
  },
  GETDEPARTDATA (state) {
    state.departState.departData = localStorage.getItem('depart')
  },
  SETNOTICE (state, data) {
    state.noticeState.infoName = data.name
    state.noticeState.notice = data.show
  }
}

export default new Vuex.Store({
  state,
  mutations
})
