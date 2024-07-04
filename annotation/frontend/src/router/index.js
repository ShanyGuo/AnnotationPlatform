import Vue from 'vue';
import VueRouter from 'vue-router';
import WelcomePage from '../components/WelcomePage.vue';
import RatingPage from '../components/RatingPage.vue';
import ThankYou from '../components/ThankYou.vue';

Vue.use(VueRouter);

const routes = [
  { path: '/', component: WelcomePage },
  { path: '/rating-page', component: RatingPage },
  { path: '/thank-you', component: ThankYou }
];

const router = new VueRouter({
  routes,
  mode: 'history' // Use 'history' mode to remove the hash from URLs
});

export default router;
