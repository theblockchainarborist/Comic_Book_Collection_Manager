<template>
  <div class="collection-container">
      <!-- <h3>{{collection.name}}</h3> -->
      <div class="container" v-for="comic in comics" v-bind:key="comic.id">
          <div class="title-image">
            <p class="comic-title">{{comic.title}}</p>
            <img class="comic-image" v-if="comic.thumbnail" v-bind:src="comic.thumbnail.path + '.' + comic.thumbnail.extension" alt="">
          </div>
          <div class="description">
            <p>{{comic.description}}</p>
          </div>
      </div>
  </div>
</template>

<script>
import collectionService from '../services/CollectionService'

export default {
    name: 'collection-details',
    data() {
        return {
            comics: []
        }
    },
    props: ['collection'],
    created(){
        //alert("collection details created")
        collectionService.getComicsFromCollectionId(this.$route.params.id, this.$store.state.token).then(
            (response) => {
            this.comics = response.data;
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

<style scoped>
.comic-image {
    height: 300px;
}

.container {
    border: solid darkred;
    border-radius: 15px;
    padding: 10px;
    margin: 20px;
    background: linear-gradient(darkred, black);
    color: white;
    display: flex;
    align-items: center;
    justify-content: space-around;
    width: 900px;
}

.collection-container{
    margin: 100px auto 0 auto;
}

.comic-title{
    font-size: 1.5em;
}

.description{
    display: inline-block;
    padding: 40px;
    width: 500px;
}

.title-image{
    padding-left: 10px;
    padding-bottom: 10px;
    width: 500px;
    text-align: center;
}

</style>