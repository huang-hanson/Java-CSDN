<template>
  <div id="app">
    <el-container>
      <el-header>
        <div class="header-content">
          <div class="logo" @click="goHome">
            <i class="el-icon-reading"></i>
            Claude Blog
          </div>
          <div class="nav-menu">
            <el-menu mode="horizontal" :default-active="activeIndex" @select="handleSelect" background-color="#409EFF" text-color="#fff" active-text-color="#ffd04b">
              <el-menu-item index="/">Home</el-menu-item>
              <el-menu-item index="/create" v-if="isLoggedIn">New Post</el-menu-item>
              <el-menu-item index="/myposts" v-if="isLoggedIn">My Posts</el-menu-item>
              <el-menu-item index="/favorites" v-if="isLoggedIn">Favorites</el-menu-item>
            </el-menu>
          </div>
          <div class="user-menu">
            <template v-if="isLoggedIn">
              <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link">
                  {{ user.nickname || user.username }}
                  <i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="profile">Profile</el-dropdown-item>
                  <el-dropdown-item command="logout">Logout</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
            <template v-else>
              <el-button type="text" @click="goLogin" style="color: white;">Login</el-button>
              <el-button type="text" @click="goRegister" style="color: white;">Register</el-button>
            </template>
          </div>
        </div>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
      <el-footer>
        <p>Claude Blog &copy; 2024 - Powered by Vue.js & Spring Boot</p>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      activeIndex: '/'
    }
  },
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn
    },
    user() {
      return this.$store.getters.user
    }
  },
  watch: {
    $route(to) {
      this.activeIndex = to.path
    }
  },
  methods: {
    goHome() {
      this.$router.push('/')
    },
    goLogin() {
      this.$router.push('/login')
    },
    goRegister() {
      this.$router.push('/register')
    },
    handleSelect(index) {
      this.$router.push(index)
    },
    handleCommand(command) {
      if (command === 'logout') {
        this.$store.dispatch('logout')
        this.$router.push('/')
        this.$message.success('Logged out successfully')
      } else if (command === 'profile') {
        this.$router.push('/profile')
      }
    }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', 'Microsoft YaHei', Arial, sans-serif;
  background-color: #f5f7fa;
}

#app {
  min-height: 100vh;
}

.el-container {
  min-height: 100vh;
}

.el-header {
  background-color: #409EFF;
  padding: 0 20px;
  height: 60px !important;
}

.header-content {
  display: flex;
  align-items: center;
  height: 100%;
  max-width: 1200px;
  margin: 0 auto;
}

.logo {
  color: white;
  font-size: 24px;
  font-weight: bold;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 10px;
  min-width: 180px;
}

.nav-menu {
  flex: 1;
}

.nav-menu .el-menu {
  border-bottom: none;
}

.nav-menu .el-menu-item {
  border-bottom: none;
}

.user-menu {
  min-width: 150px;
  text-align: right;
}

.user-menu .el-dropdown-link {
  color: white;
  cursor: pointer;
  display: flex;
  align-items: center;
}

.el-main {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  width: 100%;
}

.el-footer {
  background-color: #2c3e50;
  color: #95a5a6;
  text-align: center;
  padding: 20px;
  height: auto !important;
}
</style>
