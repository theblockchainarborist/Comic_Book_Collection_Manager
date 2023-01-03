<template>
  <div>
    <div id="title-bar">
      <h1>Comics</h1>
      <div v-if="isUsersCollection" id="share-div">
        <p id="share-coll">Share this collection link with your friends!</p>
        <div id="link-div">
          <button id="copy-btn" @click="copyText">Copy!</button>
          <router-link @click="copyText" v-bind:to="{name: 'share-collection', params:{id: this.$route.params.id}}">localhost:8080/shared/collections/{{this.$route.params.id}}</router-link>
        </div>
      </div>
    </div>
    <div id="all-comics">
      <div id="comic-add">
        <div id="comic-form">
        <div class="btn-div">
          <button v-if="isUsersCollection" id="add-comic-btn" v-on:click="createComic = !createComic">
            {{ createComic == false ? "Add Comic" : "Never Mind" }}
          </button>
          <!-- <button v-if="isUsersCollection" id="add-comic-btn">Share Collection</button> -->
          <button v-show="!createComic" v-if="isUsersCollection" id="add-comic-btn" v-on:click="exportToCsv">Export Collection</button>
        </div>

        

          <form id="add-comic-form" v-if="createComic == true">
              <label for="comic-Name">Title: </label>
              <input style="background-color: lightgray;"
                id="comic-Name"
                type="text"
                required="true"
                v-model="searchTitle"
              />
              <br />
              <div id="submit-comic-btn">
                <button v-on:click.prevent="searchComic"></button>
              </div>
          </form>

        <div v-if="createComic" id="comic-list-div">
          <div v-for="comic in comics" v-bind:key="comic.id">
            <a v-if="createComic" id="comics-list" v-on:click="addComic(comic.id)" href="#popup1">{{comic.title}}</a>
          </div>

          <div id="popup1" class="overlay">
            <div class="popup">
              <h2>Comic Added To Your Collection!</h2>
              <a class="close" href="#">&times;</a>
            </div>
          </div>

        </div>
          
        </div>
        

        <div v-show="!createComic" id="scroller-div" class="scroller" >
          <comic-component v-show="!createComic" />
        </div>
      </div>
      

      <img id="marvel" v-if="createComic" src="../assets/Ms.-Marvel-Transparent-PNG.png" alt="Ms. Marvel with Fist raised">
      
      <!-- appear if logged in (button) or routed through user homepage-->
    </div>
    
    
  </div>
</template>

<script>
import ComicComponent from "../components/ComicComponent.vue";
import CollectionService from "../services/CollectionService";
import { excelParser } from "../services/ExportService";

export default {
  components: { ComicComponent },
  data() {
    return {
      isUsersCollection: false,
      createComic: false,
      searchTitle: '',
      comics: [],
      showComics: true
    };
  },
  mounted() {
    this.loadButton();
    //alert("comic-view Path = " + JSON.stringify(this.$route.params))
  },
  methods: {
    loadButton() {
      if (this.$store.state.token != '') {
      let username = this.$store.state.user.username;
      //alert("username = " + username)
      CollectionService.getCollectionsFromUser(username, this.$store.state.token)
        .then((response) => {
         // alert("response status = " + response.status)
          if (response.status == 200) {
           // alert(JSON.stringify(response.data))
            response.data.forEach((collection) => {
              if (collection.id == this.$route.params.id) {
                this.isUsersCollection = true;
              }
            });
          }
        })
        .catch(
          (error) => {
            alert(`67 ${error}`);
          }
        );
     }
    },
    searchComic() {
      if (this.searchTitle != "") {
        CollectionService.searchComics(this.searchTitle).then(
          (response) => {
            let status = response.status;
            if (status == 200) {
              this.comics = response.data;
              this.searchTitle = '';
            }
          })
          .catch(
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
    addComic(apiID) {
      let collID = this.$route.params.id;
      let token = this.$store.state.token;

      CollectionService.addNewComic(collID, apiID, token).then(
        (response) => {
          let status = response.status;
          if (status == 201) {
            this.comics = [];
            document.location.reload();
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
    },
    exportToCsv() {
      let comicData = this.$store.state.comicArray;
      let csvData = [];
      // Stores new copies of attributes to avoid modifying store data
      comicData.forEach(comic => {
        let csvComic = Object.assign({}, comic);
        // Storing these attributes in a different format
        csvComic.series = comic.series.name;
        let comicDate = new Date(comic.dates[0].date);
        csvComic.dates = comicDate.toString().slice(0, 15);
        csvComic.thumbnail = comic.thumbnail.path + '.' + comic.thumbnail.extension;
        delete csvComic.creators;
        delete csvComic.characters;
        csvData.push(csvComic);
      });
      // Exports csvData to csv file
      excelParser().exportDataFromJSON(csvData, "collection");
    },
    copyText() {
      navigator.clipboard.writeText(`localhost:8080/shared/collections/${this.$route.params.id}`);
    }
  }
};
</script>

<style>

#title-bar {
  padding: 0px 1vw 0px 0px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.btn-div {
  width: 400px;
  padding: 2rem 0 1rem 2rem;
  display: flex;
  justify-content: space-between;
}

#share-coll {
  padding-right: 2rem;
}

#copy-btn {
  background-color: white;
  color: black;
  padding: 0.25rem;
  margin-right: 0.5rem;
  border-radius: 3px;
  font-size: 0.6rem;
}

#add-comic-btn {
  background-color: rgb(241, 215, 159);
  border-radius: 1rem;
  font-size: 1.5rem;
  color: black;
  font-family: "Kdam Thmor Pro", sans-serif;
  padding: 0.5rem;
  margin-top: -1rem;
}

#add-comic-form {
  padding-left: 2rem;
}

#all-comics {
  display: grid;
  grid-template-columns: 2fr 3fr;
}

#marvel {
  padding: 5%;
  max-height: 700px;
}

#all-comics {
  width: 100vw;
  /* padding: 2vh; */
  height: 70vh;
}

.scroller {
  height: 100vh;
  overflow-y: scroll;
  scrollbar-color: rebeccapurple green;
  scrollbar-width: thin;
  width: 100vw;
}

#scroller-div {
  height: 80vw;
  width: 95vw;
}

#submit-comic-btn button {
  background: url('../assets/submit.png');
  /* background-image: url(../assets/submit-shot.png);  */
  background-size: 100%;
  background-blend-mode: multiply;
  background-repeat: no-repeat;
  background-position: center;
  font-weight: bold;
  color: black;
  padding-top: 0.25rem;
  width: 70px;
  height: 50px;
  border: none;
  margin-top: 1rem;
}

#comic-list-div {
  padding: 2rem;
}

#comics-list:hover {
  text-decoration: underline;
  cursor: pointer;
  color: yellow;
}

#comics-list {
  padding: 0.25rem;
  display: inline-block;
  text-decoration: none;
  color: white;
  height: 2vh;
}

.overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  visibility: hidden;
  opacity: 0;
}
.overlay:target {
  visibility: visible;
  opacity: 1;
}

.popup {
  margin: 70px auto;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  position: relative;
  transition: all 5s ease-in-out;
}

.popup h2 {
  margin-top: 0;
  color: #333;
  font-family: Tahoma, Arial, sans-serif;
}
.popup .close {
  position: absolute;
  top: 20px;
  right: 30px;
  transition: all 200ms;
  font-size: 30px;
  font-weight: bold;
  text-decoration: none;
  color: #333;
}
.popup .close:hover {
  color: #06D85F;
}
.popup .content {
  max-height: 30%;
  overflow: auto;
}

.comic-anchor{
  text-decoration: none;
  color: white;
}

</style>