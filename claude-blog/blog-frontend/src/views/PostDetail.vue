<template>
  <div class="post-detail">
    <el-card v-if="post" class="post-card">
      <div class="post-header">
        <h1 class="post-title">{{ post.title }}</h1>
        <div class="post-meta">
          <span><i class="el-icon-user"></i> {{ post.authorName }}</span>
          <span><i class="el-icon-view"></i> {{ post.viewCount }}</span>
          <span><i class="el-icon-time"></i> {{ formatDate(post.createTime) }}</span>
        </div>
      </div>
      <el-divider></el-divider>
      <div class="post-content" v-html="renderedContent"></div>
      <el-divider></el-divider>
      <div class="post-actions">
        <el-button :type="post.favorited ? 'warning' : 'default'" @click="toggleFavorite">
          <i :class="post.favorited ? 'el-icon-star-on' : 'el-icon-star-off'"></i>
          {{ post.favorited ? 'Favorited' : 'Favorite' }}
        </el-button>
        <el-button v-if="isAuthor" type="primary" @click="editPost">Edit</el-button>
        <el-button v-if="isAuthor" type="danger" @click="deletePost">Delete</el-button>
      </div>
    </el-card>

    <!-- Comments Section -->
    <el-card class="comment-card">
      <div slot="header">
        <span>Comments ({{ comments.length }})</span>
      </div>

      <!-- Comment Form -->
      <div class="comment-form" v-if="isLoggedIn">
        <el-input
          type="textarea"
          v-model="newComment"
          :rows="3"
          placeholder="Write a comment...">
        </el-input>
        <el-button type="primary" @click="submitComment" :loading="submitting" style="margin-top: 10px;">Submit</el-button>
      </div>
      <div v-else class="login-tip">
        <router-link to="/login">Login</router-link> to comment
      </div>

      <el-divider></el-divider>

      <!-- Comment List -->
      <div class="comment-list">
        <div v-for="comment in comments" :key="comment.id" class="comment-item">
          <div class="comment-header">
            <span class="comment-author">{{ comment.username }}</span>
            <span class="comment-time">{{ formatDate(comment.createTime) }}</span>
          </div>
          <div class="comment-content">{{ comment.content }}</div>
          <div class="comment-actions">
            <el-button type="text" size="small" @click="replyTo(comment)">Reply</el-button>
            <el-button v-if="isCommentOwner(comment)" type="text" size="small" @click="deleteComment(comment.id)">Delete</el-button>
          </div>

          <!-- Replies -->
          <div v-if="comment.replies && comment.replies.length > 0" class="replies">
            <div v-for="reply in comment.replies" :key="reply.id" class="reply-item">
              <span class="comment-author">{{ reply.username }}</span>
              <span v-if="reply.replyUsername" class="reply-to">@{{ reply.replyUsername }}</span>:
              {{ reply.content }}
              <span class="comment-time">{{ formatDate(reply.createTime) }}</span>
            </div>
          </div>
        </div>
        <el-empty v-if="comments.length === 0" description="No comments yet"></el-empty>
      </div>
    </el-card>

    <!-- Reply Dialog -->
    <el-dialog title="Reply" :visible.sync="replyDialogVisible" width="500px">
      <el-input type="textarea" v-model="replyContent" :rows="3" placeholder="Write your reply..."></el-input>
      <span slot="footer">
        <el-button @click="replyDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitReply">Submit</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import api from '../api'
import { marked } from 'marked'

