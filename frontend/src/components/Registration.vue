<template>
	<div class="registration-component">
		<h1>Регистрация</h1>
    <form @submit.prevent="registration" method="post" class="registrartion-form">
		  <input v-model="name" type="userName" placeholder="Имя" required /><br>
			<input v-model="surname" type="userSurname" placeholder="Фамилия" required ><br>
      <input v-model="email" type="email" placeholder="Электронная почта" required /><br>
      <input v-model="password" type="password" placeholder="Пароль" required /><br>
      <button type="submit">Зарегистрироваться</button>
    </form>
		<label>Уже есть аккаунт? </label>
		<span @click="toLogin" type="submit" class="registration-to-login__button">Войти</span>
	</div>
</template>

<script>
export default {
	data() {
		return {
			name: '',
			surname: '',
			email: '',
			password: '',
			emailError: false,
		};
	},
	methods: {
		async registration() {
			try {
				const response = await fetch('http://localhost:8080/api/users', {
					method: 'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify({ userName: this.name, userSurname: this.surname, userEmail: this.email, userPassword: this.password }),
				});
				if (response.ok) {
					const user = await response.json();
					this.$store.commit('setUser', user);
					this.toLogin();
				} else {
					const error = await response.json();
					alert(error.message || 'Registration failed');
				}
			} catch (error) {
				console.error('Error:', error);
				alert('An error occurred while connecting to the server.');
			}
		},
		toLogin() {
			this.$router.push('/login');
		}
	}
}
</script>

<style>
.registration-to-login__button {
	padding: 0 5px;
	color: #00f;
	text-decoration: underline;
}
</style>