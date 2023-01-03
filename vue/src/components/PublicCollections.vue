<template>
  <div class="collection">
<!-- on click, pop up to see inside collection with comics-->
    
    <div class="collections-box scroller">
        <div id="collection-object" v-for="collection in collections" v-bind:key="collection.id" v-on:click="displayCollection(collection.id)" >
            <h2 v-bind:to="{name: 'collection-details', params: {id: collection.id}}" 
            v-bind:collection="collection"
             >{{collection.name}}
             </h2>  
            <!--<collection-details v-bind:collection="collection" /> -->
        </div>
    </div>
    
    

  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';
// import CollectionDetails from '../components/CollectionDetails.vue';

export default {
    name: 'public-collections',
    data() {
        return {
            collections: []
        }
    },
    methods: {
        displayCollection(id) {
            this.$store.commit('SET_COLLECTION_ID', id);
            this.$router.push(`/collections/${id}`);
            this.getCollectionStats(id, length);
            this.$store.commit('TOGGLE_STATS_BAR');
            let statBar = document.getElementById('aggregate-stats')
            statBar.classList = "stats scroll-text"
        },
        getCollectionStats(id) {
            CollectionService
                .getCollectionStats(id)
                .then(response => {
                    if (response.status === 200) {
                        let collectionStats = response.data;
                        let stats = {
                            "totalNumberofComics": collectionStats.totalNumberofComics,
                            "comicsPerSuperhero": this.arrayToString(collectionStats.comicsPerSuperhero),
                            "comicsPerSeries": this.arrayToString(collectionStats.comicsPerSeries),
                            "oldestComicInCollection": collectionStats.oldestComicInCollection.title
                        }
                        this.$store.commit('SET_COLLECTION_STATS', stats);
                    }
                }).catch(
                    (error) => {
                        console.log(`Error Encountered: ${error}`);
                });
        },
        // This function converts an array into a string with comma separated values
        arrayToString(array) {
            let response = "";
            for (let i = 0; i < array.length; i++) {
                    let data = array[i];
                            
                    if (i + 1 != array.length) {
                        data += ",  ";
                    }
                    response += data;
                }
            return response;
        }
    },

    created() {
        //alert("public collections created")
        CollectionService.getAllPublic().then(
            (response) => {
            if (response.status == 200) {
                this.collections = response.data;
            }
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
}
</script>

<style>

#collection-object {
    cursor: pointer;
}

.collection{
  height: 90%;
  /* background: linear-gradient(darkred, black); */
  color: white;
  
  width: 100vw;
  align-items: center;
  padding: 15px;
  display: flex;
  flex-wrap: wrap;
}

#collectionObject {
    cursor:grabbing;
}

.collections-box {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
}

.scroller {
  width: 100%;
  max-height: 500px;
  overflow-y: scroll;
  scrollbar-color: darkred white;
  scrollbar-width: thin;
}

.collections-box div{
    border: black 5px solid;
    border-radius: 8px;
    height: 12rem;
    padding: 2rem;
    text-align: center;
    vertical-align: center;
    background-image: urL("../assets/something.png");
    background-position: center;
    background-size: 100%;
    background-blend-mode: darken;
    background-color: rgba(0, 0, 0, 0.7);
    margin: 2rem; 
    width: 15rem;
}

.collections-box a{
    display: inline-block;
    width: 100%;
    color:white;
    text-decoration: none;
    font-size: 1.5rem;
}



</style>