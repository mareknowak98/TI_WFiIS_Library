import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import axios from "axios";
import VueRouter from 'vue-router';
import Home from './components/Home.vue';
import Register from './components/Register.vue';
import addBook from './components/AddBook.vue'
import DetailedBook from './components/DetailedBook.vue';
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false

// window.axios = require('axios');
import { BootstrapVue } from 'bootstrap-vue'
Vue.use(VueRouter);
Vue.use(BootstrapVue)
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

const DEFAULT_TITLE = 'sth';

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