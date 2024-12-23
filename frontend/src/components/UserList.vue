<template>
  <div>
    <h1>Список пользователей</h1>
    <ul>
    <li v-for="user in users" :key="user.userId">{{ user.userName }}</li>
    </ul>
    <button @click="loadUsers">Загрузить пользователей</button>
  </div>
</template>
  
<script>
  import UserDataService from '@/services/UserDataService'

  export default {
    name: 'users',
    data() {
      return {
        users: []
      }
    },
    methods: {
      retrieveUsers() {
        console.log(UserDataService.getAll())
        UserDataService.getAll()
          .then(response => {
            this.users = response.data
          })
          .catch(e => {
            alert(e)
          })
      }
    },
    mounted() {
      this.retrieveUsers()
    }
  }
</script>
  