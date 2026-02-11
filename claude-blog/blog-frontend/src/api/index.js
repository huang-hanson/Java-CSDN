import axios from 'axios'

const instance = axios.create({
  baseURL: '/api',
  timeout: 10000
})

instance.interceptors.request.use(config => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`
  }
  return config
})

instance.interceptors.response.use(
  response => response.data,
  error => {
    if (error.response) {
      return Promise.reject(error.response.data)
    }
    return Promise.reject({ message: 'Network Error' })
  }
)

export default {
  // User APIs
  login(data) {
    return instance.post('/user/login', data)
  },
  register(data) {
    return instance.post('/user/register', data)
  },
  getUserInfo(id) {
    return instance.get(`/user/info/${id}`)
  },
  updateUser(data) {
    return instance.put('/user/update', data)
  },

  // Post APIs
  getPostList(params) {
    return instance.get('/post/list', { params })
  },
  getPostById(id) {
    return instance.get(`/post/${id}`)
  },
  createPost(data) {
    return instance.post('/post', data)
  },
  updatePost(id, data) {
    return instance.put(`/post/${id}`, data)
  },
  deletePost(id) {
    return instance.delete(`/post/${id}`)
  },
  getMyPosts() {
    return instance.get('/post/my')
  },

  // Comment APIs
  getComments(postId) {
    return instance.get(`/comment/list/${postId}`)
  },
  addComment(data) {
    return instance.post('/comment', data)
  },
  deleteComment(id) {
    return instance.delete(`/comment/${id}`)
  },

  // Favorite APIs
  addFavorite(postId) {
    return instance.post(`/favorite/${postId}`)
  },
  removeFavorite(postId) {
    return instance.delete(`/favorite/${postId}`)
  },
  getFavorites() {
    return instance.get('/favorite/list')
  },
  checkFavorite(postId) {
    return instance.get(`/favorite/check/${postId}`)
  }
}
