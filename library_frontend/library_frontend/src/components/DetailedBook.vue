<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
      {{book}}
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