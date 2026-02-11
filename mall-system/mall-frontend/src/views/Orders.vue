<template>
  <div class="orders">
    <h2>My Orders</h2>
    <el-table :data="orders" style="width: 100%" v-if="orders.length > 0">
      <el-table-column prop="orderNo" label="Order No" width="200"></el-table-column>
      <el-table-column label="Items" width="300">
        <template slot-scope="scope">
          <div v-for="item in scope.row.orderItems" :key="item.id" class="order-item">
            <span>{{ item.productName }}</span>
            <span>x{{ item.quantity }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="Total" width="120">
        <template slot-scope="scope">
          ¥{{ scope.row.totalAmount }}
        </template>
      </el-table-column>
      <el-table-column label="Status" width="120">
        <template slot-scope="scope">
          <el-tag :type="getStatusType(scope.row.status)">{{ getStatusText(scope.row.status) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="Create Time" width="180"></el-table-column>
      <el-table-column label="Action" width="150">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status === 0" type="primary" size="small" @click="payOrder(scope.row)">Pay</el-button>
          <el-button v-if="scope.row.status === 0" type="danger" size="small" @click="cancelOrder(scope.row)">Cancel</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-empty v-else description="No orders yet"></el-empty>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Orders',
  data() {
    return {
      orders: []
    }
  },
  created() {
    this.loadOrders()
  },
  methods: {
    async loadOrders() {
      try {
        const response = await axios.get(`/api/order/list/${this.$store.getters.userId}`)
        this.orders = response.data.data || []
      } catch (error) {
        console.error('Failed to load orders:', error)
      }
    },
    getStatusText(status) {
      const statusMap = {
        0: 'Pending',
        1: 'Paid',
        2: 'Shipped',
        3: 'Completed',
        4: 'Cancelled'
      }
      return statusMap[status] || 'Unknown'
    },
    getStatusType(status) {
      const typeMap = {
        0: 'warning',
        1: 'primary',
        2: 'info',
        3: 'success',
        4: 'danger'
      }
      return typeMap[status] || 'info'
    },
    async payOrder(order) {
      try {
        await axios.put(`/api/order/pay/${order.id}`)
        this.$message.success('Payment successful')
        this.loadOrders()
      } catch (error) {
        this.$message.error('Payment failed')
      }
    },
    async cancelOrder(order) {
      try {
        await axios.put(`/api/order/cancel/${order.id}`)
        this.$message.success('Order cancelled')
        this.loadOrders()
      } catch (error) {
        this.$message.error('Failed to cancel order')
      }
    }
  }
}
</script>

<style scoped>
.orders {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  background: white;
  border-radius: 4px;
}

.orders h2 {
  margin-bottom: 20px;
}

.order-item {
  display: flex;
  justify-content: space-between;
  padding: 5px 0;
}
</style>