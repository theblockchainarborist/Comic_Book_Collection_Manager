<template>
  <div id="comic">
    <div style="justify: center; margin-left: 650px;" class="loading" v-if="isLoading"><img src="https://i.gifer.com/ZlXl.gif" alt=""></div> 
    
    <div id="comic-div" v-for="comic in comics" v-bind:key="comic.id">
        
        <div id="comic-title-div" v-if="$store.state.showComicTitle === true">
            <h2 id="comic-title">{{comic.title}}</h2>
        </div>
        
        <div id="image-div" v-if="$store.state.showComicImage === true">
            <router-link v-bind:to="{name: 'comic-details', params: {comicId: comic.id}}">
                <img id="image" :src="comic.thumbnail.path + '/portrait_uncanny.jpg' " >
            </router-link>
        </div>

        <div id="description" v-if="$store.state.showComicDescription === true">
            <p id="comic-description">{{comic.description}}</p>
        </div>
    </div>
  </div>
</template>

<script>

import CollectionService from '../services/CollectionService.js'

export default {
    name: 'comic',
    data() {
        return {
            comics: [],
            isLoading: true
        }
    },
    created(){
       //alert("comic component created")
        CollectionService.getComicsFromCollectionId(this.$route.params.id, this.$store.state.token).then(
            (response) => {
            if(response.status == 200) {
                this.comics = response.data;
                this.isLoading = false; 
                this.$store.commit('ADD_COMIC_COLLECTION_ARRAY', this.comics);
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

#comic {
    display: flex;
    flex-wrap: wrap;
}

#comic-div {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 15vw;
    height: 15vw;
    flex-basis: 10%;
    color: white;
    margin: 1vw;
}

#comic-title-div {
    display: flex;
    font-size: 1em;
    align-self: center;
    justify-self: center;
}

#comic-title {
    text-align: center;
}

#image-div {
    display: flex;
    justify-content: center;
    height: 100%;
    width: 60%;
}

#image {
    height: 100%;
    border: solid 1px black;
}

#description {
    align-self: center;
    margin: 1% 20%;
}

#comic-description {
    text-align: center;
}

</style>