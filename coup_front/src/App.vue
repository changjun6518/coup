<template>
  <PageHeader></PageHeader>
  <ActionDescriptionBar></ActionDescriptionBar>
  <router-view/>
</template>

<script>


import PageHeader from "@/views/PageHeader";
import ActionDescriptionBar from "@/views/ActionDescriptionBar";
export default {
  name: 'App',
  components: {
    ActionDescriptionBar,
    PageHeader
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
