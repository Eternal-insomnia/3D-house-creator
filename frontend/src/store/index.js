import { createStore } from 'vuex';

const store = createStore({
  state: {
    user: null,
    projects: [],
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setProjects(state, projects) {
      state.projects = projects;
    },
  },
  actions: {
    async fetchProjects({ commit }) {
      const response = await fetch('/api/projects');
      const projects = await response.json();
      commit('setProjects', projects);
    },
  },
});

export default store;