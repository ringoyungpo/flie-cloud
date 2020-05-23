<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import { createClient } from "hal-rest-client";

export default {
  name: 'Home',
  components: {
    HelloWorld
  },
  async mounted() {
    const client = createClient("/api");
    const fileCreated = await client.create("/files", { id: null, name: "Thomas", content: "jkdsjjssdksdfsfd" });
    fileCreated.prop('name', "alex")
    const fileUpdated = await fileCreated.update()
    const filesResource = await client.fetchResource("/files");
    const files = filesResource.prop("files")
    console.log({fileCreated,fileUpdated,files})
  }
}
</script>
