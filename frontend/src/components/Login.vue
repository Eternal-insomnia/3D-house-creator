<template>
  <div class="login-component">
    <h1>Вход</h1>
    <form @submit.prevent="login" method="post">
      <input v-model="email" type="email" placeholder="Электронная почта" required/><br>
      <input v-model="password" type="password" placeholder="Пароль" required/><br>
      <button type="submit">Войти</button>
    </form>
    <label>Нет аккаунта? </label>
    <span @click="toRegistration" type="submit" class="login-to-registration__button">Зарегистрироваться</span>
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
      async login() {
        try {
          const params = new URLSearchParams({ userEmail: this.email }).toString();
          const response = await fetch(`http://localhost:8080/api/users/email?${params}`);
          console.log(params, response)
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
      toRegistration() {
        this.$router.push('/registration');
      },
    },
  };
</script>
  
<style>
.login-to-registration__button {
	padding: 0 5px;
	color: #00f;
	text-decoration: underline;
}
</style>