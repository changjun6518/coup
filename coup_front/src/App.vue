<template>
  <PageHeader></PageHeader>
  <PageDashboard></PageDashboard>
  <router-view/>
</template>

<script>


import PageHeader from "@/views/PageHeader";
import PageDashboard from "@/views/PageDashboard";
export default {
  name: 'App',
  components: {
    PageDashboard,
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
