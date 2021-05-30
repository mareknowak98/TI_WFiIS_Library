<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <h1>Register:</h1>
      <b-form @submit.prevent="registerUser">
      <b-form-group id="input-group-1" label="Your name:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.name"
          required
          placeholder="Enter username"
        ></b-form-input>
      </b-form-group>

      <b-form-group
        id="input-group-2"
        label="Email address:"
        label-for="input-2"
        description=""
      >
        <b-form-input
          id="input-2"
          v-model="form.email"
          type="email"
          required
          placeholder="Enter email"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Password:" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="form.password"
          type="password"
          required
          placeholder="Enter your Password"
        ></b-form-input>
      </b-form-group>
      <b-button block type="submit" v-on:click="registerUser()" variant="secondary">Submit</b-button>
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
    name: "Register",
    components:{
        Navbar,
        Footer,
    },
  

    data() {
      return {
        form: {
          name: '',
          email: '',
          password: '',
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
          }
        }
          axios.post('http://localhost:5000/api/auth/signup',{
          name: this.form.name,
          email: this.form.email,
          password: this.form.password,
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
