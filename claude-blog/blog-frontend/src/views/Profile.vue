<template>
  <div class="profile">
    <el-card>
      <div slot="header">
        <span>Profile</span>
      </div>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px" style="max-width: 500px;">
        <el-form-item label="Username">
          <el-input v-model="user.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="Nickname" prop="nickname">
          <el-input v-model="form.nickname" placeholder="Enter nickname"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="form.email" placeholder="Enter email"></el-input>
        </el-form-item>
        <el-form-item label="Avatar">
          <el-input v-model="form.avatar" placeholder="Enter avatar URL"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateProfile" :loading="loading">Save</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card style="margin-top: 20px;">
      <div slot="header">
        <span>Statistics</span>
      </div>
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="stat-item">
            <div class="stat-value">{{ stats.posts }}</div>
            <div class="stat-label">Posts</div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="stat-item">
            <div class="stat-value">{{ stats.views }}</div>
            <div class="stat-label">Total Views</div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="stat-item">
            <div class="stat-value">{{ stats.favorites }}</div>
            <div class="stat-label">Favorites</div>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import api from '../api'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Profile',
  data() {
    return {
      form: {
        nickname: '',
        email: '',
        avatar: ''
      },
      rules: {
        email: [
          { type: 'email', message: 'Please enter a valid email', trigger: 'blur' }
        ]
      },
      loading: false,
      stats: {
        posts: 0,
        views: 0,
        favorites: 0
      }
    }
  },
  computed: {
    ...mapGetters(['user'])
  },
  created() {
    this.initForm()
    this.loadStats()
  },
  methods: {
    ...mapActions(['updateUser']),
    initForm() {
      if (this.user) {
        this.form.nickname = this.user.nickname || ''
        this.form.email = this.user.email || ''
        this.form.avatar = this.user.avatar || ''
      }
    },
    async loadStats() {
      try {
        const [postsRes, favoritesRes] = await Promise.all([
          api.getMyPosts(),
          api.getFavorites()
        ])
        if (postsRes.code === 200) {
          this.stats.posts = postsRes.data.length
          this.stats.views = postsRes.data.reduce((sum, post) => sum + (post.viewCount || 0), 0)
        }
        if (favoritesRes.code === 200) {
          this.stats.favorites = favoritesRes.data.length
        }
      } catch (error) {
        console.error('Failed to load stats')
      }
    },
    async updateProfile() {
      this.$refs.form.validate(async (valid) => {
        if (valid) {
          this.loading = true
          try {
            const response = await api.updateUser({
              id: this.user.id,
              nickname: this.form.nickname,
              email: this.form.email,
              avatar: this.form.avatar
            })
            if (response.code === 200) {
              this.updateUser(response.data)
              this.$message.success('Profile updated')
            } else {
              this.$message.error(response.message)
            }
          } catch (error) {
            this.$message.error('Update failed')
          } finally {
            this.loading = false
          }
        }
      })
    }
  }
}
</script>

<style scoped>
.profile {
  max-width: 700px;
  margin: 0 auto;
}

.stat-item {
  text-align: center;
  padding: 20px;
}

.stat-value {
  font-size: 36px;
  font-weight: bold;
  color: #409EFF;
}

.stat-label {
  font-size: 14px;
  color: #909399;
  margin-top: 5px;
}
</style>
