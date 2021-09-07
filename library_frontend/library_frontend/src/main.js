import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import axios from "axios";
import VueRouter from 'vue-router';
import Home from './components/Home.vue';
import Register from './components/Register.vue';
import EnlargeableImage from '@diracleo/vue-enlargeable-image';
import addBook from './components/AddBook.vue'
import DetailedBook from './components/DetailedBook.vue';
import addCategory from './components/AddCategory.vue'
import addAuthor from './components/AddAuthor.vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false

// window.axios = require('axios');

import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
Vue.use(VueRouter);
Vue.use(BootstrapVue)
Vue.use(EnlargeableImage)
Vue.use(BootstrapVueIcons)
Vue.component('enlargeable-image', EnlargeableImage)
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';


const routes = [{
        path: "/",
        name: "Home",
        component: Home
    },
    {
        path: "/register",
        name: "register",
        component: Register
    },
    {
        path: "/addBook",
        name: "addBook",
        component: addBook
    },
    {
        path: "/addCategory",
        name: "addCategory",
        component: addCategory
    },
    {
        path: "/addAuthor",
        name: "addAuthor",
        component: addAuthor
    },
    {
        path: "/book/:bookId",
        name: "bookDetailed",
        component: DetailedBook
    },
    {
        path: '/*',
        redirect: { name: 'home' }
    },
]


Vue.mixin({
    methods: {
        $goToAnotherPage: function(page) {
            this.$router.push(page);
        },
        $goToMainPage: function() {
            if (this.$route.path !== "/") this.$router.replace("/")
        },
        $getToken: function() {
            return localStorage.getItem("user-token");
        }
    }
})

const router = new VueRouter({
    routes: routes,
    mode: 'history',
});

Vue.config.productionTip = false;

const DEFAULT_TITLE = 'Library App';

router.afterEach((to) => {
    Vue.nextTick(() => {
        document.title = to.meta.title || DEFAULT_TITLE;
    });
});

// Vue.prototype.$basePath = basePath;

new Vue({
    el: '#app',
    router: router,
    render: h => h(App)
});