<template>
  <div>
    <h1>Вход</h1>
    <form @submit.prevent="login" method="post">
      <input v-model="email" type="userEmail" placeholder="Электронная почта" />
      <span v-if="emailError" class="error">Invalid email format</span>
      <input v-model="password" type="userPassword" placeholder="Пароль" />
      <button type="submit">Войти</button>
    </form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        email: '',
        password: '',
        emailError: false,
      };
    },
    methods: {
      validateEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
      },
      async login() {
        try {
          const response = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ userEmail: this.email, userPassword: this.password }),
          });
          if (response.ok) {
            const user = await response.json();
            this.$store.commit('setUser', user);
            this.$router.push('/dashboard');
          } else {
            const error = await response.json();
            alert(error.message || 'Login failed');
          }
        } catch (error) {
          console.error('Error:', error);
          alert('An error occurred while connecting to the server.');
        }
      },
    },
  };
</script>
  