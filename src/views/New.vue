<template>
  <el-container>
    <el-main>
      <el-form ref="fileForm" :rules="rules" :model="fileForm" label-width="80px">
        <el-form-item label="name" prop="name">
          <el-input v-model="fileForm.name" ></el-input>
        </el-form-item>
        <el-form-item label="content" prop="content">
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
      rules: {
        name: [{ required: true, message: 'Please Enter a Valid Name', trigger: 'change' }],
        content: [{ required: true, message: 'Please Enter a Valid Content', trigger: 'change' }]
      },
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
      await this.$refs['fileForm'].validate(async(valid) => {
        if (valid) {
          const {name, content} = this.fileForm
          await client.create("/files", { name , content });
          this.$message({
            message: 'Success',
            type: 'success'
          })
        } else {
          return false;
        }
      })
    }
  }
}
</script>
