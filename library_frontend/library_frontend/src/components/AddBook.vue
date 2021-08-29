<template>
  <div class="container">
  <navbar></navbar>
  <b-jumbotron class="jumbotron jumbotron-home">
    <h2>Authors</h2>
    {{authors}}
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

        <b-form-group id="input-group-2" label="Author id (temporary):" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.authorId"
          required
          placeholder="Enter author's id"
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
          authorId: ''
        },
        authors: {},
        book: '',
      }
    },

    mounted() {
        this.getAuthors();
    },

    computed: {
      
    },

    methods:{
    //   registerUser(){
    //     console.log('es')
    //     let config = {
    //       headers: {
    //         'Content-Type': 'application/json',
    //         "accept": "*/*"
    //       }
    //     }
    //       axios.post('http://localhost:5000/api/books',{
    //       name: this.form.name,
    //       }, config)
    //       .catch(err => {
    //       console.log(err);
    //       })
    //     console.log('es2')
    //   }


    registerUser(){
        axios({
            url: 'http://localhost:5000/books',
            method: 'post',
            headers: {
                'Content-Type': 'application/json', 
                // 'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjMwMjM4MTYyLCJleHAiOjE2MzA4NDI5NjJ9.OWH9qtkiquie7Vhl0cz05vRqdY1MSufRrWnJJzQnEJi_e4X0QjLECqIdJ2P2drsjw38Zgxzz61qb2NShNAv3CA'
                },
            data: {
                name: this.form.name
            }
        }).then(resp => (this.book = resp.data.id ,
        axios({
            url: 'http://localhost:5000/books/' + resp.data.id + '/authors/' + this.form.authorId + '/add',
            method: 'post',
            headers: {
                'Content-Type': 'application/json', 
                },
        })))

      },

      getAuthors(){
         let config = {
          headers: {
            'Content-Type': 'application/json',
          }
        }
          axios.get('http://localhost:5000/authors', config)
          .then(res => (this.authors = res.data))
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