<template>
  <div id="app">
    <el-container>
      <el-header>
        <div class="header-content">
          <div class="logo" @click="$router.push('/')">Mall System</div>
          <div class="nav-menu">
            <el-menu mode="horizontal" :default-active="activeIndex" router>
              <el-menu-item index="/">Home</el-menu-item>
              <el-menu-item index="/products">Products</el-menu-item>
              <el-menu-item index="/cart" v-if="isLoggedIn">
                <el-badge :value="cartCount" class="cart-badge">Cart</el-badge>
              </el-menu-item>
              <el-menu-item index="/orders" v-if="isLoggedIn">My Orders</el-menu-item>
            </el-menu>
          </div>
          <div class="user-info">
            <template v-if="isLoggedIn">
              <span>Welcome, {{ username }}</span>
              <el-button type="text" @click="logout">Logout</el-button>
            </template>
            <template v-else>
              <el-button type="primary" size="small" @click="$router.push('/login')">Login</el-button>
              <el-button size="small" @click="$router.push('/register')">Register</el-button>
            </template>
          </div>
        </div>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
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
    username() {
      return this.$store.getters.username
    },
    cartCount() {
      return this.$store.getters.cartCount
    }
  },
  methods: {
    logout() {
      this.$store.dispatch('logout')
      this.$router.push('/')
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

#app {
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', 'Microsoft YaHei', Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.el-header {
  background-color: #409EFF;
  color: white;
  padding: 0 20px;
}

.header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 60px;
}

.logo {
  font-size: 24px;
  font-weight: bold;
  cursor: pointer;
}

.nav-menu .el-menu {
  background-color: transparent;
  border-bottom: none;
}

.nav-menu .el-menu-item {
  color: white;
  border-bottom: none;
}

.nav-menu .el-menu-item:hover,
.nav-menu .el-menu-item.is-active {
  background-color: rgba(255, 255, 255, 0.2);
  border-bottom: none;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 10px;
}

.cart-badge .el-badge__content {
  top: 8px;
}

.el-main {
  padding: 20px;
  min-height: calc(100vh - 60px);
  background-color: #f5f5f5;
}
</style>