<template>
  <div class="about">
    <h1>This is an about page</h1>
  </div>
</template>

<script>
export default {
  mounted(){
    const {params} = this.$router
    console.log({params})
  }
}
</script>
