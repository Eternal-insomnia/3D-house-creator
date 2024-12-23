<template>
  <div>
    <h1>Dashboard</h1>
    <p>Welcome, {{ user ? user.email : 'Guest' }}</p>
    <div v-if="projects.length">
      <h2>Your Projects</h2>
      <ul>
        <li v-for="project in projects" :key="project.id">{{ project.name }}</li>
      </ul>
    </div>
    <div v-else>
      <p>No projects available. Create your first project!</p>
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