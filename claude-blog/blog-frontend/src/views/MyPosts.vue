<template>
  <div class="my-posts">
    <el-card>
      <div slot="header" class="card-header">
        <span>My Posts</span>
        <el-button type="primary" size="small" @click="createPost">New Post</el-button>
      </div>
      <el-table :data="posts" style="width: 100%" v-loading="loading">
        <el-table-column prop="title" label="Title" min-width="200">
          <template slot-scope="scope">
            <router-link :to="`/post/${scope.row.id}`" class="post-link">
              {{ scope.row.title }}
            </router-link>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="Status" width="100">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'info'" size="small">
              {{ scope.row.status === 1 ? 'Published' : 'Draft' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="viewCount" label="Views" width="80"></el-table-column>
        <el-table-column prop="commentCount" label="Comments" width="100"></el-table-column>
        <el-table-column prop="createTime" label="Created" width="120">
          <template slot-scope="scope">
            {{ formatDate(scope.row.createTime) }}
          </template>
        </el-table-column>
        <el-table-column label="Actions" width="150">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="editPost(scope.row.id)">Edit</el-button>
            <el-button type="text" size="small" @click="deletePost(scope.row)" style="color: #F56C6C;">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-empty v-if="posts.length === 0 && !loading" description="No posts yet">
        <el-button type="primary" @click="createPost">Create Your First Post</el-button>
      </el-empty>
    </el-card>
  </div>
</template>

<script>
import api from '../api'

export default {
  name: 'MyPosts',
  data() {
    return {
      posts: [],
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
        const response = await api.getMyPosts()
        if (response.code === 200) {
          this.posts = response.data
        }
      } catch (error) {
        this.$message.error('Failed to load posts')
      } finally {
        this.loading = false
      }
    },
    createPost() {
      this.$router.push('/create')
    },
    editPost(id) {
      this.$router.push(`/edit/${id}`)
    },
    async deletePost(post) {
      try {
        await this.$confirm('Are you sure you want to delete this post?', 'Warning', {
          type: 'warning'
        })
        const response = await api.deletePost(post.id)
        if (response.code === 200) {
          this.$message.success('Post deleted')
          this.loadPosts()
        } else {
          this.$message.error(response.message)
        }
      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('Delete failed')
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
.my-posts {
  max-width: 900px;
  margin: 0 auto;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.post-link {
  color: #409EFF;
  text-decoration: none;
}

.post-link:hover {
  text-decoration: underline;
}
</style>
