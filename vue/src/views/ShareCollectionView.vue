<template>
  <div>
      <h1>Comics</h1>
      <div id="share-comics">
        <div id="scroller-div" class="scroller">
            <comic-component />
        </div>
      </div>
  </div>
</template>

<script>
import collectionService from '../services/CollectionService.js';
import ComicComponent from '../components/ComicComponent.vue';

export default {
    name: "share-collection",
    components: {ComicComponent},
    data(){
        return{
            comics: []
        }
    },
    created(){
        collectionService.getSharedCollectionLink(this.$route.params.id).then(response =>{
            this.comics = response.data;
        });
    }
}
</script>

<style>
#share-comics{
    width: 100vw;
    height: 70vh;
}
.scroller {
  height: 100vh;
  overflow-y: scroll;
  scrollbar-color: rebeccapurple green;
  scrollbar-width: thin;
  width: 100vw;
  padding: 40px 0px 0px 10px;
}
#scroller-div {
  height: 80vw;
  width: 95vw;
}

</style>