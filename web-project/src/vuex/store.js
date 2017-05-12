/**
 * Created by mouizumi on 2017/5/12.
 */
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  loginId: '',
  loginType: ''
}

const mutations = {
  INIT_STATE (state) {
    state.loginType = -1
    state.loginId = -1
  },
  LOGIN (state, data) {
    state.loginId = data.id
    state.loginType = data.type
  },
  LOGOUT (state) {
    state.loginId = -1
    state.loginType = -1
  }
}

export default new Vuex.Store({
  state,
  mutations
})
