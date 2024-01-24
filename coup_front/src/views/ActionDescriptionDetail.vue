<template>
  <ul role="list" class="divide-y divide-gray-100">
    <li v-for="action in actions" :key="action.email" class="flex justify-between gap-x-6 py-5">
      <div class="flex min-w-0 gap-x-4">
        <img class="h-12 w-12 flex-none rounded-full bg-gray-50" :src="action.imageUrl" alt="" />
        <div class="min-w-0 flex-auto">
          <p class="text-sm font-semibold leading-6 text-gray-900 inline-block">{{ action.name }}</p>
          <p class="text-xs leading-6 text-gray-900">{{ action.role }}</p>
        </div>
      </div>
      <div class="hidden shrink-0 sm:flex sm:flex-col sm:items-end">
        <p class="text-xs leading-6 text-gray-900">{{ action.role }}</p>
        <p class="mt-1 text-xs leading-5 text-gray-500">{{ action.description }}</p>
      </div>
    </li>
  </ul>
</template>

<script setup>
const actions = [
  {
    name: '수입',
    description: '1원 획득, 방해할 수 없습니다.',
    imageUrl:
        require('../assets/user.png'),
  },
  {
    name: '해외원조',
    description: '2원 획득, 공작에게 방해받을 수 있습니다.',
    imageUrl:
        require('../assets/user.png'),
  },
  {
    name: '쿠',
    description: '7원 지불, 한명을 지목하여 카드를 잃게합니다. 방해할 수 없습니다.',
    imageUrl:
        require('../assets/user.png'),
  },
  {
    name: '징세',
    description: '3원 획득, 공작 행동으로 방해할 수 없습니다.',
    imageUrl:
        require('../assets/duke.png'),
  },
  {
    name: '강탈, 갈취',
    description: '다른 플레이어로부터 2원을 빼앗아옵니다. 사령관 / 대사로 방해할 수 있습니다.',
    imageUrl:
        require('../assets/captain.png'),
  },
  {
    name: '교환',
    description: '덱의 카드 2장과 원하는 카드를 교환, 방해할 수 없습니다.',
    imageUrl:
        require('../assets/ambassador.png'),
  },
  {
    name: '암살',
    description: '3원 지불, 다른 플레이어를 지목하여 암살합니다. 귀부인으로 방해할 수 있습니다.',
    imageUrl:
        require('../assets/assassin.png'),
  },
]
</script>