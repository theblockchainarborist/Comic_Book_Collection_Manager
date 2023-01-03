<template>
  <div><h1>My Collections</h1>
    <!--TODO //display, create, add comic button -->
    <div id="user-list">
      <div class="btn-div">
        <button
          id="add-coll-btn"
          v-on:click="createCollection = !createCollection"
        >
          {{ createCollection == false ? "Add Collection" : "Never Mind" }}
        </button>
      </div>
      <form id="add-col-form" v-if="createCollection">
        <label for="col-Name">Name: </label>
        <input style="background-color: lightgray;"
          id="col-Name"
          type="text"
          required="true"
          v-model="newCollection.name"
        />
        <br />
        <label for="col-Status">Status: </label>
        <select style="background-color: lightgray;"
          name="col-Status"
          id="col-Status"
          v-model="newCollection.status"
          required="true"
        > 
          <option value="private">Private</option>
          <option value="public">Public</option>
        </select>
        <br />
        <div id="submit-coll-btn">
          <button v-on:click="addCollection"></button>
        </div>
      </form>

      <div v-if="!createCollection" id="user-collections" class="scroller">
        <div
          class="title"
          v-for="collection in collections"
          v-bind:key="collection.id"
          v-on:click="displayCollection(collection.id)"
        >
          <h2 id="my-collection"
              v-on:click="displayCollection(collection.id)"
              v-bind:to="{
              name: 'collection-details',
              params: { id: collection.id },
            }"
            >{{ collection.name }}
          </h2>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService";
export default {
  name: "user-home",
  data() {
    return {
      collections: [],
      createCollection: false,
      newCollection: {
        name: "",
        status: "",
      },
    };
  },
  mounted() {
    let username = this.$store.state.user.username;
    let token = this.$store.state.token
    CollectionService.getCollectionsFromUser(username, token)
      .then((response) => {
        if (response.status == 200) {
          this.collections = response.data;
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
  },
  methods: {
    addCollection() {
      let username = this.$store.state.user.username;
      let token = this.$store.state.token;
      if (this.newCollection.name != "" && this.newCollection.status != "") {
        CollectionService.addNewCollection(username, token, this.newCollection)
          .then((response) => {
            let status = response.status;
            if (status == 201) {
              alert("Your collection has been added!");
              this.newCollection = {
                name: '',
                status: ''
              }
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
                if(error.response.status == 404) {
                  this.$router.push({name: 'error-view'})
                } else {
                  console.error(error);
                }
              }
            );
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
    
  }
};
</script>

<style>

h1 {
  padding-left: 10px;
}

#my-collection {
    cursor: pointer;
}

.btn-div {
  padding: 2rem 0 1rem 2rem;
}

.title {
  cursor: pointer;
}

#add-coll-btn {
  background-color: rgb(241, 215, 159);
  border-radius: 1rem;
  font-size: 1.5rem;
  color: black;
  font-family: "Kdam Thmor Pro", sans-serif;
  padding: 0.5rem;
  margin-top: -1rem;
}

#user-collections {
  padding: 2rem;
  font-size: 1.25rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
}

#user-collections a {
    background-color: black;
    mix-blend-mode:color-burn;
    border-radius: 10px;
    display: inline-block;
    width: 100%;
    color:white;
    text-decoration: none;
    font-size: 1.5rem;
}

.scroller {
  width: 95%;
  height: 75%;
  overflow-y: scroll;
  scrollbar-color: darkred white;
  scrollbar-width: thin;
}

.title {
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

#user-collections a:hover {
  text-decoration: none;
}

#user-list {
  /* background: linear-gradient(darkred, black); */
  /* margin-top: 4rem; */
  /* height: 90%; */
  width: 100vw;
}

#add-col-form {
  padding: 2rem;
  color: white;
}

#col-Status {
  margin-left: 0.5em;
}

#submit-coll-btn > button {
  background: url('https://www.nicepng.com/png/full/355-3557440_clipart-free-library-comic-clipart-comic-callout-pow.png');
  background-size: 100%;
  background-blend-mode: multiply;
  background-repeat: no-repeat;
  background-position: center;
  font-weight: bold;
  padding-top: 0.25rem;
  width: 70px;
  height: 50px;
  border: none;
  margin-top: 1rem;
  color: black;
}
</style>