<!-- PageAbout.vue -->
<template>
  <main>
    <CharacterDescriptionBar></CharacterDescriptionBar>
    <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
      <!-- Your content -->
      <div class="flex h-screen">
        <div class="flex-2">
          <ActionDescriptionDetail></ActionDescriptionDetail>
        </div>
        <div class="flex-1">
          <PageDesk></PageDesk>
        </div>
      </div>
    </div>
  </main>
</template>
<script>
import CharacterDescriptionBar from "@/views/CharacterDescriptionBar";
import ActionDescriptionDetail from "@/views/ActionDescriptionDetail";
import PageDesk from "@/views/PageDesk";
export default {
  components: {PageDesk, ActionDescriptionDetail, CharacterDescriptionBar}
}
</script>