<template>

  <div id="app">
    <div id="nav">

      <div id="nav-div">
         <div class="text-center">

            <v-col id="left-side">
                <v-btn class="left-button" rounded color="white" dark> <router-link v-bind:to="{ name: 'home' }">Home</router-link> </v-btn>
                 <v-btn class="left-button" rounded color="primary" dark> <router-link v-bind:to="{name: 'public-collections'}">Public Collections</router-link></v-btn>
                <v-btn class="left-button" rounded color="primary" dark v-show="$store.state.token != '' "> <router-link v-bind:to="{name: 'user-home'}" >My Collections</router-link></v-btn>
            </v-col>
           
           <v-img style="margin-left: 25vw; margin-top: 10px; max-width: 60px;" class="ml-auto" src="./assets/cc.png" />
    
      </div>
         <v-col id="right-side">
                  <v-spacer></v-spacer>
                   <v-btn class="right-button" rounded color="white" dark v-show="$store.state.token == '' " > <router-link v-bind:to="{name: 'login'}" >Login</router-link></v-btn>
                  <v-btn class="right-button" rounded color="primary" dark v-show="$store.state.token == '' "> <router-link v-bind:to="{name: 'register'}" >Create Account</router-link></v-btn>
                   <v-btn class="right-button"  rounded color="primary" dark v-show="$store.state.token != '' ? true : false"> <router-link v-bind:to="{ name: 'logout' }" >Logout</router-link></v-btn>

            </v-col>
      
  </div>
    
    </div>

    <div style="position: relative;" id="stats-div">
      <stats-component />

    </div>


<!-- card for background red/black-->
  <v-card  id="card">
    <router-view class="main" />
  </v-card>




    <footer class="footer">
      <div id="footer-div">
        <p id="footer-text">Data provided by Marvel. © 2014 Marvel</p>
      </div>
      
    </footer>

  </div>


</template>


<script>
import StatsComponent from './components/StatsComponent.vue';

export default {
  components: {StatsComponent },
  data() {
    return {
      isHidden: false
    }
  }
};
</script>

<style>

#app {
  background-color: lightgray;
  font-family: "Kdam Thmor Pro", sans-serif;
  height: 100vh;
  width: 100vw;
  display: grid;
  grid-template-areas: 
  "header header"
  "stats stats"
  "main main"
  "footer footer";
  grid-template-rows: 10% 3% 77% 10%;
 
}

#card {
  background: linear-gradient(darkred, black);
  border-radius: 0;
  height: 100%;
  width: 100%;
}

.right-button {
  margin-right: 5px;
}

.left-button {
  margin-right: 5px;
}

.text-center {
  display: flex;
  flex-direction: row;
}

#nav-div {
  display: flex;
  flex-direction: row;
  flex-basis: 100%;
}

#right-side {
  display: flex;
  justify-self: right;
  width: 100%;
}

#left-side {
  display: flex;
  justify-content: right;
  width: 100%;
}

#nav {
  grid-area: header;
  background-color: lightgray;
  padding-left: 15px;
  background-image: url('./assets/city.png');
  width: 100vw;
  height: 100%;
}

#stats-div {
  grid-area: stats;
  height: 100%;
  width: 100%;
}

.main {
  grid-area: main;
  color: white;
  
}

footer {
  grid-area: footer;
  width: 100%;
  height: 100%;
  align-self: baseline;
  color: rgb(226, 157, 28);
  background-color: black;
}

#footer-div {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
  height: 100%;
}



/* view-header {
  background: linear-gradient(darkred, black)
} */

a{
  color: white;
}

#nav a{
  color: white;
}


a:visited {
  color:gray;

}

#nav a:hover{
  text-decoration: none;
  color:red;
}

.router-link-exact-active{
  text-decoration: none;
  color: black;
}

</style>