<template>
  <el-container>
    <el-main>
      <flip-down :endDate="endDate" :type="1" @timeUp="timeUp" />
      <el-form ref="fileResourceForm" :model="fileForm" label-width="80px">
        <el-form-item label="name">
          <el-input v-model="fileForm.name" :disabled="!available"></el-input>
        </el-form-item>
        <el-form-item label="content">
          <el-input type="textarea" v-model="fileForm.content" :disabled="!available">
          </el-input>
        </el-form-item>
        <el-button type="primary" @click="onSubmit" :disabled="!available">Save</el-button>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import { createClient } from "hal-rest-client";
import SockJS from  'sockjs-client';  
import  Stomp from 'stompjs';
import FlipDown from 'vue-flip-down'

const client = createClient("/api");

export default {
  components: {
    FlipDown
  },
  data() {
    return {
      fileResource: undefined,
      fileForm: {
        props:{
          name: '',
          content: ''
        }
      },
      endDate: 0,
      stompClient: undefined,
      available: false,
      id: undefined
    }
  },
  methods: {
    async onSubmit() {
      const {name, content} = this.fileForm
      this.fileResource.prop('name', name)
      this.fileResource.prop('content', content)
      await this.fileResource.update()
    },
    timeUp(){
      this.stompClient.disconnect()
      this.connectWebsocket()
    },
    connectWebsocket(){
      this.stompClient = Stomp.over(new SockJS('/api/editing_center')); 
      this.stompClient.connect({}, () => {
        this.stompClient.subscribe(`/user/topic/editing_box`, editReplayFram => {
          const editReplay = JSON.parse(editReplayFram.body)
          const {available} = editReplay
          if(available){
            this.endDate = new Date().getTime() + 60 * 1000
          }
          this.available = available

        })
        this.stompClient.send(`/ui/edit_action/${this.id}`, {}, JSON.stringify({'fileId': this.id, type: 'APPLY_FOR_EDITING'}))
      })
    }
  },
  async mounted(){
    this.id = this.$route.params.id
    const fileResource = await client.fetchResource(`/files/${this.$route.params.id}`);
    const {name, content} = fileResource.props
    this.fileForm = {name, content}
    this.fileResource = fileResource
    this.connectWebsocket()
  },
  beforeDestroy(){
    this.stompClient.disconnect()
  },
}
</script>