export default {
  name: 'PostDetail',
  data() {
    return {
      post: null,
      comments: [],
      newComment: '',
      submitting: false,
      replyDialogVisible: false,
      replyContent: '',
      replyingTo: null
    }
  },
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn
    },
    currentUser() {
      return this.$store.getters.user
    },
    isAuthor() {
      return this.post && this.currentUser && this.post.authorId === this.currentUser.id
    },
    renderedContent() {
      if (this.post && this.post.content) {
        return marked(this.post.content)
      }
      return ''
    }
  },
  created() {
    this.loadPost()
    this.loadComments()
  },
  methods: {
    async loadPost() {
      try {
        const response = await api.getPostById(this.$route.params.id)
        if (response.code === 200) {
          this.post = response.data
        } else {
          this.$message.error('Post not found')
          this.$router.push('/')
        }
      } catch (error) {
        this.$message.error('Failed to load post')
      }
    },
    async loadComments() {
      try {
        const response = await api.getComments(this.$route.params.id)
        if (response.code === 200) {
          this.comments = response.data
        }
      } catch (error) {
        console.error('Failed to load comments')
      }
    },
    async toggleFavorite() {
      if (!this.isLoggedIn) {
        this.$message.warning('Please login first')
        return
      }
      try {
        if (this.post.favorited) {
          await api.removeFavorite(this.post.id)
          this.post.favorited = false
          this.$message.success('Removed from favorites')
        } else {
          await api.addFavorite(this.post.id)
          this.post.favorited = true
          this.$message.success('Added to favorites')
        }
      } catch (error) {
        this.$message.error('Operation failed')
      }
    },
    editPost() {
      this.$router.push(`/edit/${this.post.id}`)
    },
    async deletePost() {
      try {
        await this.$confirm('Are you sure you want to delete this post?', 'Warning', {
          type: 'warning'
        })
        const response = await api.deletePost(this.post.id)
        if (response.code === 200) {
          this.$message.success('Post deleted')
          this.$router.push('/')
        } else {
          this.$message.error(response.message)
        }
      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('Delete failed')
        }
      }
    },
    async submitComment() {
      if (!this.newComment.trim()) {
        this.$message.warning('Please enter comment content')
        return
      }
      this.submitting = true
      try {
        const response = await api.addComment({
          postId: this.post.id,
          content: this.newComment
        })
        if (response.code === 200) {
          this.$message.success('Comment added')
          this.newComment = ''
          this.loadComments()
        } else {
          this.$message.error(response.message)
        }
      } catch (error) {
        this.$message.error('Failed to add comment')
      } finally {
        this.submitting = false
      }
    },
    replyTo(comment) {
      if (!this.isLoggedIn) {
        this.$message.warning('Please login first')
        return
      }
      this.replyingTo = comment
      this.replyContent = ''
      this.replyDialogVisible = true
    },
    async submitReply() {
      if (!this.replyContent.trim()) {
        this.$message.warning('Please enter reply content')
        return
      }
      try {
        const response = await api.addComment({
          postId: this.post.id,
          parentId: this.replyingTo.id,
          replyUserId: this.replyingTo.userId,
          content: this.replyContent
        })
        if (response.code === 200) {
          this.$message.success('Reply added')
          this.replyDialogVisible = false
          this.loadComments()
        } else {
          this.$message.error(response.message)
        }
      } catch (error) {
        this.$message.error('Failed to add reply')
      }
    },
    isCommentOwner(comment) {
      return this.currentUser && comment.userId === this.currentUser.id
    },
    async deleteComment(commentId) {
      try {
        await this.$confirm('Are you sure you want to delete this comment?', 'Warning', {
          type: 'warning'
        })
        const response = await api.deleteComment(commentId)
        if (response.code === 200) {
          this.$message.success('Comment deleted')
          this.loadComments()
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
        year: 'numeric',
        month: 'short',
        day: 'numeric'
      })
    }
  }
}
</script>

<style scoped>
.post-detail {
  max-width: 800px;
  margin: 0 auto;
}

.post-card {
  margin-bottom: 20px;
}

.post-title {
  font-size: 28px;
  margin-bottom: 15px;
}

.post-meta {
  display: flex;
  gap: 20px;
  color: #909399;
  font-size: 14px;
}

.post-content {
  line-height: 1.8;
  font-size: 16px;
}

.post-content img {
  max-width: 100%;
}

.post-actions {
  display: flex;
  gap: 10px;
}

.comment-card {
  margin-top: 20px;
}

.comment-form {
  margin-bottom: 20px;
}

.login-tip {
  text-align: center;
  color: #909399;
  margin-bottom: 20px;
}

.login-tip a {
  color: #409EFF;
}

.comment-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.comment-item {
  padding: 15px;
  background: #f9f9f9;
  border-radius: 4px;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.comment-author {
  font-weight: bold;
  color: #409EFF;
}

.comment-time {
  color: #909399;
  font-size: 12px;
}

.comment-content {
  line-height: 1.6;
}

.comment-actions {
  margin-top: 10px;
}

.replies {
  margin-top: 15px;
  padding-left: 20px;
  border-left: 2px solid #ddd;
}

.reply-item {
  padding: 10px 0;
  font-size: 14px;
  color: #606266;
}

.reply-to {
  color: #409EFF;
}
</style>
