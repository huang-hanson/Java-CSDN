import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: localStorage.getItem('token') || '',
    user: JSON.parse(localStorage.getItem('user') || '{}'),
    cartCount: 0
  },
  getters: {
    isLoggedIn: state => !!state.token,
    username: state => state.user.username || '',
    userId: state => state.user.id || null,
    cartCount: state => state.cartCount
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token
      localStorage.setItem('token', token)
    },
    SET_USER(state, user) {
      state.user = user
      localStorage.setItem('user', JSON.stringify(user))
    },
    CLEAR_AUTH(state) {
      state.token = ''
      state.user = {}
      localStorage.removeItem('token')
      localStorage.removeItem('user')
    },
    SET_CART_COUNT(state, count) {
      state.cartCount = count
    }
  },
  actions: {
    login({ commit }, { token, user }) {
      commit('SET_TOKEN', token)
      commit('SET_USER', user)
    },
    logout({ commit }) {
      commit('CLEAR_AUTH')
      commit('SET_CART_COUNT', 0)
    },
    async fetchCartCount({ commit, getters }) {
      if (getters.userId) {
        try {
          const response = await axios.get(`/api/cart/list/${getters.userId}`)
          commit('SET_CART_COUNT', response.data.data?.length || 0)
        } catch (error) {
          console.error('Failed to fetch cart count:', error)
        }
      }
    }
  }
})