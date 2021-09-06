<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <b-container class="bv-example-row">
      <b-row>
        <b-col sm="4">
          <enlargeable-image src="https://lh3.googleusercontent.com/proxy/swjeISxuNV9nRi4WfIkTuL-gy2pK-_5R5WRRWtDBm6XjDp6-C9NCc9Xp_nQUrBbabKT9OdWhmCjKRC3HUhFughTMgZaQJ7I" src_large="https://lh3.googleusercontent.com/proxy/swjeISxuNV9nRi4WfIkTuL-gy2pK-_5R5WRRWtDBm6XjDp6-C9NCc9Xp_nQUrBbabKT9OdWhmCjKRC3HUhFughTMgZaQJ7I"/>
        </b-col>
        <b-col sm="8" style="text-align: left;">
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
        book:{}
      }
    },

    mounted: function () {

      this.bookId = this.$route.params.bookId;
      this.getBook(this.bookId)
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