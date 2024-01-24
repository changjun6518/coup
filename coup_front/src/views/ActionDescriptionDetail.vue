<template>
  <ul role="list" class="divide-y divide-gray-100">
    <li v-for="person in people" :key="person.email" class="flex justify-between gap-x-6 py-5">
      <div class="flex min-w-0 gap-x-4">
        <img class="h-12 w-12 flex-none rounded-full bg-gray-50" :src="person.imageUrl" alt="" />
        <div class="min-w-0 flex-auto">
          <p class="text-sm font-semibold leading-6 text-gray-900 inline-block">{{ person.name }}</p>
        </div>
      </div>
      <div class="hidden shrink-0 sm:flex sm:flex-col sm:items-end">
        <p class="text-xs leading-6 text-gray-900">{{ person.role }}</p>
        <p class="mt-1 text-xs leading-5 text-gray-500">{{ person.passive }}</p>
<!--        <p v-if="person.lastSeen" class="mt-1 text-xs leading-5 text-gray-500">-->
<!--          Last seen <time :datetime="person.lastSeenDateTime">{{ person.lastSeen }}</time>-->
<!--        </p>-->
<!--        <div v-else class="mt-1 flex items-center gap-x-1.5">-->
<!--          <div class="flex-none rounded-full bg-emerald-500/20 p-1">-->
<!--            <div class="h-1.5 w-1.5 rounded-full bg-emerald-500" />-->
<!--          </div>-->
<!--          <p class="text-xs leading-5 text-gray-500">Online</p>-->
<!--        </div>-->
      </div>
    </li>
  </ul>
</template>

<script setup>
const people = [
  {
    name: '공작(Duke)',
    passive: '해외원조를 방해할 수 있습니다.',
    role: '세금징수 (코인 3개를 획득합니다.)',
    imageUrl:
        require('../assets/duke.png'),
  },
  {
    name: '암살자(Assassin)',
    passive: '',
    role: '코인 3원으로 상대를 암살할 수 있습니다.',
    imageUrl:
        require('../assets/assassin.png'),
  },
  {
    name: '사령관(Captain)',
    passive: '갈취를 막을 수 있습니다.',
    role: '상대를 지목하여 코인 2원을 갈취할 수 있습니다.',
    imageUrl:
        require('../assets/captain.png'),
  },
  {
    name: '대사(Ambassador)',
    passive: '갈취를 막을 수 있습니다.',
    role: '카드 2장을 가져와 원하는 카드와 교환할 수 있습니다.',
    imageUrl:
        require('../assets/ambassador.png'),
  },
  {
    name: '귀부인(Contessa)',
    passive: '암살을 방어할 수 있습니다.',
    role: '',
    imageUrl:
        require('../assets/contessa.png'),
  },
]
</script>