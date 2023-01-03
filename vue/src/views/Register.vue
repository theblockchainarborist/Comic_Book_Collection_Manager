<template>
  <div id="whole-page">
    <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h2 class="h2 mb-3 font-weight-normal">Create Account</h2>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username: </label>
      <input style="background-color: lightgray;"
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br>
      <label for="password" class="sr-only">Password: </label>
      <input style="background-color: lightgray;"
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input style="background-color: lightgray;"
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <br>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <br>
      <br>
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      
    </form>
  </div>
  </div>
  
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style >
#register {
  width: 100vw;
}

#whole-page {
  background-image: urL('../assets/spiderman.png');
  background-position-x: right;
  background-size: 500px;
  /* background-position-y: ; */
  background-position-x: 1000px;
  height: 100%;
}

.form-register{
  /* height: 500px; */
  /* background: linear-gradient(darkred, black); */
  color: white;
  margin-top: 100px;
  align-items: center;
  padding-left: 15px;
}

aside {
  display: grid;
  justify-content: flex-end;
  /* margin-left: 500px */
}

.alert {
  color: rgb(226, 157, 28)
}

input{
  margin:10px;
}
</style>
