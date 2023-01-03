import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    showComicTitle: false,
    showComicImage: true,
    showComicDescription: false,
    showAggStats: true,
    showCollectionStats: false,
    showAddComicBtn: false,
    currentCollectionId: 0,
    aggStats: {
        totalNumberOfUsers: 0,
        totalNumberOfCollections: 0,
        totalComicsInAllCollections: 0,
        highestNumberOfComicsInCollection: 0
      },
    collectionStats: {},
    comicArray: []
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_AGGSTATS(state, stats) {
      state.aggStats = stats;
    },
    SET_COLLECTION_STATS(state, stats) {
      state.collectionStats = stats;
    },
    SET_COLLECTION_ID(state, id) {
      state.currentCollectionId = id;
    },
    TOGGLE_STATS_BAR(state) {
      state.showAggStats !== false ? state.showAggStats = false : state.showAggStats = true; 
      state.showCollectionStats !== false ? state.showCollectionStats = false : state.showCollectionStats = true ;
    },
    TOGGLE_ADD_COMIC_BTN(state) {
      state.showAddComicBtn === false ? state.showAddComicBtn = true : state.showAddComicBtn = false;
    },
    ADD_COMIC_COLLECTION_ARRAY(state, comicList) {
      state.comicArray = [];
      state.comicArray = comicList
    }
  }
})
