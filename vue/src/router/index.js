import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import ComicView from '../views/ComicView.vue'
import UserHomeView from '../views/UserHomeView.vue'
import CollectionView from '../views/CollectionView.vue'
import ComicDetailsView from '../views/ComicDetailsView.vue'
import ErrorView from '../views/ErrorView.vue'
import ShareCollectionView from '../views/ShareCollectionView.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false   /**WAS TRUE */
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/public",
      name: "public-collections",
      component: CollectionView
    },
    {
      path: "/collections/:id",
      name: "collection-details",
      component: ComicView
    },
    {
      path: "/stats/:id",
      name: "aggregate-stats"
    },
    {
      path: "/user/collections",
      name: "user-home",
      component: UserHomeView
    },
    {
      path: "/comics/:comicId",
      name: "comic-details",
      component: ComicDetailsView
    },
    {
      path: "/404",
      name: "error-view",
      component: ErrorView
    },
    {
      path:"/shared/collections/:id",
      name: "share-collection",
      component: ShareCollectionView
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
