<template>
  <div id="background">
    
      <div id="comic-details-card" class="scroller" :style="{backgroundImage: `url(${comic.thumbnail.path}.jpg)`}">
        <div id="comic-cover">
            <img id="image" :src="comic.thumbnail.path + '/portrait_uncanny.jpg' " >
        </div>
        <div id="comic-details">
            <h2 id="title">{{comic.title}}</h2>
            <br>
            <div v-for="creator in comic.creators.items" v-bind:key="creator.name">
                <p>{{creator.role}}</p>
                <p>{{creator.name}}</p>
                <br> 
            </div>
            <p>{{comic.description}}</p>  
        </div>
        
      </div>
      
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';

export default {
    name: 'comic-details',
    data() {
        return {
            comic: {}
        }
    },
    created() {
        CollectionService.getComicDetails(this.$route.params.comicId).then(
            (response) => {
                if(response.status == 200) {
                    this.comic = response.data;
                }
            }
        ).catch(
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
#background {
    width: 100vw;
    /* height: 80vh; */
}

#comic-details-card {
    margin: 3rem;
    padding: 3rem;
    border-radius: 1.5rem;
    display: flex;
    max-height: 70vh;
    background-blend-mode: darken;
    background-color: rgba(0, 0, 0, 0.8);
    background-size: 100vw;
    /* flex: wrap; */
}

.scroller {
  width: 95%;
  height: 90vh;
  overflow-y: scroll;
  scrollbar-color: darkred white;
  scrollbar-width: thin;
}

#comic-details {
    display: flex;
    flex-direction: column;
    margin-top: -0.5rem;
}

#comic-cover img {
    height: 80%;
    margin: 0 6rem 0 0;
}



</style>