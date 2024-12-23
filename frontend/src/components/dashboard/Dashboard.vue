<template>
  <div class="dashboard-component">
    <h1>Личный кабинет</h1>
    <p>Добро пожаловать, {{ user ? user.userName : 'Guest' }}</p>
    <div class="main-window">
      <div class="dashboard-menu">

        <!-- ПЕРЕДЕЛАТЬ НА КАСТОМНЫЙ СЕЛЕКТ -->

        <div class="dashboard-menu-button">
          <button @click="currentComponent = 'PersonalAccountComponent'">
            Личные данные
          </button>
        </div>
        <div class="dashboard-menu-button">
          <button @click="currentComponent = 'ProjectComponent'">
            Проекты
          </button>
        </div>
        <div class="dashboard-menu-button">
          <button>
            Выйти
          </button>
        </div>
      </div>
      <div class="workspace">
        <component :is="currentComponent"/>
      </div>
    </div>
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
import PersonalAccountComponent from './PersonalAccountComponent.vue'
import ProjectComponent from './ProjectComponent.vue'

export default {
  components: {
    PersonalAccountComponent,
    ProjectComponent
  },
  data() {
    return {
      currentComponent: "PersonalAccountComponent",
    }
  },
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

<style>
.dashboard-component {
  width: 100vw;
  height: 100vh;
}
.main-window {
  display: flex;
  flex-direction: row;
}
.dashboard-menu {
  width: 15vw;
  height: 80vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
}
.dashboard-menu-button {
  width: 100%;
}
.dashboard-menu-button button{
  width: 100%;
}
.workspace {
  width: 100%;
  height: 80vh;
  border: solid;
}
</style>