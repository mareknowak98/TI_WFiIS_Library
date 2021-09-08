<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <b-container class="bv-example-row">
      <b-row>
        <b-col sm="4">
          <enlargeable-image :src="require('../../static/single_book.jpeg')" :src_large="require('../../static/single_book.jpeg')"/>
        </b-col>
        <b-col sm="8" style="text-align: left;">

          <div v-if="userInfo != null && userInfo.roles[0].name == 'ROLE_ADMIN'">
            <b-row>
              <b-col sm="6">
                <b-button block variant="danger" v-on:click="removeBook()">Usuń</b-button>
              </b-col>
              <b-col sm="6">
                <b-button block variant="warning" v-on:click="editBook()">Edytuj</b-button>
              </b-col>
            </b-row>
          </div>
          <h1>{{book.name}}</h1>
          <p>
            <strong>Autorzy: </strong>
              <template v-for="author in book.authors" style="white-space: nowrap;">
                {{author.author}},
              </template>
          </p>
          <p>
            <strong>Wydawnictwo: </strong>{{book.publisher}}
          </p>
          <p>
            <strong>Strony: </strong>{{book.pages}}
          </p>
          <p>
            <strong>Kategoria: </strong>
              <template v-for="category in book.categories" style="white-space: nowrap;">
                {{category.category}},
              </template>
          </p>
          <p>
            <strong>Nr ISBN: </strong>{{book.isbn}}
          </p>
          <p>
            <strong>Opis: </strong>{{book.description}}
          </p>
        </b-col>
      </b-row>
    </b-container>
    <Footer></Footer>
  </b-jumbotron>
  </div>
</template>



<script>
import Navbar from './Navbar.vue'
import Footer from './Footer.vue'


import axios from 'axios';
  export default {
    name: "bookDetailed",
    components:{
        Navbar,
        Footer,
    },
  

    data() {
      return {
        bookId: '',
        book:{},
        userInfo: null
      }
    },

    mounted: function () {

      this.bookId = this.$route.params.bookId;
      this.getBook(this.bookId)
      this.getUserInfo();
    },

    computed: {
      
    },

    methods:{
        getBook(i){
          let config = {
              headers: {
              'Content-Type': 'application/json',
              }
          }
          axios.get('http://localhost:5000/books/' + i, config)
          .then(res => (this.book = res.data))
          .catch(err => {
          console.log(err);
          })     
        },

        getUserInfo(){
        if(localStorage.getItem('user-token') != null){
          let config = {
              headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + localStorage.getItem('user-token')
              }
          }
          axios.get('http://localhost:5000/api/users/me', config)
          .then(res => (this.userInfo = res.data))
          .catch(err => {
          console.log(err);
          })    
        }
      }, 

      removeBook(){
        let config = {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('user-token')
          }
        }
          axios.delete('http://localhost:5000/books/' + this.book.id, config)
          .then(this.$goToMainPage())
          .catch(err => {
          console.log(err);
          })
      },

      editBook(){

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