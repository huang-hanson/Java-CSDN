<template>
  <div class="home">
    <div class="search-bar">
      <el-input
        v-model="keyword"
        placeholder="Search posts..."
        @keyup.enter.native="searchPosts"
        style="max-width: 400px;">
        <el-button slot="append" icon="el-icon-search" @click="searchPosts"></el-button>
      </el-input>
    </div>

    <div class="post-list">
      <el-card v-for="post in posts" :key="post.id" class="post-card" @click.native="viewPost(post.id)">
        <div class="post-title">{{ post.title }}</div>
        <div class="post-summary">{{ post.summary }}</div>
        <div class="post-meta">
          <span><i class="el-icon-user"></i> {{ post.authorName }}</span>
          <span><i class="el-icon-view"></i> {{ post.viewCount }}</span>
          <span><i class="el-icon-chat-dot-round"></i> {{ post.commentCount }}</span>
          <span><i class="el-icon-time"></i> {{ formatDate(post.createTime) }}</span>
        </div>
      </el-card>

      <el-empty v-if="posts.length === 0 && !loading" description="No posts yet"></el-empty>
    </div>

    <div class="pagination" v-if="total > 0">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="pageSize"
        :current-page="pageNum"
        @current-change="handlePageChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import api from '../api'

export default {
  name: 'Home',
  data() {
    return {
      posts: [],
      keyword: '',
      pageNum: 1,
      pageSize: 10,
      total: 0,
      loading: false
    }
  },
  created() {
    this.loadPosts()
  },
  methods: {
    async loadPosts() {
      this.loading = true
      try {
        const response = await api.getPostList({
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          keyword: this.keyword
        })
        if (response.code === 200) {
          this.posts = response.data.list
          this.total = response.data.total
        }
      } catch (error) {
        this.$message.error('Failed to load posts')
      } finally {
        this.loading = false
      }
    },
    searchPosts() {
      this.pageNum = 1
      this.loadPosts()
    },
    handlePageChange(page) {
      this.pageNum = page
      this.loadPosts()
    },
    viewPost(id) {
      this.$router.push(`/post/${id}`)
    },
    formatDate(dateStr) {
      if (!dateStr) return ''
      const date = new Date(dateStr)
      return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric'
      })
    }
  }
}
</script>

<style scoped>
.home {
  max-width: 800px;
  margin: 0 auto;
}

.search-bar {
  margin-bottom: 20px;
  text-align: center;
}

.post-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.post-card {
  cursor: pointer;
  transition: all 0.3s;
}

.post-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.post-title {
  font-size: 20px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 10px;
}

.post-summary {
  color: #606266;
  font-size: 14px;
  line-height: 1.6;
  margin-bottom: 15px;
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

.post-meta span {
  display: flex;
  align-items: center;
  gap: 5px;
}

.pagination {
  margin-top: 30px;
  text-align: center;
}
</style>
