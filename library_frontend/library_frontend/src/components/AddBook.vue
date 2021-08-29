<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <h1>Add book:</h1>
      <b-form @submit.prevent="registerUser">
      <b-form-group id="input-group-1" label="Book name:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.name"
          required
          placeholder="Enter username"
        ></b-form-input>
        </b-form-group>
      <b-button block type="submit" variant="secondary">Submit</b-button>
      </b-form>
    <Footer></Footer>
  </b-jumbotron>
  </div>
</template>



<script>
import Navbar from './Navbar.vue'
import Footer from './Footer.vue'


import axios from 'axios';
  export default {
    name: "addBook",
    components:{
        Navbar,
        Footer,
    },
  

    data() {
      return {
        form: {
          name: '',
        }
      }
    },

    mounted: function () {
    },

    computed: {
      
    },

    methods:{
      registerUser(){
        console.log('es')
        let config = {
          headers: {
            'Content-Type': 'application/json',
            "accept": "*/*"
          }
        }
          axios.post('http://localhost:5000/books',{
          name: this.form.name,
          }, config)
          .catch(err => {
          console.log(err);
          })
        console.log('es2')
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
