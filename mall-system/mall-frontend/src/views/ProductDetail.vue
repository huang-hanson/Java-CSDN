<template>
  <div class="product-detail">
    <el-row :gutter="40" v-if="product">
      <el-col :span="10">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="product.image" class="product-image">
        </el-card>
      </el-col>
      <el-col :span="14">
        <div class="product-info">
          <h1>{{ product.name }}</h1>
          <p class="price">¥{{ product.price }}</p>
          <p class="description">{{ product.description }}</p>
          <p class="stock">Stock: {{ product.stock }}</p>
          <div class="quantity">
            <span>Quantity:</span>
            <el-input-number v-model="quantity" :min="1" :max="product.stock"></el-input-number>
          </div>
          <div class="actions">
            <el-button type="primary" size="large" @click="addToCart">Add to Cart</el-button>
            <el-button type="success" size="large" @click="buyNow">Buy Now</el-button>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-empty v-else description="Product not found"></el-empty>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'ProductDetail',
  data() {
    return {
      product: null,
      quantity: 1
    }
  },
  created() {
    this.loadProduct()
  },
  methods: {
    async loadProduct() {
      try {
        const response = await axios.get(`/api/product/${this.$route.params.id}`)
        this.product = response.data.data
      } catch (error) {
        console.error('Failed to load product:', error)
      }
    },
    async addToCart() {
      if (!this.$store.getters.isLoggedIn) {
        this.$message.warning('Please login first')
        this.$router.push('/login')
        return
      }
      try {
        await axios.post('/api/cart/add', {
          userId: this.$store.getters.userId,
          productId: this.product.id,
          quantity: this.quantity
        })
        this.$message.success('Added to cart')
        this.$store.dispatch('fetchCartCount')
      } catch (error) {
        this.$message.error('Failed to add to cart')
      }
    },
    async buyNow() {
      if (!this.$store.getters.isLoggedIn) {
        this.$message.warning('Please login first')
        this.$router.push('/login')
        return
      }
      await this.addToCart()
      this.$router.push('/cart')
    }
  }
}
</script>

<style scoped>
.product-detail {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  background: white;
  border-radius: 4px;
}

.product-image {
  width: 100%;
  height: 400px;
  object-fit: cover;
}

.product-info h1 {
  font-size: 24px;
  color: #303133;
  margin-bottom: 20px;
}

.price {
  color: #f56c6c;
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 20px;
}

.description {
  color: #606266;
  line-height: 1.6;
  margin-bottom: 20px;
}

.stock {
  color: #909399;
  margin-bottom: 20px;
}

.quantity {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 10px;
}

.actions {
  display: flex;
  gap: 10px;
}
</style>