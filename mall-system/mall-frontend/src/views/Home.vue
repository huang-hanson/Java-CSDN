<template>
  <div class="home">
    <el-carousel height="400px">
      <el-carousel-item v-for="item in banners" :key="item.id">
        <div class="banner" :style="{ backgroundColor: item.color }">
          <h2>{{ item.title }}</h2>
          <p>{{ item.subtitle }}</p>
        </div>
      </el-carousel-item>
    </el-carousel>
    
    <div class="section">
      <h2>Featured Products</h2>
      <el-row :gutter="20">
        <el-col :span="6" v-for="product in featuredProducts" :key="product.id">
          <el-card :body-style="{ padding: '0px' }" class="product-card" @click.native="goToProduct(product.id)">
            <img :src="product.image" class="product-image">
            <div class="product-info">
              <h3>{{ product.name }}</h3>
              <p class="price">¥{{ product.price }}</p>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    
    <div class="section">
      <h2>Categories</h2>
      <el-row :gutter="20">
        <el-col :span="6" v-for="category in categories" :key="category.id">
          <el-card class="category-card" @click.native="goToCategory(category.id)">
            <div class="category-name">{{ category.name }}</div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Home',
  data() {
    return {
      banners: [
        { id: 1, title: 'Welcome to Mall System', subtitle: 'Best products, best prices', color: '#409EFF' },
        { id: 2, title: 'New Arrivals', subtitle: 'Check out our latest products', color: '#67C23A' },
        { id: 3, title: 'Special Offers', subtitle: 'Up to 50% off on selected items', color: '#E6A23C' }
      ],
      featuredProducts: [],
      categories: []
    }
  },
  created() {
    this.loadFeaturedProducts()
    this.loadCategories()
  },
  methods: {
    async loadFeaturedProducts() {
      try {
        const response = await axios.get('/api/product/page?pageNum=1&pageSize=8')
        this.featuredProducts = response.data.data.records || []
      } catch (error) {
        console.error('Failed to load products:', error)
      }
    },
    async loadCategories() {
      try {
        const response = await axios.get('/api/category/list')
        this.categories = response.data.data || []
      } catch (error) {
        console.error('Failed to load categories:', error)
      }
    },
    goToProduct(id) {
      this.$router.push(`/product/${id}`)
    },
    goToCategory(id) {
      this.$router.push(`/products?category=${id}`)
    }
  }
}
</script>

<style scoped>
.home {
  max-width: 1200px;
  margin: 0 auto;
}

.banner {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
}

.banner h2 {
  font-size: 36px;
  margin-bottom: 10px;
}

.banner p {
  font-size: 18px;
}

.section {
  margin: 30px 0;
}

.section h2 {
  margin-bottom: 20px;
  color: #303133;
}

.product-card {
  cursor: pointer;
  transition: transform 0.3s;
}

.product-card:hover {
  transform: translateY(-5px);
}

.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.product-info {
  padding: 14px;
}

.product-info h3 {
  font-size: 14px;
  color: #303133;
  margin-bottom: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.price {
  color: #f56c6c;
  font-size: 18px;
  font-weight: bold;
}

.category-card {
  cursor: pointer;
  text-align: center;
  transition: all 0.3s;
}

.category-card:hover {
  background-color: #409EFF;
  color: white;
}

.category-name {
  font-size: 16px;
  padding: 20px 0;
}
</style>