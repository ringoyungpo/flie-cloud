<template>
  <el-container>
    <el-main>
      <el-form ref="fileResourceForm" :model="fileForm" label-width="80px">
        <el-form-item label="name">
          <el-input v-model="fileForm.name" ></el-input>
        </el-form-item>
        <el-form-item label="content">
          <el-input type="textarea" v-model="fileForm.content" >
          </el-input>
        </el-form-item>
        <el-button type="primary" @click="onSave" >Save</el-button>
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
    async onSave() {
      const {name, content} = this.fileForm
      await client.create("/files", { name , content });
      this.$message({
          message: 'Success',
          type: 'success'
        })
    }
  }
}
</script>
