<template>
  <div class="create-post">
    <el-card>
      <div slot="header">
        <span>{{ isEdit ? 'Edit Post' : 'Create New Post' }}</span>
      </div>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="Title" prop="title">
          <el-input v-model="form.title" placeholder="Enter post title"></el-input>
        </el-form-item>
        <el-form-item label="Content" prop="content">
          <mavon-editor
            v-model="form.content"
            :toolbars="toolbars"
            :box-shadow="false"
            style="height: 500px;"
            placeholder="Write your post content using Markdown..."
            ref="editor">
          </mavon-editor>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm" :loading="loading">
            {{ isEdit ? 'Update' : 'Publish' }}
          </el-button>
          <el-button @click="saveDraft" :loading="loading" v-if="!isEdit">Save Draft</el-button>
          <el-button @click="goBack">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import api from '../api'

export default {
  name: 'CreatePost',
  data() {
    return {
      form: {
        title: '',
        content: ''
      },
      rules: {
        title: [
          { required: true, message: 'Please enter title', trigger: 'blur' },
          { max: 200, message: 'Title cannot exceed 200 characters', trigger: 'blur' }
        ],
        content: [
          { required: true, message: 'Please enter content', trigger: 'blur' }
        ]
      },
      toolbars: {
        bold: true,
        italic: true,
        header: true,
        underline: true,
        strikethrough: true,
        mark: true,
        superscript: true,
        subscript: true,
        quote: true,
        ol: true,
        ul: true,
        link: true,
        imagelink: true,
        code: true,
        table: true,
        fullscreen: true,
        readmodel: true,
        htmlcode: true,
        help: true,
        undo: true,
        redo: true,
        trash: true,
        save: false,
        navigation: true,
        alignleft: true,
        aligncenter: true,
        alignright: true,
        subfield: true,
        preview: true
      },
      loading: false,
      isEdit: false
    }
  },
  created() {
    if (this.$route.params.id) {
      this.isEdit = true
      this.loadPost()
    }
  },
  methods: {
    async loadPost() {
      try {
        const response = await api.getPostById(this.$route.params.id)
        if (response.code === 200) {
          const post = response.data
          const currentUser = this.$store.getters.user
          if (post.authorId !== currentUser.id) {
            this.$message.error('You do not have permission to edit this post')
            this.$router.push('/')
            return
          }
          this.form.title = post.title
          this.form.content = post.content
        } else {
          this.$message.error('Post not found')
          this.$router.push('/')
        }
      } catch (error) {
        this.$message.error('Failed to load post')
      }
    },
    submitForm() {
      this.$refs.form.validate(async (valid) => {
        if (valid) {
          this.loading = true
          try {
            let response
            if (this.isEdit) {
              response = await api.updatePost(this.$route.params.id, {
                title: this.form.title,
                content: this.form.content,
                status: 1
              })
            } else {
              response = await api.createPost({
                title: this.form.title,
                content: this.form.content,
                status: 1
              })
            }
            if (response.code === 200) {
              this.$message.success(this.isEdit ? 'Post updated' : 'Post published')
              this.$router.push(`/post/${response.data.id}`)
            } else {
              this.$message.error(response.message)
            }
          } catch (error) {
            this.$message.error('Operation failed')
          } finally {
            this.loading = false
          }
        }
      })
    },
    async saveDraft() {
      if (!this.form.title) {
        this.$message.warning('Please enter a title')
        return
      }
      this.loading = true
      try {
        const response = await api.createPost({
          title: this.form.title,
          content: this.form.content,
          status: 0
        })
        if (response.code === 200) {
          this.$message.success('Draft saved')
          this.$router.push('/myposts')
        } else {
          this.$message.error(response.message)
        }
      } catch (error) {
        this.$message.error('Failed to save draft')
      } finally {
        this.loading = false
      }
    },
    goBack() {
      this.$router.back()
    }
  }
}
</script>

<style scoped>
.create-post {
  max-width: 900px;
  margin: 0 auto;
}
</style>
