<template>
  <div>
    <h1>Личный кабинет</h1>
    <p>Добро пожаловать, {{ user ? user.userName : 'Guest' }}</p>
    <div v-if="projects.length">
      <h2>Ваши проекты</h2>
      <ul>
        <li v-for="project in projects" :key="project.id">{{ project.name }}</li>
      </ul>
    </div>
    <div v-else>
      <p>У вас нет проектов. Возможно, пришло время создать новый!</p>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    user() {
      return this.$store.state.user;
    },
    projects() {
      return this.$store.state.projects;
    },
  },
  created() {
    this.$store.dispatch('fetchProjects');
  },
};
</script>