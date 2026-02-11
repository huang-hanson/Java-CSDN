<template>
  <div class="cart">
    <h2>Shopping Cart</h2>
    <el-table :data="cartItems" style="width: 100%" v-if="cartItems.length > 0">
      <el-table-column width="60">
        <template slot-scope="scope">
          <el-checkbox v-model="scope.row.checked" @change="updateChecked(scope.row)"></el-checkbox>
        </template>
      </el-table-column>
      <el-table-column label="Product" width="400">
        <template slot-scope="scope">
          <div class="product-cell">
            <img :src="scope.row.product?.image" class="product-image">
            <div class="product-info">
              <span>{{ scope.row.product?.name }}</span>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="Price" width="120">
        <template slot-scope="scope">
          ¥{{ scope.row.product?.price }}
        </template>
      </el-table-column>
      <el-table-column label="Quantity" width="150">
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.quantity" :min="1" :max="scope.row.product?.stock" 
            size="small" @change="updateQuantity(scope.row)"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="Subtotal" width="120">
        <template slot-scope="scope">
          ¥{{ (scope.row.product?.price * scope.row.quantity).toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="Action" width="100">
        <template slot-scope="scope">
          <el-button type="danger" size="small" @click="removeItem(scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-empty v-else description="Your cart is empty"></el-empty>
    <div class="cart-footer" v-if="cartItems.length > 0">
      <div class="total">
        <span>Total: </span>
        <span class="total-price">¥{{ totalPrice }}</span>
      </div>
      <el-button type="primary" size="large" @click="checkout">Checkout</el-button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Cart',
  data() {
    return {
      cartItems: []
    }
  },
  computed: {
    totalPrice() {
      return this.cartItems
        .filter(item => item.checked)
        .reduce((sum, item) => sum + (item.product?.price * item.quantity), 0)
        .toFixed(2)
    }
  },
  created() {
    this.loadCart()
  },
  methods: {
    async loadCart() {
      try {
        const response = await axios.get(`/api/cart/list/${this.$store.getters.userId}`)
        this.cartItems = response.data.data || []
      } catch (error) {
        console.error('Failed to load cart:', error)
      }
    },
    async updateQuantity(item) {
      try {
        await axios.put(`/api/cart/quantity/${item.id}?quantity=${item.quantity}`)
        this.$store.dispatch('fetchCartCount')
      } catch (error) {
        this.$message.error('Failed to update quantity')
      }
    },
    async updateChecked(item) {
      try {
        await axios.put(`/api/cart/checked/${item.id}?checked=${item.checked ? 1 : 0}`)
      } catch (error) {
        this.$message.error('Failed to update')
      }
    },
    async removeItem(item) {
      try {
        await axios.delete(`/api/cart/delete/${item.id}`)
        this.cartItems = this.cartItems.filter(i => i.id !== item.id)
        this.$store.dispatch('fetchCartCount')
        this.$message.success('Item removed')
      } catch (error) {
        this.$message.error('Failed to remove item')
      }
    },
    async checkout() {
      const checkedItems = this.cartItems.filter(item => item.checked)
      if (checkedItems.length === 0) {
        this.$message.warning('Please select items to checkout')
        return
      }
      try {
        const order = {
          userId: this.$store.getters.userId,
          receiverName: this.$store.getters.username,
          receiverPhone: '13800138000',
          receiverAddress: 'Default Address'
        }
        await axios.post('/api/order/create', order)
        this.$message.success('Order created successfully')
        this.$router.push('/orders')
      } catch (error) {
        this.$message.error('Failed to create order')
      }
    }
  }
}
</script>

<style scoped>
.cart {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  background: white;
  border-radius: 4px;
}

.cart h2 {
  margin-bottom: 20px;
}

.product-cell {
  display: flex;
  align-items: center;
}

.product-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
  margin-right: 15px;
}

.cart-footer {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-top: 20px;
  padding-top: 20px;
  border-top: 1px solid #eee;
}

.total {
  margin-right: 20px;
}

.total-price {
  color: #f56c6c;
  font-size: 24px;
  font-weight: bold;
}
</style>