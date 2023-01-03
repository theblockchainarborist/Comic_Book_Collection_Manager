<template>
  <div class="image-container">
    <div id="subcontainer" v-for="comic in comics" v-bind:key="comic.id">
      

      <div id="sample1">
        <router-link v-bind:to="{name: 'comic-details', params: {comicId: comic.id}}">
                <img id="imagepic" v-bind:src="comic.image" alt="" />
            </router-link>
  
        </div>
    </div>
  </div>
</template>

<script>
// import CollectionService from "../services/CollectionService"

export default {
  name: "comic-card",
  data() {
    return {
      comics: [
        {
          id: 93328,
          title: "Shang-Chi (2021) #2",
          image:
            "https://i.annihil.us/u/prod/marvel/i/mg/9/b0/609ee2a089b7d/clean.jpg",
        },
        {
          id: 70777,
          title: "Ms. Marvel (2015) #36",
          image:
            "https://i.annihil.us/u/prod/marvel/i/mg/e/b0/5be371a0f2dae/clean.jpg",
        },
        {
          id: 71093,
          title: "Doctor Strange (2018) #17",
          image:
            "https://i.annihil.us/u/prod/marvel/i/mg/5/40/5d4c2bded3ffc/clean.jpg",
        },
      ],
    };
  },
  // created() {
  //     CollectionService.getComicsFromCollectionId
  // }
};
</script>

<style>
#imagepic {
  height: 350px;
  
}

.image-container {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  width: 100%;
  height: 100%;
  padding-bottom: 12px;
}


</style>