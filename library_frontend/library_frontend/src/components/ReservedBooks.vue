<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <b-card-group deck>
        <b-card
            border-variant="secondary"
            header="Twoje zarezerowane książki"
            header-bg-variant="transparent"
            align="center"
        >
            <b-card-text>
            <b-list-group v-for="(i) in reserved" :key="i.id">
                <ul class="list-unstyled">
                <b-media tag="li">
                <template #aside>
                    <b-img :src="require('../../static/single_book.jpeg')" blank-color="#abc" width="64" alt="placeholder"></b-img>
                </template>
                <h5 class="mt-0 mb-1" style="text-align: left;">{{i.book.name}}</h5>
                <b-row>
                    <b-col style="text-align: left;">Zarezerowowano do: {{i.dueDate}}</b-col>
                    <b-col style="text-align: left;"><b-button type="submit" v-on:click="removeReservation(i)" variant="secondary">Usuń rezerwację</b-button></b-col>
                </b-row>                

                <hr>
                </b-media>
                </ul>
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
    name: "reserveBook",
    components:{
        Navbar,
        Footer,
    },
  

    data() {
      return {
        reserved: [],
        deleted: null,
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
          axios.get('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/reserve/getUserBorrowed/me', config)
          .then(res => (this.reserved = res.data))
          .catch(err => {
            console.log(err);
            })
        },

        removeReservation(i){
            let config = {
              headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + localStorage.getItem('user-token')
              }
          }
          axios.delete('http://ec2-3-67-44-255.eu-central-1.compute.amazonaws.com:5000/reserve/removeReservation/' + i.id, config)
          .then(res => (this.deleted = res.data))
          .then(res => (console.log(res), this.getBorrowedByUser()))
          .catch(err => {
            console.log(err);
            })
        }


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
