<template>
  <div id="login" class="text-center">
   <div id="first">
         <form class="form-signin" @submit.prevent="login">
      <h2 class="h2 mb-3 font-weight-normal">Login:</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
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
      <br>
      <button type="submit">Sign in</button>
      <br>
      <br>
      <router-link :to="{ name: 'register' }">Need an account?</router-link>

    </form>
   </div>
                 <ul>  <div  class="baby-heroes" >
      
      <img style="position: center;"
        src="https://images.squarespace-cdn.com/content/v1/53c00916e4b0f6e03b89b91b/1560712937902-JQU52MHUBNZ176MJPIHV/Marvel_HeroesLetsGoAnimatedPart1.gif?format=1500w"
        alt="Baby superhero lineup"
      />
    </div><h2>Marvel Fun Facts</h2>
                 <em><li>Marvel has released 32,000 comics as of 2009</li>
          <li>There is a team of avengers made up of animals</li>
          <li>Samuel L. Jackson’s appearance was used as a design reference for the character Nick Fury</li>
          <li>There is an alternate version of Spider-Man where Peter is an anthropomorphic pig</li>
          <li>Michael Jackson once tried to buy Marvel Comics</li>
          <li>The richest Marvel superhero is Thor</li>
          <li>Marvel Comics used to be called Timely Publications</li></em>
          <li id="sources-link">Sources: https://facts.net/marvel-facts/ </li>
          
        </ul>

  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

img {
    display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: flex-end;
  height: 60px;
}

#login {
  width: 100vw;
  display: grid;
  grid-template-areas: 
  "left-side right-side";
  grid-template-columns: 1fr 2fr;
}

#sources-link {
  font-weight: lighter;
  font-size: 10px;
  text-align: center;
}

#first {
  grid-area: left-side;
}

ul {
  grid-area: right-side;
  /* display: flex; */
  /* justify-content: right; */
  /* align-content: center; */
  margin-top: 10%;
  list-style: none;
}

.alert {
  color: rgb(226, 157, 28);
}

.sr-only, h3 {
  color: rgb(230, 221, 221);
}

.form-signin{
  height: 500px;
  /* background: linear-gradient(darkred, black); */
  color: white;
  margin-top: 100px;
  
  align-items: center;
  padding-left: 15px;

}
</style>