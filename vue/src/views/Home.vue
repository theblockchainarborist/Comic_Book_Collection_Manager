<template>
  <div class="home"><h1 id="front-page">Home</h1>
    <div id="subcontainer">
      <div id="main-text">
        
        <h1 id="createtext">
          Create a collection of your personal favorite comics!
        </h1>
        <p>Click on a comic to see more details!</p>
      </div> 

      <div id="main-images">
        <comic-card />
      </div>

      
     
    </div>
    
    <br>
    <div id="leader-things">
      <leaderboard-stats id="leaderboard-stats"/>
    </div>
    
  </div>
</template>

<script>
import ComicCard from "../components/ComicCard.vue";
import LeaderboardStats from "../components/LeaderboardStats.vue";

export default {
  components: { ComicCard, LeaderboardStats },
  name: "home",
};
</script>

<style scoped>

#front-page {
  padding: 15px 0 0 30px;
  margin: 0;
}

h2 {
  margin-left: 20px;
}

ul {
  margin-top: 10px;
}

.scroller {
  width: 80%;
  height: 100px;
  overflow-y: scroll;
  scrollbar-color: rebeccapurple green;
  scrollbar-width: thin;
}

ul {
  list-style: none;
}

p {
  padding-top: 10px;
}

img {
  height: 80px;
  /* align-self: baseline; */
}

#subcontainer {
  /* width: 200px; */
  display: grid;
  grid-template-areas: "text photos";
  grid-template-columns: 1fr 4fr;
  /* flex-direction: row;  */
  /* justify-content: space-between; */
  height: 90%;
  /* background: linear-gradient(darkred, black); */
  color: white;
  margin-top: 30px;
  padding-top: 20px;
  align-items: top;
  position: relative;  background-image: url('../assets/marvelbg.jpg');
  background-repeat: repeat;
}

/* #subcontainer::after {

  opacity: 50%;
  position: absolute;
  z-index: 1;
} */

#main-text {
  display: inline-block;
  flex-wrap: wrap;
  grid-area: text;
  width: 280px;
  margin: 0 30px;
}

h1 {
  display: inline-block;
}

#main-images {
  grid-area: photos;
}

.home {
  display: inline-block;
  width: 100vw;
}

#leaderboard-stats{
  text-align: center;
}
</style>