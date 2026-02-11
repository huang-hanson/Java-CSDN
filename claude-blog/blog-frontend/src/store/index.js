import Vue from 'vue'
import Vuex from 'vuex'
import api from '../api'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: JSON.parse(localStorage.getItem('user')) || null,
    token: localStorage.getItem('token') || null
  },
  getters: {
    isLoggedIn: state => !!state.token,
    user: state => state.user,
    token: state => state.token
  },
  mutations: {
    SET_USER(state, user) {
      state.user = user
      if (user) {
        localStorage.setItem('user', JSON.stringify(user))
      } else {
        localStorage.removeItem('user')
      }
    },
    SET_TOKEN(state, token) {
      state.token = token
      if (token) {
        localStorage.setItem('token', token)
      } else {
        localStorage.removeItem('token')
      }
    }
  },
  actions: {
    async login({ commit }, credentials) {
      const response = await api.login(credentials)
      if (response.code === 200) {
        commit('SET_TOKEN', response.data.token)
        commit('SET_USER', response.data.user)
      }
      return response
    },
    async register({ commit }, userData) {
      const response = await api.register(userData)
      if (response.code === 200) {
        commit('SET_TOKEN', response.data.token)
        commit('SET_USER', response.data.user)
      }
      return response
    },
    logout({ commit }) {
      commit('SET_TOKEN', null)
      commit('SET_USER', null)
    },
    updateUser({ commit }, user) {
      commit('SET_USER', user)
    }
  }
})
