<template>
  <div class="login">
    <el-card class="login-card">
      <div slot="header">
        <span>Login</span>
      </div>
      <el-form :model="form" :rules="rules" ref="loginForm">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="Username" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" placeholder="Password" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="handleLogin" :loading="loading">Login</el-button>
        </el-form-item>
        <div class="register-link">
          Don't have an account? <router-link to="/register">Register</router-link>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Login',
  data() {
    return {
      form: {
        username: '',
        password: ''
      },
      rules: {
        username: [{ required: true, message: 'Please enter username', trigger: 'blur' }],
        password: [{ required: true, message: 'Please enter password', trigger: 'blur' }]
      },
      loading: false
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(async (valid) => {
        if (valid) {
          this.loading = true
          try {
            const response = await axios.post('/api/user/login', this.form)
            if (response.data.code === 200) {
              const { token, user } = response.data.data
              this.$store.dispatch('login', { token, user })
              this.$message.success('Login successful')
              this.$router.push('/')
            } else {
              this.$message.error(response.data.message)
            }
          } catch (error) {
            this.$message.error('Login failed')
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
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 60px);
}

.login-card {
  width: 400px;
}

.register-link {
  text-align: center;
  margin-top: 10px;
}

.register-link a {
  color: #409EFF;
}
</style>