<template>
  <div class="favorites">
    <el-card>
      <div slot="header">
        <span>My Favorites</span>
      </div>
      <div class="favorite-list" v-loading="loading">
        <el-card v-for="favorite in favorites" :key="favorite.id" class="favorite-item" @click.native="viewPost(favorite.post.id)">
          <div class="post-title">{{ favorite.post.title }}</div>
          <div class="post-summary">{{ favorite.post.summary }}</div>
          <div class="post-meta">
            <span><i class="el-icon-user"></i> {{ favorite.post.authorName }}</span>
            <span><i class="el-icon-time"></i> Favorited {{ formatDate(favorite.createTime) }}</span>
          </div>
          <div class="post-actions">
            <el-button type="text" @click.stop="removeFavorite(favorite)">Remove</el-button>
          </div>
        </el-card>
        <el-empty v-if="favorites.length === 0 && !loading" description="No favorites yet"></el-empty>
      </div>
    </el-card>
  </div>
</template>

<script>
import api from '../api'

export default {
  name: 'Favorites',
  data() {
    return {
      favorites: [],
      loading: false
    }
  },
  created() {
    this.loadFavorites()
  },
  methods: {
    async loadFavorites() {
      this.loading = true
      try {
        const response = await api.getFavorites()
        if (response.code === 200) {
          this.favorites = response.data
        }
      } catch (error) {
        this.$message.error('Failed to load favorites')
      } finally {
        this.loading = false
      }
    },
    viewPost(postId) {
      this.$router.push(`/post/${postId}`)
    },
    async removeFavorite(favorite) {
      try {
        await this.$confirm('Remove this post from favorites?', 'Confirm', {
          type: 'warning'
        })
        const response = await api.removeFavorite(favorite.post.id)
        if (response.code === 200) {
          this.$message.success('Removed from favorites')
          this.loadFavorites()
        } else {
          this.$message.error(response.message)
        }
      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('Operation failed')
        }
      }
    },
    formatDate(dateStr) {
      if (!dateStr) return ''
      const date = new Date(dateStr)
      return date.toLocaleDateString('en-US', {
        month: 'short',
        day: 'numeric'
      })
    }
  }
}
</script>

<style scoped>
.favorites {
  max-width: 800px;
  margin: 0 auto;
}

.favorite-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.favorite-item {
  cursor: pointer;
  transition: all 0.3s;
}

.favorite-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.post-title {
  font-size: 18px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 8px;
}

.post-summary {
  color: #606266;
  font-size: 14px;
  line-height: 1.6;
  margin-bottom: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.post-meta {
  display: flex;
  gap: 20px;
  color: #909399;
  font-size: 13px;
}

.post-actions {
  margin-top: 10px;
  text-align: right;
}
</style>
