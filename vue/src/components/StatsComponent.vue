<template>
  <div id="main-stats-div" class="scroll-container"  >
      <div class="stats hover scroll-text-slow" id="aggregate-stats" >
          <div id="total-users-num" class="margin-spacer">
              <h4 id="num-users">Number of Users: {{this.$store.state.aggStats.totalNumberOfUsers}}</h4>
          </div>
          <div id="total-collections-num" class="margin-spacer">
              <h4 id="num-collections">Number of Total Collections: {{this.$store.state.aggStats.totalNumberOfCollections}}</h4>
          </div>
          <div id="total-comics-all-collections" class="margin-spacer">
              <h4 id="all-collections-num-comics">Total Number of Comics in All Collections: {{this.$store.state.aggStats.totalComicsInAllCollections}}</h4>
          </div>
          <div id="most-comics-in-collection" class="margin-spacer">
              <h4 id="most-comics">Most Comics in a Collection: {{this.$store.state.aggStats.highestNumberOfComicsInCollection}}</h4>
          </div>
          <div id="total-num-comics-div" class="margin-spacer" v-if="this.$route.path.includes('/collections/')" >
              <h4 id="total-num-comics">Number of Comics in Collection: {{this.$store.state.collectionStats.totalNumberofComics}}</h4>
          </div>
          <div id="comics-per-superhero-div" class="margin-spacer" v-if="this.$route.path.includes('/collections/')" >
              <h4 id="comics-per-superhero">Number of Comics Hero Appeared in: {{this.$store.state.collectionStats.comicsPerSuperhero}}</h4>
          </div>
          <div id="comics-per-series-div" class="margin-spacer" v-if="this.$route.path.includes('/collections/')">
              <h4 id="comics-per-series">Number of Comics in each Series: {{this.$store.state.collectionStats.comicsPerSeries}}</h4>
          </div>
          <div id="oldest-comic-in-collection-div" class="margin-spacer" v-if="this.$route.path.includes('/collections/')">
              <h4 id="oldest-comic-in-collection">Oldest Comic in Collection: {{this.$store.state.collectionStats.oldestComicInCollection}}</h4>
          </div>
      </div>
  </div>
</template>
<script>
import CollectionService from '../services/CollectionService.js';
export default {
    name: 'stats-component',
    data() {
        return {
        }
    },
    methods: {
        getAggStats() {
          CollectionService.getAllAggStats().then(
            (response) => {
              const stats = response.data;
              this.$store.commit('SET_AGGSTATS', stats);
            }).catch(
              (error) => {
                if(error.response.status == 404) {
                  this.$router.push({name: 'error-view'})
                } else {
                  console.error(error);
                }
              }
            );
        }
        
    },
    created() {
        this.getAggStats();
    },
    updated() {
        if (this.$route.path.includes('/collections/')) {
            let statBar = document.getElementById('aggregate-stats')
            statBar.classList = "stats hover scroll-text"
        } else {
            let statBar = document.getElementById('aggregate-stats')
            statBar.classList = "stats hover scroll-text-slow"
        }
    }
}
</script>
<style>
#main-stats-div {
    display: flex;
    flex-direction: row;
    align-items: center;
    height: 100%;
    white-space: nowrap;
}
.stats {
    display: flex;
    width: 100vw;
}
#total-users-num {
    display: flex;
}
#total-collections-num {
    display: flex;
}
#total-comics-all-collections {
    display: flex;
}
#total-comics-all-collections {
    display: flex;
}
#most-comics-in-collection {
    display: flex;
}


.scroll-container {
  border: 3px solid black;
  border-radius: 5px;
  overflow: hidden;
}

.scroll-text {
  /* animation properties */
  -moz-transform: translateX(100%);
  -webkit-transform: translateX(100%);
  transform: translateX(100%);
  
  -moz-animation: my-animation 85s linear infinite;
  -webkit-animation: my-animation 85s linear infinite;
  animation: my-animation 100s linear infinite;
}

.hover:hover {
-webkit-animation-play-state:paused;
  -moz-animation-play-state:paused;
  -o-animation-play-state:paused;
  animation-play-state:paused;
  cursor: pointer;
}

.scroll-text-slow {
  /* animation properties */
  -moz-transform: translateX(100%);
  -webkit-transform: translateX(100%);
  transform: translateX(100%);
  
  -moz-animation: my-animation-slow 50s linear infinite;
  -webkit-animation: my-animation-slow 50s linear infinite;
  animation: my-animation-slow 55s linear infinite;
}

/* for Firefox */
@-moz-keyframes my-animation {
  from { -moz-transform: translateX(100%); }
  to { -moz-transform: translateX(-100%); }
}

/* for Chrome */
@-webkit-keyframes my-animation {
  from { -webkit-transform: translateX(100%); }
  to { -webkit-transform: translateX(-250%); }
}

@keyframes my-animation {
  from {
    -moz-transform: translateX(100%);
    -webkit-transform: translateX(100%);
    transform: translateX(100%);
  }
  to {
    -moz-transform: translateX(-250%);
    -webkit-transform: translateX(-250%);
    transform: translateX(-250%);
  }
}

@-moz-keyframes my-animation-slow {
  from { -moz-transform: translateX(100%); }
  to { -moz-transform: translateX(-100%); }
}

/* for Chrome */
@-webkit-keyframes my-animation-slow {
  from { -webkit-transform: translateX(100%); }
  to { -webkit-transform: translateX(-100%); }
}

@keyframes my-animation-slow {
  from {
    -moz-transform: translateX(100%);
    -webkit-transform: translateX(100%);
    transform: translateX(100%);
  }
  to {
    -moz-transform: translateX(-100%);
    -webkit-transform: translateX(-100%);
    transform: translateX(-100%);
  }
}


.margin-spacer {
    margin-left: 100px;
}

</style>