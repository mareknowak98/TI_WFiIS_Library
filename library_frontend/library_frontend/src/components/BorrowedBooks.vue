<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">    
    <b-card-group deck>
        <b-card
            border-variant="secondary"
            header="Aktualne wypożyczenia"
            header-bg-variant="transparent"
            align="center"
        >
            <b-card-text>
            <b-list-group v-for="(i) in borrowed" :key="i.id">
                <div v-if="i.returned == false">

                    <ul class="list-unstyled">
                    <b-media tag="li">
                    <template #aside>
                        <b-img :src="require('../../static/single_book.jpeg')" blank-color="#abc" width="64" alt="placeholder"></b-img>
                    </template>
                    <h5 class="mt-0 mb-1" style="text-align: left;">{{i.book.name}}</h5>
                    <b-row>
                        <b-col>Wypożyczono: {{i.startDate}}</b-col>
                    </b-row>
                    <hr>
                    </b-media>
                    </ul>
                
                </div>
                </b-list-group>
            </b-card-text>
        </b-card>
        <b-card
            border-variant="secondary"
            header="Zakończone wypożyczenia"
            header-bg-variant="transparent"
            align="center"
        >
            <b-card-text>
                <b-list-group v-for="(i) in borrowed" :key="i.id">
                <div v-if="i.returned == true">

                    <ul class="list-unstyled">
                    <b-media tag="li">
                    <template #aside>
                        <b-img :src="require('../../static/single_book.jpeg')" blank-color="#abc" width="64" alt="placeholder"></b-img>
                    </template>
                    <h5 class="mt-0 mb-1" style="text-align: left;">{{i.book.name}}</h5>
                    <b-row>
                        <b-col>Wypożyczono: {{i.startDate}}</b-col>
                        <b-col>Oddano: {{i.dueDate}}</b-col>

                    </b-row>
                    <hr>
                    </b-media>
                    </ul>
                
                </div>
                </b-list-group>
            </b-card-text>
        </b-card>
    </b-card-group>

    <Footer></Footer>
  </b-jumbotron>
  </div>
</template>



<script>
import Navbar from './Navbar.vue'
import Footer from './Footer.vue'


import axios from 'axios';
  export default {
    name: "borrowedBooks",
    components:{
        Navbar,
        Footer,
    },
  

    data() {
      return {
        borrowed: [],
        userInfo: null
      }
    },

    mounted: function () {
        this.getBorrowedByUser();
    },

    computed: {
      
    },

    methods:{

       getBorrowedByUser(){
            let config = {
              headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + localStorage.getItem('user-token')
              }
          }
          axios.get('http://localhost:5000/api/users/me', config)
          .then(res => (this.userInfo = res.data))
          .then(resp => (console.log(resp) ,
            axios({
                url: 'http://localhost:5000/borrow/getUserBorrowed/' + this.userInfo.id,
                method: 'get',
                headers: {
                    'Content-Type': 'application/json', 
                    'Authorization': 'Bearer ' + localStorage.getItem('user-token')
                    },
            }).then (resp => (this.borrowed = resp.data))))
        },

    }
  }
</script>

<style scoped>
@media (min-width: 100px) {
    .container{
        max-width: 1400px;
    }
}

</style>
