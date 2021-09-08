<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <h2>Wybierz książkę którą chcesz zarezerwować</h2>


    <b-button block type="submit" v-on:click="reserveBook()" variant="secondary">Zarezerwuj</b-button>


    <Footer></Footer>
  </b-jumbotron>
  </div>
</template>



<script>
import Navbar from './Navbar.vue'
import Footer from './Footer.vue'
// import Multiselect from 'vue-multiselect'


// import axios from 'axios';
  export default {
    name: "reserveBook",
    components:{
        Navbar,
        Footer,
        // Multiselect
    },
  

    data() {
      return {
        dueDate: null,
      }
    },

    mounted: function () {

    },

    computed: {
      
    },

    methods:{
        getDate(){
            var nowDate = new Date(); 
            var date = nowDate.toISOString().split('T')[0];
            this.today=date;
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
