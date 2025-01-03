<template>
	<div class="registration-component">
		<h1>Регистрация</h1>
    <form @submit.prevent="registration" method="post" class="registrartion-form">
			<input v-model="name" type="userName" placeholder="Имя" /><br>
			<input v-model="surname" type="userSurname" placeholder="Фамилия"><br>
      <input v-model="email" type="userEmail" placeholder="Электронная почта" />
      <span v-if="emailError" class="error">Invalid email format</span><br>
      <input v-model="password" type="password" placeholder="Пароль" /><br>
      <button type="submit">Зарегистрироваться</button>
    </form>
		<label>Уже есть аккаунт? </label>
		<span @click="toLogin" type="submit" class="registration-to-login__button">Войти</span>
	</div>
</template>

<script>
export default {
	methods: {
		registration() {
			alert("TEST TEXT");
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