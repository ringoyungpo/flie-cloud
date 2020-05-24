<template>
  <el-container>
    <el-main>
      <el-form ref="fileResourceForm" :model="fileForm" label-width="80px">
        <el-form-item label="name">
          <el-input v-model="fileForm.name"></el-input>
        </el-form-item>
        <el-form-item label="content">
          <el-input type="textarea" v-model="fileForm.content">
          </el-input>
        </el-form-item>
        <el-button type="primary" @click="onSubmit">submit</el-button>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import { createClient } from "hal-rest-client";

const client = createClient("/api");

export default {
  data() {
    return {
      fileResource: undefined,
      fileForm: {
        props:{
          name: '',
          content: ''
        }
      }
    }
  },
  methods: {
    async onSubmit() {
      const {name, content} = this.fileForm
      this.fileResource.prop('name', name)
      this.fileResource.prop('content', content)
      await this.fileResource.update()

    }
  },
  async mounted(){
    const fileResource = await client.fetchResource(`/files/${this.$route.params.id}`);
    const {name, content} = fileResource.props
    this.fileForm = {name, content}
    this.fileResource = fileResource
    console.log({fileResource})
  }
}
</script>
